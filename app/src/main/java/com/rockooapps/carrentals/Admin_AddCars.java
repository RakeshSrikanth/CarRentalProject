package com.rockooapps.carrentals;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Admin_AddCars extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    int MY_PERMISSIONS_REQUEST_READ_AND_WRITE_EXTERNAL_STORAGE;
    EditText nam, mod, mak, pri,carnam;
    Button adcar;
    DBCar_Details DB = new DBCar_Details(this);
    CarImageAdapter imageAdapter;
    ImageView im;
    ArrayList<CarElements> caritems;
    CarElements carElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add_cars);

        carnam = (EditText) findViewById(R.id.Carname);
        nam = (EditText) findViewById(R.id.CarType);
        mod = (EditText) findViewById(R.id.CarModel);
        mak = (EditText) findViewById(R.id.CarMaker);
        pri = (EditText) findViewById(R.id.CarPrice);
        im = (ImageView) findViewById(R.id.CarImage);
        caritems = new ArrayList<>();
        carElements = new CarElements();
        imageAdapter = new CarImageAdapter(this, caritems);

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Checking permission
                if ((ContextCompat.checkSelfPermission(Admin_AddCars.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                        || (ContextCompat.checkSelfPermission(Admin_AddCars.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))

                {
                    ActivityCompat.requestPermissions
                            (Admin_AddCars.this, new String[]{
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                            }, MY_PERMISSIONS_REQUEST_READ_AND_WRITE_EXTERNAL_STORAGE);
                }

                //Starting intent activity to get gallery images
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PICTURE);
                Bitmap bmp = BitmapFactory.decodeFile(String.valueOf((SELECT_PICTURE)));
                im.setImageBitmap(bmp);

            }
        });

        adcar = (Button) findViewById(R.id.AddCarbutton);
        adcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Starts button animation
                final Animation myAnim = AnimationUtils.loadAnimation(Admin_AddCars.this, R.anim.bounce);
                adcar.startAnimation(myAnim);
                BounceInterpolator interpolator = new BounceInterpolator(0.1, 40);
                myAnim.setInterpolator(interpolator);
                adcar.startAnimation(myAnim);

                //Checks if all the fields are filled
                boolean invalid = false;
                if (im.equals("")) {
                    invalid = true;
                    Toast.makeText(Admin_AddCars.this, "PLease insert image", Toast.LENGTH_LONG).show();
                } else if (carnam.equals("")) {
                    invalid = true;
                    Toast.makeText(Admin_AddCars.this, "PLease enter Name", Toast.LENGTH_LONG).show();
                } else if (nam.equals("")) {
                    invalid = true;
                    Toast.makeText(Admin_AddCars.this, "PLease enter Type", Toast.LENGTH_LONG).show();
                } else if (mod.equals("")) {
                    invalid = true;
                    Toast.makeText(Admin_AddCars.this, "PLease enter Model", Toast.LENGTH_LONG).show();
                } else if (mak.equals("")) {
                    invalid = true;
                    Toast.makeText(Admin_AddCars.this, "PLease enter Maker", Toast.LENGTH_LONG).show();
                } else if (pri.equals("")) {
                    invalid = true;
                    Toast.makeText(Admin_AddCars.this, "PLease enter Price", Toast.LENGTH_LONG).show();
                } else {
                    String carname = carnam.getText().toString();
                    String type = nam.getText().toString();
                    String model = mod.getText().toString();
                    String maker = mak.getText().toString();
                    String price = pri.getText().toString();

                    //Stores the details in the database
                    carElements.setName(carname);
                    carElements.setType(type);
                    carElements.setModel(model);
                    carElements.setMakers(maker);
                    carElements.setPrice(price);
                    DB.entercardetails(carElements);

                    Toast.makeText(Admin_AddCars.this, "Information registered", Toast.LENGTH_LONG).show();
                    imageAdapter.add(carElements);
                    Intent i = new Intent(Admin_AddCars.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });

    }

   public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == RESULT_OK) {
                if (requestCode == SELECT_PICTURE) {
                    Uri selectedImageUri = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                    String selectedImagePath = cursor.getString(columnIndex);
                    System.out.println("Image Path : " + selectedImagePath);
                    im.setImageURI(selectedImageUri);
                    carElements.setPath(selectedImagePath);

                }
            }
        }





}
