package com.example.roadinvento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class dataBaseStore extends SQLiteOpenHelper {
    public static final String RIDB_Name = "RoadInvento.db";
    public static final String TREG_Name = "Registration";
    public static final String R1= "PhoneNumber";
    public static final String R2 = "UserName";
    public static final String R3 = "Password";
    public static final String TINFO_Name = "Information";
    public static final String I1 = "RoadName";
    public static final String I2 = "Latitude_Longitude_StartPoint";
    public static final String I3 = "Latitude_Longitude_EndPoint";
    public static final String I4 = "AreaName";
    public static final String I5 = "DistrictName";
    public static final String I6 = "StateName";
    public static final String I7 = "LandMark";
    public static final String I8 = "ChainageSetting";
    public static final String I9 = "PavementType";


    public dataBaseStore(Context context) {
        super(context, RIDB_Name, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Registration( PhoneNumber TEXT PRIMARY KEY,UserName TEXT,Password TEXT)");
        db.execSQL("CREATE TABLE Information( RoadName TEXT PRIMARY KEY,Latitude_Longitude_StartPoint INTEGER,Latitude_Longitude_EndPoint TEXT ,AreaName TEXT,DistrictName TEXT ,StateName TEXT ,LandMark TEXT  ,PavementType TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Registration");
        db.execSQL("DROP TABLE IF EXISTS Information");
        onCreate(db);

    }

    public boolean insertData( String UserName, String PhoneNumber,String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(R1, PhoneNumber);
        contentValues.put(R2,UserName);
        contentValues.put(R3, Password);
        long result = db.insert(TREG_Name, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean CheckPhone(String PhoneNumber) {
        Log.v("CheckPhone", PhoneNumber);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from Registration where PhoneNumber=? ", new String[]{PhoneNumber});
        if (cs.getCount() > 0)
            return true;
        else
            return false;

    }


    public boolean validate(String PhoneNumber, String Pwd) {
        Log.v("validate", PhoneNumber);
        Log.v("validate", Pwd);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from Registration where PhoneNumber=? and Password=?", new String[]{PhoneNumber,Pwd});
        if (cs.getCount() > 0)
            return true;
        else
            return false;

    }

    public boolean inforData( String RoadName, String  Latitude_Longitude_StartPoint, String Latitude_Longitude_EndPoint,String AreaName, String DistrictName, String StateName, String LandMark, String PavementType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(I1, RoadName);
        contentValues.put(I2, Latitude_Longitude_StartPoint);
        contentValues.put(I3, Latitude_Longitude_EndPoint);
        contentValues.put(I4, AreaName);
        contentValues.put(I5, DistrictName);
        contentValues.put(I6, StateName);
        contentValues.put(I7, LandMark);
        contentValues.put(I9, PavementType);
        long result = db.insert(TINFO_Name, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAlldata(String PhoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Registration where PhoneNumber= ?", new String[]{PhoneNumber});
        return res;
    }
    public boolean infoData( String RoadName, String  Latitude_Longitude_StartPoint, String Latitude_Longitude_EndPoint,String AreaName, String DistrictName, String StateName, String LandMark, String ChainageSetting, String PavementType)
    {

        return false;
    }


}


