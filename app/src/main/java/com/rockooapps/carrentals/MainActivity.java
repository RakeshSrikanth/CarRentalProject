package com.rockooapps.carrentals;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<CarElements> carElementses;
    private CarImageAdapter imageAdapter;
    private ListView listView;
    DBCar_Details dbCar_details = new DBCar_Details(this);
    Button regusers, adcars;
    private Uri mCapturedImageURI;
    TextView vi2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adcars = (Button) findViewById(R.id.AddCarsButton);
        listView = (ListView) findViewById(R.id.CarsList);
        carElementses = new ArrayList<>();
        imageAdapter = new CarImageAdapter(this, carElementses);


        listView.setAdapter(imageAdapter);
        initDB();

        adcars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Admin_AddCars.class);
                startActivity(i);
                finish();
            }
        });

        regusers = (Button) findViewById(R.id.RegisteredusersButton);
        regusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                regusers.startAnimation(myAnim);
                BounceInterpolator interpolator = new BounceInterpolator(0.1, 40);
                myAnim.setInterpolator(interpolator);
                regusers.startAnimation(myAnim);
                Intent i = new Intent(MainActivity.this, RegisteredUsers_Admin.class);
                startActivity(i);
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
                builderSingle.setTitle("Select One Option");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_activated_1);
                arrayAdapter.add("Delete");
                builderSingle.setNegativeButton(
                        "Back",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builderSingle.setAdapter(arrayAdapter,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String strName = arrayAdapter.getItem(which);
                                vi2=(TextView)view.findViewById(R.id.NumberText);
                                if(strName.equals("Delete")){
                                    dialog.dismiss();
                                }
                            }
                        });
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
        // Saves the user's current game state
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
