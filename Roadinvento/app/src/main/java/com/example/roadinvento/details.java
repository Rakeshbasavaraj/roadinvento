package com.example.roadinvento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class details extends AppCompatActivity {
    TextView t1;
    dataBaseStore mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        t1= (TextView) findViewById(R.id.t1);
        mydb = new dataBaseStore(this);
        Cursor c = mydb.Alldata();

        if(c.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_SHORT).show();
        }else
            while ( c.moveToNext())

                Toast.makeText(getApplicationContext(),"roadname is : "+c.getString(7),Toast.LENGTH_SHORT).show();

    }


}