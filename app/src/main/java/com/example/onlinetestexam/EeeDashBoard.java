package com.example.onlinetestexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EeeDashBoard extends AppCompatActivity {


    Toolbar toolbar;
    ActionBar actionBar;
    FirebaseAuth mAuth;

    DatabaseReference reference;
    List<EeeDataModel> eeedataModels;
    newAdapter adapter;
    RecyclerView eeerecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eee_dash_board);




        mAuth=FirebaseAuth.getInstance();

        //Toolbar setup
        toolbar=findViewById(R.id.eeeDashToolbar);
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        actionBar.setTitle("EeeDashBoard");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);





        reference= FirebaseDatabase.getInstance().getReference("EeeData");
        eeedataModels = new ArrayList<>();


        eeerecyclerView=findViewById(R.id.EeeDashRecycleViewId);
        adapter = new newAdapter(this,eeedataModels);

        eeerecyclerView.setLayoutManager(new LinearLayoutManager(this));
        eeerecyclerView.setHasFixedSize(true);
        eeerecyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new newAdapter.ClickSoftListener() {
            @Override
            public void onItemClick(int position, View v) {


                if(position==0)
                {
                    Intent setsElectronic=new Intent(getApplicationContext(),ElectronicSubActivity.class);
                    startActivity(setsElectronic);
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
                        EeeDataModel model = ds.getValue(EeeDataModel.class);
                        eeedataModels.add(model);
                        adapter.notifyDataSetChanged();
                    }
                }

                catch (Exception e){
                    Toast.makeText(EeeDashBoard.this, "Could not parse the data properly", Toast.LENGTH_LONG).show();


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
