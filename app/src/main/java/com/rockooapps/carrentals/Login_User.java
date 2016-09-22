package com.rockooapps.carrentals;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_User extends Activity {
    SharedPreferences sp;
    SharedPreferences.Editor SPEditor;
    boolean saveLogin;
    Registration_userDb DB = new Registration_userDb(this);
    Registration_user db = new Registration_user();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__user);


//Fields Declaration
        final EditText user = (EditText)findViewById(R.id.Username_editText);
        final EditText pass = (EditText)findViewById(R.id.Password_editText);
        final Button log = (Button)findViewById(R.id.LoginButton);
        final CheckBox rem = (CheckBox)findViewById(R.id.RememberMe);
        TextView sign = (TextView)findViewById(R.id.SignupText);
        TextView adminsign = (TextView)findViewById(R.id.AdminLogText);

//Creating SharedPreferences
        sp = getSharedPreferences("LoginActivity", MODE_PRIVATE);
        SPEditor = sp.edit();
        saveLogin = sp.getBoolean("SaveLogin", false);
//Checking checkbox status
        if(saveLogin){
            user.setText(sp.getString("user", "Plz enter username"));
            pass.setText(sp.getString("pass", "Plz enter username"));
            rem.setChecked(true);
        }else{
            user.setText("");
            pass.setText("");
            rem.setChecked(false);
        }
//hyperlink text for admin login page
        adminsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_User.this, Login_Admin.class);
                startActivity(i);
            }
        });
//hyperlink text for registration form for new users
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_User.this, Registration_user.class);
                startActivity(i);
            }
        });

//once details are verified the login button is clicked
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(Login_User.this, R.anim.bounce);
                log.startAnimation(myAnim);
                BounceInterpolator interpolator = new BounceInterpolator(0.1, 40);
                myAnim.setInterpolator(interpolator);
                log.startAnimation(myAnim);
                String username = user.getText().toString();
                String password = pass.getText().toString();
                boolean checked = false;

                if (!username.isEmpty()&&!password.isEmpty()) {
                    if (rem.isChecked()) {
                        checked = true;
                        SPEditor.putBoolean("SaveLogin", checked);
                    }
                    SPEditor.putString("user", username);
                    SPEditor.putString("pass", password);
                    SPEditor.putBoolean("SaveLogin", checked);
                    SPEditor.commit();

                    boolean validLogin = validateLogin(username, password, Login_User.this);
                    if(validLogin)
                    {
                        Intent in = new Intent(Login_User.this, User_CarDetails.class);
                        in.putExtra("UserName", user.getText().toString());
                        startActivity(in);
                        finish();
                    }
                }else{
                    Toast.makeText(Login_User.this, "Field not filled", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public boolean validateLogin(String username, String password, Context baseContext)
    {
        DB = new Registration_userDb(Login_User.this);
        SQLiteDatabase db = DB.getReadableDatabase();

        String[] columns = {"_id"};
        String selection = "username=? AND password=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = null;
        try{
            cursor = db.query(Registration_userDb.DATABASE_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            startManagingCursor(cursor);
        }
        catch(Exception e)

        {
            e.printStackTrace();
        }
        int numberOfRows = cursor.getCount();

        if(numberOfRows == 0)
        {
            Toast.makeText(getApplicationContext(), "User Name and Password miss match..\nPlease Try Again", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Login_User.this, Login_User.class);
            startActivity(intent);
            finish();
            return false;
        }
        return true;

    }

    }

