package com.rockooapps.carrentals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Login_Admin extends AppCompatActivity {

    EditText aduser, adpass;
    Button log, chg;
    SharedPreferences sp;
    SharedPreferences.Editor SPEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__admin);


        aduser = (EditText)findViewById(R.id.Adminname_editText);
        adpass = (EditText)findViewById(R.id.AdminPassword_editText);
        log = (Button)findViewById(R.id.AdminLoginButton);
        chg = (Button)findViewById(R.id.ChagnePasButton);


        //setting default user name and password as keyvalues
        sp = getSharedPreferences("LoginActivity", MODE_PRIVATE);
        SPEditor = sp.edit();
        SPEditor.putString("User", "Admin");
        SPEditor.putString("Pass", "Admin");
        SPEditor.apply();

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //button animation
                final Animation myAnim = AnimationUtils.loadAnimation(Login_Admin.this, R.anim.bounce);
                log.startAnimation(myAnim);
                BounceInterpolator interpolator = new BounceInterpolator(0.1, 40);
                myAnim.setInterpolator(interpolator);
                log.startAnimation(myAnim);

                String username = aduser.getText().toString();
                String password = adpass.getText().toString();

                //checks the field entry
                if(username.isEmpty()&&password.isEmpty()){
                    Toast.makeText(Login_Admin.this, "Invalid Entry... Retry", Toast.LENGTH_SHORT).show();

                    //checks key value for the password
                }else if(password.equals(sp.getString("Pass", ""))){
                    Intent i = new Intent (Login_Admin.this, MainActivity.class  );
                    Toast.makeText(Login_Admin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(Login_Admin.this, "FailedLogin", Toast.LENGTH_SHORT).show();
                }

            }
        });

        chg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent activity for changing password
                final Animation myAnim1 = AnimationUtils.loadAnimation(Login_Admin.this, R.anim.bounce);
                chg.startAnimation(myAnim1);
                BounceInterpolator interpolator1 = new BounceInterpolator(0.2, 20);
                myAnim1.setInterpolator(interpolator1);
                chg.startAnimation(myAnim1);
                Intent i = new Intent(Login_Admin.this, AdminPass_Change.class);
                i.putExtra("Pass",adpass.getText().toString() );
                startActivity(i);
            }
        });
    }
}
