package com.example.onlinetestexam;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity  {
    Toolbar toolbar;
    ActionBar actionBar;
    RadioButton op1,op2,op3,op4,bt;
    TextView questionText;
    RadioGroup radiogroup;
    Button submitBtn;
    AlertDialog i;

    FirebaseAuth mAuth;
    String path,QuestionAnswer;

    ArrayList<String> positionstores=new ArrayList<>();
    String []positionsto=null;
    DatabaseReference reference;
    List<QuestionDataModel> questiondataModels;
    QuestionRecycleViewAdapter adapter;
    RecyclerView questionrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionText = findViewById(R.id.SoftEasyQuizQuestion);

        submitBtn=findViewById(R.id.submitButton);
        radiogroup=findViewById(R.id.radioGroup);




        mAuth = FirebaseAuth.getInstance();


        //Toolbar setup
        toolbar = findViewById(R.id.questionDashToolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("QuestionDash");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        //Data received using intent and showing the same activity

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String type = bundle.getString("type");


            if (type.equals("HardQuiz")) {
                path = "02/SoftwareHardQuiz";
            } else if (type.equals("EasyQuiz")) {
                path = "01/SoftwareEasyQuiz";
            }

        }
        reference = FirebaseDatabase.getInstance().getReference("SoftwareQuiz/").child(path);
        questiondataModels = new ArrayList<>();
        questionrecyclerView = findViewById(R.id.QuestionDashRecycleViewId);
        adapter = new QuestionRecycleViewAdapter(this, questiondataModels);
        questionrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        questionrecyclerView.setHasFixedSize(true);
        questionrecyclerView.setAdapter(adapter);

     adapter.setOnItemClick(new QuestionRecycleViewAdapter.QuestionClick() {
     @Override
     public void onItemClick(int position, View v) {

 //Find out the radio group from adapter
         RadioGroup r=v.findViewById(R.id.radioGroup);
         Log.i("check",position+"-"+r.getCheckedRadioButtonId());

    }
 });



//Data is retrieve here from database
      reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                try {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        QuestionDataModel model = ds.getValue(QuestionDataModel.class);
                        questiondataModels.add(model);
                        adapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    Toast.makeText(QuestionActivity.this, "Could not parse the data properly", Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//Check the  question and answer

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<String> data=new ArrayList<>();
                for(int i=0;i<questiondataModels.size();i++){
                    View view=questionrecyclerView.getChildAt(i);
                    radiogroup =view.findViewById(R.id.radioGroup);
                    bt=view.findViewById(radiogroup.getCheckedRadioButtonId());
                    bt.setChecked(false);
                    if(!bt.getText().toString().equals(questiondataModels.get(i).getAnswer())){
                        data.add((i+1)+". "+questiondataModels.get(i).getAnswer());
                    }


                }

                QuestionAnswer="";
                for (int i=0;i<data.size();i++){
                   QuestionAnswer+= (data.get(i))+"\n";


                }

                    i=new AlertDialog.Builder(QuestionActivity.this)
                            .setTitle("RESULTS")
                            .setMessage("Congratulations,You Got "+(questiondataModels.size()-data.size())+" Out of "+questiondataModels.size()+"\n\n\nAnswer:\n"+QuestionAnswer)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }

                            }).show();


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
