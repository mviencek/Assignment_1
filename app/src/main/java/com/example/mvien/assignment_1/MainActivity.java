package com.example.mvien.assignment_1;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.DatePickerDialog;
import android.app.Dialog;
import java.util.Date;
import java.util.Calendar;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button secondActivityBtn;
    private EditText editText;
    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText ageEditText;
    private EditText ageText;

    private Calendar calendar;
    private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        ageText = findViewById(R.id.ageText);
        secondActivityBtn = findViewById(R.id.secondActivityBtn);
        secondActivityBtn.setEnabled(false);
        ageEditText.setFocusable(false);
        ageText.setFocusable(false);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, month, day, year);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        //put bithdate in field
       ageEditText.setText(new StringBuilder().append(month+1).append("/")
               .append(day).append("/").append(year));

        //calculate age and place age in field
        int years = Calendar.getInstance().get(Calendar.YEAR);
        int months = Calendar.getInstance().get(Calendar.MONTH);
        int days =  Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Date now = new Date(year,month,day);
        Date then = new Date(years, months, days);
        int year1 = then.getYear();
        int year2 = now.getYear();
        int age = year1 - year2;
        int month1 = then.getMonth();
        int month2 = now.getMonth();
        if (month2 > month1) {
            age--;
        } else if (month1 == month2) {
            int day1 = then.getDay();
            int day2 = now.getDay();
            if (day2 > day1) {
                age--;
            }
        }
        ageText.setText(String.valueOf(age));
        //if client is over 18 enable button
        if(age>=18){
            secondActivityBtn.setEnabled(true);
        }
        else
        {
            secondActivityBtn.setEnabled(false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if display image is selected
        if (id == R.id.action_image) {
            ImageView image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.volcano_hawaii_12_22_17);
            return true;
        }

        //if home is selected.  remove image.
        if(id == R.id.home)
        {
            ImageView image = findViewById(R.id.imageView);
            image.setImageDrawable(null);
            return true;

        }

        //else return parent function
        return super.onOptionsItemSelected(item);
    }

    public void goToSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(Constants.KEY_NAME, editText.getText().toString().trim());
        intent.putExtra(Constants.KEY_USERNAME, usernameEditText.getText().toString().trim());
        intent.putExtra(Constants.KEY_EMAIL, emailEditText.getText().toString().trim());
        intent.putExtra(Constants.KEY_AGE,  ageEditText.getText().toString().trim());
        intent.putExtra(Constants.KEY_BIRTHDAY, ageText.getText().toString().trim());
        startActivity(intent);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
            editText.setText((String)savedInstanceState.get(Constants.KEY_NAME));
        }
        if (savedInstanceState.containsKey(Constants.KEY_USERNAME)) {
            usernameEditText.setText((String)savedInstanceState.get(Constants.KEY_USERNAME));
        }
        if (savedInstanceState.containsKey(Constants.KEY_AGE)) {
            ageText.setText((String)savedInstanceState.get(Constants.KEY_AGE));
        }
        if (savedInstanceState.containsKey(Constants.KEY_BIRTHDAY)) {
            ageEditText.setText((String)savedInstanceState.get(Constants.KEY_BIRTHDAY));
        }
        if (savedInstanceState.containsKey(Constants.KEY_EMAIL)) {
            emailEditText.setText((String)savedInstanceState.get(Constants.KEY_EMAIL));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_NAME, editText.getText().toString());
        outState.putString(Constants.KEY_EMAIL, emailEditText.getText().toString());
        outState.putString(Constants.KEY_BIRTHDAY, ageEditText.getText().toString());
        outState.putString(Constants.KEY_AGE, ageText.getText().toString());
        outState.putString(Constants.KEY_USERNAME, usernameEditText.getText().toString());

    }
}
