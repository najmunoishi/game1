package com.example.onlinetestexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CseDashBoard extends AppCompatActivity {


    Toolbar toolbar;
    ActionBar actionBar;
    FirebaseAuth mAuth;

    DatabaseReference reference;
    List<CseDataModel> csedataModels;
    CseRecycleViewAdapter adapter;
    RecyclerView cserecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse_dash_board);




        mAuth=FirebaseAuth.getInstance();

        //Toolbar setup
        toolbar=findViewById(R.id.cseDashToolbar);
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        actionBar.setTitle("CseDashBoard");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);





        reference= FirebaseDatabase.getInstance().getReference("CseData");
        csedataModels = new ArrayList<>();


        cserecyclerView=findViewById(R.id.CseDashRecycleViewId);
        adapter = new CseRecycleViewAdapter(this,csedataModels);

        cserecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cserecyclerView.setHasFixedSize(true);
        cserecyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new CseRecycleViewAdapter.ClickSoftListener() {
            @Override
            public void onItemClick(int position, View v) {


                if(position==0)
                {
                    Intent setsSoftware=new Intent(getApplicationContext(),SoftwareSubActivity.class);
                    startActivity(setsSoftware);
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"This is not adjust listener",Toast.LENGTH_SHORT).show();

                }

            }
        });


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                try {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        CseDataModel model = ds.getValue(CseDataModel.class);
                        csedataModels.add(model);
                        adapter.notifyDataSetChanged();
                    }
                }

                catch (Exception e){
                    Toast.makeText(CseDashBoard.this, "Could not parse the data properly", Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
