package com.example.roadinvento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class dashboard extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView uname,phone;
    dataBaseStore mydb;
    EditText  RoadName, Latitude_Longitude_StartPoint, Latitude_Longitude_EndPoint,AreaName, DistrictName, StateName, LandMark, PavementType;

    Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Spinner spinner = findViewById(R.id.ChainageSetting);
        Latitude_Longitude_StartPoint = (EditText) findViewById(R.id.Latitude_Longitude_StartPoint);
        Latitude_Longitude_EndPoint = (EditText) findViewById(R.id.Latitude_Longitude_EndPoint);
        RoadName = (EditText) findViewById(R.id.RoadName);
        AreaName = (EditText) findViewById(R.id.AreaName);
        DistrictName = (EditText) findViewById(R.id. DistrictName);
        StateName = (EditText) findViewById(R.id.StateName);
        LandMark = (EditText) findViewById(R.id.LandMark);
        PavementType = (EditText) findViewById(R.id.PavementType);
        next= (Button) findViewById(R.id.next);
        mydb = new dataBaseStore(this);






        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Chainage, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        mydb = new dataBaseStore(this);
        uname = (TextView) findViewById(R.id.uname);
        Intent intent = getIntent();
        String pid = intent.getStringExtra("message");
        /*uname.setText(pid);*/
        viewdata(pid);
        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vl();
                        Toast.makeText(dashboard.this,"DataSaved",Toast.LENGTH_SHORT).show();
                        boolean isInserted = mydb.inforData(
                                RoadName.getText().toString(),
                                Latitude_Longitude_StartPoint.getText().toString(),
                                Latitude_Longitude_EndPoint.getText().toString(),
                                AreaName.getText().toString(),
                                DistrictName.getText().toString(),
                                StateName.getText().toString(),
                                LandMark.getText().toString(),
                                PavementType.getText().toString()

                        );
                        Intent i;
                        i = new Intent(getApplicationContext(),details.class);
                        startActivity(i);
                    }
                }
        );
    }
    private void vl() {
        if(TextUtils.isEmpty( RoadName.getText().toString().trim()) ||  TextUtils.isEmpty(Latitude_Longitude_StartPoint.getText().toString().trim()) || TextUtils.isEmpty(Latitude_Longitude_EndPoint.getText().toString().trim()) ||
                TextUtils.isEmpty( AreaName.getText().toString().trim()) || TextUtils.isEmpty( DistrictName.getText().toString().trim()) || TextUtils.isEmpty( StateName.getText().toString().trim()) || TextUtils.isEmpty( LandMark.getText().toString().trim()) ||
                TextUtils.isEmpty( PavementType.getText().toString().trim()))
        {

            RoadName.setError("PhoneNumber Can't be empty");
            Latitude_Longitude_StartPoint.setError("UserName Can't be empty");
            Latitude_Longitude_EndPoint.setError("Password Can't be empty");
            AreaName.setError("Password Can't be empty");
            DistrictName.setError("Password Can't be empty");
            StateName.setError("Password Can't be empty");
            LandMark.setError("Password Can't be empty");
            PavementType.setError("Password Can't be empty");

        }


        else
        {
            Toast.makeText(dashboard.this,"dataupaded",Toast.LENGTH_SHORT).show();

            Intent i;
            i = new Intent(getApplicationContext(),details.class);
            startActivity(i);

        }
    }
    public  void viewdata(String pid) {
        Cursor res = mydb.getAlldata(pid);
        if (res.getCount() == 0) {
            showMessage("error", "nothingf");
            return;
        }


        while (res.moveToNext()) {

            uname.append(res.getString(1));
        }



    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder b= new AlertDialog.Builder(this);
        b.setCancelable(true);
        b.setTitle(title);
        b.setTitle(Message);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
