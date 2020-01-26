package com.example.roadinvento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class login extends AppCompatActivity {
    Button sigin;
    EditText PhoneNumber, pwd;
    dataBaseStore mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PhoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        pwd = (EditText) findViewById(R.id.pwd);
        mydb = new dataBaseStore(this);

        TextView signUp_text = findViewById(R.id.signUp_text);
        sigin = findViewById(R.id.sigin);
        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, siginUp.class));
                finish();
            }
        });
        sigin.setOnClickListener(
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
            onSignUpFailed(false);
            return;
        }

        sigin.setEnabled(false);
        String Phone=PhoneNumber.getText().toString();
        String pas=pwd.getText().toString();
        boolean val = mydb.validate(Phone,pas);
        if(val==true)
        {
            onSignupSuccess(val);
        }
        else
        {
            onSignUpFailed(val);

        }
    }

    private void onSignupSuccess(boolean val) {
        Toast.makeText(login.this, "login Sucessfully", Toast.LENGTH_SHORT).show();
        String str = PhoneNumber.getText().toString();
        Intent i = new Intent(getApplicationContext(), dashboard.class);
        i.putExtra("message", str);
        startActivity(i);
        finish();
    }

    private void onSignUpFailed(boolean wrongCreds) {
        if(wrongCreds){
            Toast.makeText(getBaseContext(), "Invalid Email or Password", Toast.LENGTH_LONG).show();
        }


        sigin.setEnabled(true);

    }

    private boolean validate() {




        boolean valid = true;

        String Phone=PhoneNumber.getText().toString();
        String pas=pwd.getText().toString();


        if (pas.isEmpty() || pas.length() < 4 || pas.length() > 10) {
            pwd.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            pwd.setError("invalid");
        }





        if (Phone.isEmpty() || Phone.length()!=10) {
            PhoneNumber.setError("Enter Valid Mobile Number");
            valid = false;
        }

         else PhoneNumber.setError(null);

        return valid;

    }
}




