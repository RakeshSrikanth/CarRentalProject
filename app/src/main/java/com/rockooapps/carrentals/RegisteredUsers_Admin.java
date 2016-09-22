package com.rockooapps.carrentals;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisteredUsers_Admin extends AppCompatActivity {
    ListView listView;
    Registration_userDb db = new Registration_userDb(RegisteredUsers_Admin.this);
    ArrayList<HashMap<String,String>> list;
    EditText in1, in2;
    TextView vi2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_users__admin);

        in1 = (EditText)findViewById(R.id.RName);
        in2 = (EditText)findViewById(R.id.RPhone);
        list = db.get_all_names();
        listView = (ListView)findViewById(R.id.RegisteredUsersList);

        //setting adapter to populate with name and number
        SimpleAdapter simple = new SimpleAdapter(RegisteredUsers_Admin.this, list, R.layout.registered_user_custom_list, new String[]{"getName", "getNumber"}, new int[]{R.id.NameText, R.id.NumberText});
        listView.setAdapter(simple);

        //displays alert dialogue for call and message option for the admin
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(RegisteredUsers_Admin.this);
                builderSingle.setTitle("Select One Option");
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        RegisteredUsers_Admin.this,
                        android.R.layout.simple_list_item_activated_1);
                arrayAdapter.add("Call");
                arrayAdapter.add("Message");
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

                                if(strName.equals("Call")){
                                    Log.d("CALL STRING", vi2.getText().toString());
                                    Intent intent = new Intent(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:"+ vi2.getText().toString()));
                                    startActivity(intent);
                                }else if (strName.equals("Message")){
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", vi2.getText().toString(), null)));
                                }
                            }
                        });
                builderSingle.show();
                return false;
            }
        });
    }
    }

