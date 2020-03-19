package com.example.onlinetestexam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText SignUpNameText,SignUpEmailText,SignUpPhoneNumberText,SignUpPasswordText,RetypePasswordText;
    Button SignUpSubmitButton;
    TextView ClickHereText;
    ProgressBar SignUpProgress;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignUpNameText=findViewById(R.id.Name);
        SignUpEmailText=findViewById(R.id.EmailSignUp);
        SignUpPhoneNumberText=findViewById(R.id.PhoneNumber);
        SignUpPasswordText=findViewById(R.id.PasswordSignUp);
        RetypePasswordText=findViewById(R.id.RetypePasswordSignUp);
        SignUpSubmitButton=findViewById(R.id.SubmitButton);
        ClickHereText=findViewById(R.id.ClickHereText);
        SignUpProgress=findViewById(R.id.SignUpProgressbar);

        databaseReference= FirebaseDatabase.getInstance().getReference("Client: ");





        mAuth = FirebaseAuth.getInstance();



        ClickHereText.setOnClickListener(this);
        SignUpSubmitButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.ClickHereText:
                Intent LogInPageLayout=new Intent(SignUp.this,MainActivity.class);
                startActivity(LogInPageLayout);
                break;
            case R.id.SubmitButton:
               SignUpMethod();
                break;


        }

    }


    public void SignUpMethod()
    {
         final String FullName=SignUpNameText.getText().toString().trim();
         final String email=SignUpEmailText.getText().toString().trim();
        String Password=SignUpPasswordText.getText().toString();
        final String Phone=SignUpPhoneNumberText.getText().toString().trim();
        String RetypePass=RetypePasswordText.getText().toString();


        if(FullName.isEmpty())
        {
            SignUpNameText.setError("Enter Your Full Name");
            SignUpNameText.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
            SignUpEmailText.setError("Enter an email address");
            SignUpEmailText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            SignUpEmailText.setError("Enter a valid email address");
            SignUpEmailText.requestFocus();
            return;
        }
        if(Phone.isEmpty())
        {
            SignUpPhoneNumberText.setError("Enter a Phone Number");
            SignUpPhoneNumberText.requestFocus();
            return;

        }
        if(Phone.length()<11)
        {
            SignUpPhoneNumberText.setError("Enter a valid Mobile Number");
            SignUpPhoneNumberText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(Password.isEmpty())
        {
            SignUpPasswordText.setError("Enter a password");
            SignUpPasswordText.requestFocus();
            return;
        }
        if(Password.length()<8)
        {
            SignUpPasswordText.setError("Enter atleast 8 character");
            SignUpPasswordText.requestFocus();
            return;

        }
        if(!(Password.equals(RetypePass)))
        {
            RetypePasswordText.setError("Password is not matched");
            RetypePasswordText.requestFocus();
            return;
        }


        SignUpProgress.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {



                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            SignUpProgress.setVisibility(View.GONE);
                            String key = databaseReference.push().getKey();
                            Client client=new Client(FullName,Phone);

                           // databaseReference.child(key).setValue(client);

                            Toast.makeText(getApplicationContext(),"SignUp is Successful",Toast.LENGTH_SHORT).show();
                            ClearSignUp();


                        }
                        else {
                            if(task.getException()instanceof FirebaseAuthUserCollisionException)
                            {
                                SignUpProgress.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"User is already loggedIn",Toast.LENGTH_SHORT).show();

                            }
                            else {
                                SignUpProgress.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }


                    }

                    private void ClearSignUp() {

                        SignUpNameText.setText("");
                        SignUpEmailText.setText("");
                        SignUpPasswordText.setText("");
                        SignUpPhoneNumberText.setText("");
                        RetypePasswordText.setText("");



                    }


                });




    }


}
