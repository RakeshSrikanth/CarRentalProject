package com.rockooapps.carrentals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rakesh on 7/14/2016.
 */
public class DBCar_Details extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "Cars";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_ID = "_id";

    public static final String DATABASE_TABLE_NAME = "CarDetails";
    public DBCar_Details(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creates database CarDetails
        String query = "CREATE TABLE CarDetails (_id INTEGER PRIMARY KEY AUTOINCREMENT, CarImage TEXT, CarName TEXT, CarType TEXT,  CarModel TEXT, Makers TEXT, Priceperhour INTEGER )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DELETE TABLE CarDetails ");
        onCreate(db);

    }

    public void entercardetails(CarElements car) {

        //writes car details into database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CarImage", car.getPath());
        cv.put("CarName", car.getName());
        cv.put("CarType", car.getType());
        cv.put("CarModel", car.getModel());
        cv.put("Makers", car.getMakers());
        cv.put("Priceperhour", car.getPrice());
        db.insert("CarDetails", null, cv);

    }


    public List<CarElements> getImages() {
        List<CarElements> mycars = new ArrayList<>();
        Cursor cursor =getReadableDatabase().rawQuery("SELECT * FROM CarDetails",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CarElements elements = cursorToMyImage(cursor);
            mycars.add(elements);
            cursor.moveToNext();
        }
        cursor.close();
        return mycars;
    }

    private CarElements cursorToMyImage(Cursor cursor) {

        //Reads car details from database
        db = this.getReadableDatabase();
        CarElements cars = new CarElements();
        cars.setPath(cursor.getString(
                cursor.getColumnIndex("CarImage")));
        cars.setName(cursor.getString(
                cursor.getColumnIndex("CarName")));
        cars.setPrice(cursor.getString(
                cursor.getColumnIndex("Priceperhour")));
        cars.setMakers(cursor.getString(
                cursor.getColumnIndex("Makers")));
        cars.setModel(cursor.getString(
                cursor.getColumnIndex("CarModel")));
        cars.setType(cursor.getString(
                cursor.getColumnIndex("CarType")));
        return cars;

    }


}
