package com.halo.carInfoWithAi.Activity;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.halo.carInfoWithAi.Models.*;

        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.firebase.auth.FirebaseAuth;
        import com.firebase.ui.database.FirebaseRecyclerAdapter;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.halo.carInfoWithAi.R;

public class LowRIskPeopleActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    String post_key;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        String key = intent.getStringExtra("key");

        setContentView(R.layout.activity_data_entry);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uID = mUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Covidtracker");
        mDatabase.keepSynced(true);
        recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        FloatingActionButton create = findViewById(R.id.float_create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editDataIntent = new Intent(LowRIskPeopleActivity.this, AddCovidPatientActivity.class);
                startActivity(editDataIntent);
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.logout:
                        mAuth.signOut();
                        if(mAuth.getCurrentUser()==null){
                            Toast.makeText(LowRIskPeopleActivity.this, "Logout Successful.", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                        else{
                            Toast.makeText(LowRIskPeopleActivity.this, "Error !!! Logout Not Success.", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        break;
                    case R.id.profileView:
                        Intent profileIntent = new Intent(LowRIskPeopleActivity.this, ProfileActivity.class);
                        startActivity(profileIntent);
                        break;
                    case R.id.home:
                        Intent MainActivity = new Intent(LowRIskPeopleActivity.this, MainActivity.class);
                        startActivity(MainActivity);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data, myViewHolder> adapter = new FirebaseRecyclerAdapter<Data, myViewHolder>(
                Data.class, R.layout.activity_about_us, myViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(myViewHolder viewHolder, final Data data, final int i) {
                viewHolder.setLowAffected(data.getLowContact());


                viewHolder.myView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        post_key = getRef(i).getKey();
//                        Intent intent = new Intent(CarListActivity.this, CarInfoDetailActivity.class);
//                        intent.putExtra(EXTRA_POST_KEY , post_key);
//                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }


    public static class myViewHolder extends RecyclerView.ViewHolder {
        View myView;

        public myViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
        }

        public void setLowAffected(String lowAffected) {
            TextView lowContact1  = myView.findViewById(R.id.lowContact);
            lowContact1.setText(lowAffected);
        }


    }
}
