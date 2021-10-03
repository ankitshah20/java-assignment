package com.halo.carInfoWithAi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.halo.carInfoWithAi.Models.Data;
import com.halo.carInfoWithAi.R;

import java.util.HashMap;
import java.util.Objects;

public class CovidPatientDetailActivity extends AppCompatActivity {
    public static final String TAG1 = "TAG";
    public static final String TAG2 = "TAG";

    public  static  final  String EXTRA_POST_KEY = "POST_KEY";

    DatabaseReference mDatabase;

    EditText mcname;
    EditText mage;
    EditText mdate;
    EditText mcontact;
    EditText maddress;
    EditText mprofession;
    EditText mhighContact;
    EditText mlowContact;
    EditText mfamilyMember;
    Button mUpdateButton;
    Button mDeleteButton;
    Button mBtnBack;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Data data;
    EditText mID;
    private static final String TAG = "SignUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_dialog);
        mBtnBack = findViewById(R.id.activity_care_detail_back_btn);
        mUpdateButton = findViewById(R.id.btn_delete_from_update);
        mDeleteButton = findViewById(R.id.deleteData);
        mcname =findViewById(R.id.cname1);
        mage =findViewById(R.id.age1);
        mdate =findViewById(R.id.date1);
        mcontact =findViewById(R.id.contact1);
        maddress =findViewById(R.id.address1);
        mprofession =findViewById(R.id.profession1);
        mhighContact =findViewById(R.id.highContact1);
        mlowContact =findViewById(R.id.lowContact1);
        mfamilyMember =findViewById(R.id.familyMember1);
        mID = findViewById(R.id.ID1);

        final String postKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if(postKey == null){
            onBackPressed();
            return;
        }
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        Log.e("postKey",postKey+"");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Covidtracker").child(postKey);
        mDatabase.keepSynced(true);

        mBtnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "clicked");
                onBackPressed();
            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             Log.e("snapshot",snapshot.getValue()+"");
             if(snapshot.getValue()!=null) {
                 HashMap hashMap = ((HashMap) snapshot.getValue());
                 Data data = new Data(
                         hashMap.get("cname").toString(),
                         hashMap.get("age").toString(),
                         hashMap.get("date").toString(),
                         hashMap.get("contact").toString(),
                         hashMap.get("address").toString(),
                         hashMap.get("profession").toString(),
                         hashMap.get("highContact").toString(),
                         hashMap.get("lowContact").toString(),
                         hashMap.get("familyMember").toString(),
                         hashMap.get("id").toString()
                 );
                 //Log.e("snapshot data",data.toString()+"");
                 CovidPatientDetailActivity.this.data = data;
                 CovidPatientDetailActivity.this.initData();
             }
             else{
                 CovidPatientDetailActivity.this.data = new Data();
                 CovidPatientDetailActivity.this.initData();
             }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("snapshot ERrr",error.toString()+"");
            }
        });


        mUpdateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String cname = mcname.getText().toString().trim();
                String age = mage.getText().toString();
                String date = mdate.getText().toString();
                String contact = mcontact.getText().toString().trim();
                String address = maddress.getText().toString().trim();
                String profession = mprofession.getText().toString();
                String highContact = mhighContact.getText().toString();
                String lowContact = mlowContact.getText().toString();
                String familyMember = mfamilyMember.getText().toString();
                String ID = mID.getText().toString();


//                if (numberPlate.isEmpty()) {
//                    nNumberInfo.setError("Number Plate Data is required");
//                    return;
//                }
//                if (companyName.isEmpty()) {
//                    mCompanyName.setError("Company Name cant be empty");
//                    return;
//                }
//                if (modelNo.isEmpty()) {
//                    mModelNo.setError("Model No cant be empty");
//                    return;
//                }
//                if (ownerName.isEmpty()) {
//                    mOwnerName.setError("Owner Name cant be empty");
//                    return;
//                }
//                if (ownerContact.length() < 6) {
//                    mOwnerContact.setError("Owner Contact must be >= 6 Characters");
//                    return;
//                }
                mDatabase.child(postKey).removeValue();
                String id = mDatabase.push().getKey();
                HashMap hashMap = new HashMap();
                hashMap.put("cname", cname);
                hashMap.put("age", age);
                hashMap.put("id", ID);
                hashMap.put("date", date);
                hashMap.put("contact", contact);
                hashMap.put("address", address);
                hashMap.put("profession", profession);
                hashMap.put("highContact", highContact);
                hashMap.put("lowContact", lowContact);
                hashMap.put("familyMember", familyMember);
                mDatabase.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(CovidPatientDetailActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(CovidPatientDetailActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mDeleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Task<Void> deletedTask = mDatabase.removeValue();
                deletedTask.addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CovidPatientDetailActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                            Intent detailsIntent = new Intent(CovidPatientDetailActivity.this, CovidPatientListActivity.class);
                            startActivity(detailsIntent);
                        } else {
                            Toast.makeText(CovidPatientDetailActivity.this, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                }
        });
    }

    private void initData() {
        mcname.setText(data.getCname());
        mage.setText(data.getAge());
        mdate.setText(data.getDate());
        mcontact.setText(data.getContact());
        maddress.setText(data.getAddress());
        mprofession.setText(data.getProfession());
        mhighContact.setText(data.getHighContact());
        mlowContact.setText(data.getLowContact());
        mfamilyMember.setText(data.getFamilyMember());
        mID.setText(data.getId());
    }
}
