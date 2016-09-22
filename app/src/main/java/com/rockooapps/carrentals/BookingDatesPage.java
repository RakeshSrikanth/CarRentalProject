package com.rockooapps.carrentals;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;
import java.util.SimpleTimeZone;

public class BookingDatesPage extends AppCompatActivity implements DialogInterface.OnClickListener, DatePickerDialog.OnDateSetListener {

    EditText FDate, TDate, FTime, TTime;
    Button conBook, canBook;

    private int mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_dates_page);

        //Edit text for from time, to time, from date and to date
        FDate = (EditText) findViewById(R.id.EnterDate);
        TDate = (EditText) findViewById(R.id.EnterToDate);
        FTime = (EditText) findViewById(R.id.FromTime);
        TTime = (EditText) findViewById(R.id.ToTime);

        //confirm booking and cancel booking
        conBook = (Button)findViewById(R.id.ConfirmBookingButton);
        canBook = (Button)findViewById(R.id.BookCancelButton);

        //From time
        FTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                {
                    int min = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    TimePickerDialog tpd = new TimePickerDialog(BookingDatesPage.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker args, int h, int m) {
                            FTime.setText(h + ":" + m);
                        }
                    }, hour, min, true);
                    tpd.show();
                }

            }
        });

        //To time
        TTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                {
                    int min = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);

                    TimePickerDialog tpd = new TimePickerDialog(BookingDatesPage.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker args, int h, int m) {
                            TTime.setText(h + ":" + m);
                        }
                    }, hour, min, true);
                    tpd.show();
                }
            }
        });

        //confirm booking
        conBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(BookingDatesPage.this, R.anim.bounce);
                conBook.startAnimation(myAnim);
                BounceInterpolator interpolator = new BounceInterpolator(0.1, 40);
                myAnim.setInterpolator(interpolator);
                conBook.startAnimation(myAnim);
                if ((!FDate.getText().toString().isEmpty())&&(!TDate.getText().toString().isEmpty())&&
                        (!FTime.getText().toString().isEmpty())&&(!TTime.getText().toString().isEmpty())){
                    Toast.makeText(BookingDatesPage.this, "Booking confirmed", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BookingDatesPage.this,User_CarDetails.class );
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(BookingDatesPage.this, "Select date and time to book", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //cancel booking
        canBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(BookingDatesPage.this, R.anim.bounce);
                canBook.startAnimation(myAnim);
                BounceInterpolator interpolator = new BounceInterpolator(0.1, 40);
                myAnim.setInterpolator(interpolator);
                canBook.startAnimation(myAnim);
                Intent i = new Intent(BookingDatesPage.this,User_CarDetails.class );
                startActivity(i);
                finish();
            }
        });

        //For From Date
        FDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BookingDatesPage.this, fromdate, myfromCalendar
                        .get(Calendar.YEAR), myfromCalendar.get(Calendar.MONTH),
                        myfromCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //For To Date
        TDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BookingDatesPage.this, todate, mytoCalendar
                        .get(Calendar.YEAR), mytoCalendar.get(Calendar.MONTH),
                        mytoCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

//DatePickDialogue ---- From
    Calendar myfromCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener fromdate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myfromCalendar.set(Calendar.YEAR, year);
            myfromCalendar.set(Calendar.MONTH, monthOfYear);
            myfromCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy"; //In which format you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            FDate.setText(sdf.format(myfromCalendar.getTime()));
        }
    };


//DatePickDialogue ---- To
    Calendar mytoCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener todate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mytoCalendar.set(Calendar.YEAR, year);
            mytoCalendar.set(Calendar.MONTH, monthOfYear);
            mytoCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy"; //In which you need put here

            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            TDate.setText(sdf.format(mytoCalendar.getTime()));
        }
    };


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }


}
