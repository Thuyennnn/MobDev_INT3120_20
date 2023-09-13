package com.mob.datetime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends Activity {

    TextView labelDate;
    Button btnSetDateTime;
    Button btnSetTime;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        labelDate = (TextView) findViewById(R.id.labelDate);
        btnSetDateTime = (Button) findViewById(R.id.setDateTime);
        btnSetTime = (Button) findViewById(R.id.setTime);

        btnSetDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimePicker();
            }
        });
    }

    private void openDatePicker() {
        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                labelDate.setText(String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day));
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, d,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void openTimePicker() {
        TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hour);
                myCalendar.set(Calendar.MINUTE, minute);
                labelDate.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, t,
                myCalendar.get(Calendar.HOUR_OF_DAY),
                myCalendar.get(Calendar.MINUTE), true);

        timePickerDialog.show();
    }

}