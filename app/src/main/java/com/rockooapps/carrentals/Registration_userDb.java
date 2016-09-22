package com.rockooapps.carrentals;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rakesh on 7/12/2016.
 */
public class Registration_userDb extends SQLiteOpenHelper {


    private SQLiteDatabase db;
    Registration_userDb DB = null;
    private static final String DATABASE_NAME = "Registration";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE_NAME = "Registrationform";


    public Registration_userDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creates table Registration form
        String query = "CREATE TABLE Registrationform (_id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, phone INTEGER, username TEXT, password TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DELETE TABLE Registrationform ");
        onCreate(db);

    }

    public void entervalues(String name, String phone, String username, String password) {

        //enters value into the database when called
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("firstname", name);
        cv.put("phone", phone);
        cv.put("username", username);
        cv.put("password", password);
        db.insert("Registrationform", null, cv);
    }

    public ArrayList<HashMap<String,String>> get_all_names() {

        //gets value from database when called
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = " SELECT * FROM Registrationform";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("getID",c.getString(0));
                map.put("getName", c.getString(1));
                map.put("getNumber", c.getString(2));
                list.add(map);

            }while (c.moveToNext());

        }
        return list;
    }

}