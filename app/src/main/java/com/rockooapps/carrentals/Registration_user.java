package com.rockooapps.carrentals;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration_user extends Activity {
     EditText name;
    private EditText Rphone;
    private EditText Ruser;
    private EditText Rpass;
    private Button regis;

    protected Registration_userDb DB = new Registration_userDb(Registration_user.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_user);

        name = (EditText)findViewById(R.id.RName);
        Rphone = (EditText)findViewById(R.id.RPhone);
        Ruser = (EditText)findViewById(R.id.RUserName);
        Rpass = (EditText)findViewById(R.id.RPassword);
        regis = (Button) findViewById(R.id.RegisterButton);

    }

    public void onRegister(View view) {
        //button animation
        final Animation myAnim = AnimationUtils.loadAnimation(Registration_user.this, R.anim.bounce);
        regis.startAnimation(myAnim);
        BounceInterpolator interpolator = new BounceInterpolator(0.1, 40);
        myAnim.setInterpolator(interpolator);
        regis.startAnimation(myAnim);

        String nam = name.getText().toString();
        String phn = Rphone.getText().toString();
        String use = Ruser.getText().toString();
        String pas = Rpass.getText().toString();

        boolean invalid = false;

        if (nam.equals("")) {
            invalid = true;
            Toast.makeText(Registration_user.this, "PLease enter Name", Toast.LENGTH_LONG).show();
        } else if (phn.equals("")) {
            invalid = true;
            Toast.makeText(Registration_user.this, "PLease enter MailID", Toast.LENGTH_LONG).show();
        } else if (use.equals("")) {
            invalid = true;
            Toast.makeText(Registration_user.this, "PLease enter Username", Toast.LENGTH_LONG).show();
        } else if (pas.equals("")) {
            invalid = true;
            Toast.makeText(Registration_user.this, "PLease enter Password", Toast.LENGTH_LONG).show();
        } else {
            //if all the fields are enter the details are stored in database
            Toast.makeText(Registration_user.this, "Registration Successful", Toast.LENGTH_LONG).show();
            DB.entervalues(nam, phn, use, pas);
            Intent i = new Intent(Registration_user.this, Login_User.class);
            startActivity(i);
            finish();

        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        DB.close();
    }


}
