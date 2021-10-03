package com.halo.carInfoWithAi.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.halo.carInfoWithAi.Models.*;
import com.halo.carInfoWithAi.R;

public class AddCovidPatientActivity extends AppCompatActivity  {

    public static final String TAG1 = "TAG";
    public static final String TAG2 = "TAG";
    DatabaseReference mDatabase;
    EditText mCName;
    EditText mage;
    EditText mdate;
    EditText mcontact;
    EditText maddress;
    EditText mprofession;
    EditText mhighContact;
    EditText mlowContact;
    EditText mfamilyMember;
    Button mSaveButton;
    Button mBtnBack;
    RecyclerView recyclerView;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    private static final String TAG = "SignUp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custominput);


        mCName = findViewById(R.id.cname);
        mage = findViewById(R.id.age);
        mdate=findViewById(R.id.date);
        mcontact = findViewById(R.id.contact);
        maddress = findViewById(R.id.address);
        mprofession = findViewById(R.id.profession);
        mhighContact=findViewById(R.id.highContact);
        mlowContact = findViewById(R.id.lowContact);
        mfamilyMember = findViewById(R.id.familyMember);
        mSaveButton = findViewById(R.id.btn_save);
        mBtnBack = findViewById(R.id.btnBack);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        recyclerView = findViewById(R.id.recycleView);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Covidtracker");
        mDatabase.keepSynced(true);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"clicked");
                onBackPressed();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String cname = mCName.getText().toString().trim();
                 String age = mage.getText().toString();
                 String date = mdate.getText().toString();

                 String contact = mcontact.getText().toString().trim();
                 String address = maddress.getText().toString().trim();
                 String profession = mprofession.getText().toString();
                 String highContact = mhighContact.getText().toString();
                 String lowContact = mlowContact.getText().toString();
                 String familyMember = mfamilyMember.getText().toString();


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

                String id = mDatabase.push().getKey();
                Data data = new Data(cname,age,date,contact,address,profession,highContact,lowContact,familyMember,id);
                mDatabase.child(id).setValue(data);
                Toast.makeText(AddCovidPatientActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }
}
