package com.rockooapps.carrentals;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
//Displays the list of car items for the user to select from

public class User_CarDetails extends AppCompatActivity {

    private ArrayList<CarElements> carElementses;
    private CarImageAdapter imageAdapter;
    private ListView listView;
    DBCar_Details dbCar_details = new DBCar_Details(this);
    private Uri mCapturedImageURI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__car_details);

        listView = (ListView) findViewById(R.id.CarsListView);
        carElementses = new ArrayList<>();
        imageAdapter = new CarImageAdapter(this, carElementses);
        listView.setAdapter(imageAdapter);
        initDB();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                CarElements cars = (CarElements) listView.getItemAtPosition(position);
                Intent intent = new Intent(User_CarDetails.this, BookingDatesPage.class);
                intent.putExtra("IMAGE", (new Gson()).toJson(cars));
                startActivity(intent);
                finish();
            }

        });
    }

    private void initDB() {
        dbCar_details = new DBCar_Details(this);
        //        add images from database to images ArrayList
        for (CarElements mi : dbCar_details.getImages()) {
            carElementses.add(mi);
        }
    }
    @Override protected void onSaveInstanceState(Bundle outState) {
        // Saves the user's current state
        if (mCapturedImageURI != null) {
            outState.putString("mCapturedImageURI", mCapturedImageURI.toString());
        }
        // calls the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }

    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // calls the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restores state members from saved instance
        if (savedInstanceState.containsKey("mCapturedImageURI")) {
            mCapturedImageURI = Uri.parse(savedInstanceState.getString("mCapturedImageURI"));
        }
    }
}

