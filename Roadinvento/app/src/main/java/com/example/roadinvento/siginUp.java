package com.example.roadinvento;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;


public class siginUp extends AppCompatActivity {
    Button SignUp;
    dataBaseStore mydb;
    EditText UserName, PhoneNumber, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sigin_up);
        TextView signIn_text = findViewById(R.id.signIn_text);
        SignUp = findViewById(R.id.SignUp);
        UserName = (EditText) findViewById(R.id.UserName);
        PhoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        Password = (EditText) findViewById(R.id.Password);
        mydb = new dataBaseStore(this);
        signIn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(siginUp.this, login.class));
                finish();
            }
        });
        SignUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check();
                    }
                }
        );

    }

    private void check() {


        if (!validate()) {
            onSignUpFailed();
            return;
        }


        SignUp.setEnabled(false);
        String name = UserName.getText().toString();
        String mobile = PhoneNumber.getText().toString();
        String password = Password.getText().toString();
        boolean isInserted = mydb.insertData(
                UserName.getText().toString(),
                PhoneNumber.getText().toString(),
                Password.getText().toString());
        if(isInserted== true) {
            onSignupSuccess(isInserted);
        }
        else
        {

            onSignUpFailed();

        }

    }

    private void onSignupSuccess(boolean isInserted) {




        Toast.makeText(getBaseContext(), "Registration successful", Toast.LENGTH_LONG).show();

        Intent i = new Intent(getApplicationContext(), login.class);
        startActivity(i);
        finish();
    }



    private void onSignUpFailed () {
        Toast.makeText(siginUp.this,"Registred failed",Toast.LENGTH_SHORT).show();
    }
    private boolean validate() {
        boolean valid = true;

        String name = UserName.getText().toString();
        String mobile = PhoneNumber.getText().toString();
        String password = Password.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            UserName.setError("at least 3 characters");
            valid = false;
        } else {
            UserName.setError(null);
        }





        if (mobile.isEmpty() || mobile.length()!=10) {
            PhoneNumber.setError("Enter Valid Mobile Number");
            valid = false;
        } else if(mydb.CheckPhone(mobile)){
            PhoneNumber.setError("Mobile already taken");
            valid = false;
        } else PhoneNumber.setError(null);



        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            Password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            Password.setError(null);
        }

        return valid;
    }
}
