package com.example.onlinetestexam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SoftwareSubActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ActionBar actionBar;
    Button easyQuiz,hardQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_sub);

        easyQuiz=findViewById(R.id.EasyQuizBtn);
        hardQuiz=findViewById(R.id.hardQuizBtn);

        //Toolbar setup
        toolbar=findViewById(R.id.SoftwareDashToolBar);
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Patterns");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        easyQuiz.setOnClickListener(this);
        hardQuiz.setOnClickListener(this);




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

    @Override
    public void onClick(View v) {


        switch (v.getId())
        {
            case R.id.EasyQuizBtn:


                Intent QustionActivity=new Intent(SoftwareSubActivity.this,QuestionActivity.class);
                QustionActivity.putExtra("type","EasyQuiz");
                startActivity(QustionActivity);
                break;
            case R.id.hardQuizBtn:
                Intent hard=new Intent(SoftwareSubActivity.this,QuestionActivity.class);
                hard.putExtra("type","HardQuiz");
                startActivity(hard);
                break;

        }

    }
}
