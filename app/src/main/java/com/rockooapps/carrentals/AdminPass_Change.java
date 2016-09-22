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

public class AdminPass_Change extends AppCompatActivity {

    EditText old, newpass, connew;
    Button set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pass__change);

        old = (EditText)findViewById(R.id.CurrentPass_Edittext);
        newpass = (EditText)findViewById(R.id.NewPass_EditText);
        connew = (EditText)findViewById(R.id.ConfirmPass_EditText);
        set = (Button) findViewById(R.id.ChangeAdminPassButton);

        //gets the keyvalue of password
        final SharedPreferences sp = getSharedPreferences("LoginActivity", MODE_PRIVATE);
        final String pas = sp.getString("Pass", "");

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(AdminPass_Change.this, R.anim.bounce);
                set.startAnimation(myAnim);
                BounceInterpolator interpolator = new BounceInterpolator(0.1, 40);
                myAnim.setInterpolator(interpolator);
                set.startAnimation(myAnim);

                String nP=  newpass.getText().toString();
                String CnP= connew.getText().toString();
                String Opa = old.getText().toString();

                //checks if old password matches for change of new
                //checks if new password is type correctly twice
                if((Opa.equals(pas))&&(nP.equals(CnP))){
                    SharedPreferences.Editor SPEditor = sp.edit();
                    SPEditor.putString("Pass", CnP);
                    SPEditor.apply();
                    Toast.makeText(AdminPass_Change.this, "Password Changed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AdminPass_Change.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
