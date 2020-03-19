package com.example.onlinetestexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordRecovery extends AppCompatActivity {
    EditText resetPassEmail;
    Button  resetPassword;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        resetPassEmail=findViewById(R.id.PassRecoveryEmailEdit);
        resetPassword=findViewById(R.id.ResetPassword);


        mAuth=FirebaseAuth.getInstance();


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=resetPassEmail.getText().toString().trim();

                if(userEmail.isEmpty())
                {
                    resetPassEmail.setError("Enter your registered  email address");
                    resetPassEmail.requestFocus();
                    return;

                }
                else{


                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {

                                Toast.makeText(getApplicationContext(),"Password Reset Email is Sent",Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i=new Intent(PasswordRecovery.this,MainActivity.class);
                                startActivity(i);

                            }
                            else{

                                Toast.makeText(getApplicationContext(),"Error in Sending Password Reset",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }




            }
        });

    }
}
