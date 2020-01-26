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
                        String Phone=PhoneNumber.getText().toString();
                        String pas=pwd.getText().toString();
                        boolean validate = mydb.validate(Phone,pas);
                        if(validate==true) {
                            Toast.makeText(login.this, "login Sucessfully", Toast.LENGTH_SHORT).show();
                            String str = PhoneNumber.getText().toString();
                            Intent i = new Intent(getApplicationContext(), dashboard.class);
                            i.putExtra("message", str);


                            startActivity(i);
                        } else
                            Toast.makeText(login.this,"Please enter correct details",Toast.LENGTH_SHORT).show();


                    }
                }
        );

    }
}




