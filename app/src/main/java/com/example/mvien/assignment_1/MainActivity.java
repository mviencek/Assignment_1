package com.example.mvien.assignment_1;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private EditText occupation;
    private EditText description;

    //dialog listener
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2, arg3);
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        ageText = findViewById(R.id.ageText);
        secondActivityBtn = findViewById(R.id.secondActivityBtn);
        secondActivityBtn.setEnabled(false);
        ageEditText.setFocusable(false);
        ageText.setFocusable(false);
        occupation = findViewById(R.id.occupation);
        description = findViewById(R.id.description);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Calendar",
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    //dialog
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, 2000, 0, 1);
        }
        return null;
    }

    //setting age and birthday
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
        Calendar n = Calendar.getInstance();
        Calendar t = Calendar.getInstance();
        n.setTime(now);
        t.setTime(then);
        int year1 = t.get(Calendar.YEAR);
        int year2 = n.get(Calendar.YEAR);
        int age = year1 - year2;
        int month1 = t.get(Calendar.MONTH);
        int month2 = n.get(Calendar.MONTH);
        if (month2 > month1) {
            age--;
        }
        else if (month1 == month2) {
            int day1 = t.get(Calendar.DAY_OF_MONTH);
            int day2 = n.get(Calendar.DAY_OF_MONTH);
            if (day2 > day1) {
                age--;
            }
        }
        ageText.setText(String.valueOf(age));
        //if client is over 18 enable button
        if(age>=18){
            ageText.setError(null);
            secondActivityBtn.setEnabled(true);
        }
        else
        {
            ageText.setError("Must be over 18 years of age!");
            secondActivityBtn.setEnabled(false);
        }

    }
    //displays error and returns false if empty.
    private boolean isValidName(String name)
    {
        boolean valid = false;
        if(name.trim().length() == 0){
            editText.setError("Name is required!");
        }
        else{
            valid = true;
        }
        return valid;
    }
    //displays error and returns false if empty.
    private boolean isValidUserName(String userName)
    {
        boolean valid = false;
        if(userName.trim().length() == 0){
            usernameEditText.setError("User Name is required!");
        }
        else{
            valid = true;
        }
        return valid;
    }
    //displays error and returns false if empty.
    private boolean isValidEmail(String email)
    {
        boolean valid = false;
        if(email.trim().length() == 0) {
            emailEditText.setError( "Occupation is required!" );
        }
        else{
            valid = true;
        }
        return valid;
    }
    //displays error and returns false if empty.
    private boolean isValidJob(String job)
    {
        boolean valid = false;
        if(job.trim().length() == 0)
        {
            occupation.setError( "Occupation is required!" );
        }
        else{
            valid = true;
        }
        return valid;
    }
    //displays error and returns false if empty.
    private boolean isValidDesc(String descript)
    {
        boolean valid = false;
        if (descript.trim().length() == 0) {

            description.setError("Description required!");
        }
        else{
            valid = true;
        }
        return valid;
    }

    //traveling to the second activity with a bundle
    public void goToSecondActivity(View view) {
        //only go to second activity if all fields arent empty(valid)
        if(isValidName(editText.getText().toString()) && isValidUserName(usernameEditText.getText().toString())
                && isValidEmail(emailEditText.getText().toString()) && isValidJob(occupation.getText().toString())
                && isValidDesc(description.getText().toString())) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(Constants.KEY_NAME, editText.getText().toString().trim());
            intent.putExtra(Constants.KEY_USERNAME, usernameEditText.getText().toString().trim());
            intent.putExtra(Constants.KEY_EMAIL, emailEditText.getText().toString().trim());
            intent.putExtra(Constants.KEY_AGE, ageText.getText().toString().trim());
            intent.putExtra(Constants.KEY_BIRTHDAY, ageEditText.getText().toString().trim());
            intent.putExtra(Constants.KEY_OCCUPATION, occupation.getText().toString().trim());
            intent.putExtra(Constants.KEY_DESCRIPTION, description.getText().toString().trim());
            startActivity(intent);

        }
    }

    @Override
    //restoring info
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
        if(savedInstanceState.containsKey(Constants.KEY_DESCRIPTION)){
            description.setText((String)savedInstanceState.get(Constants.KEY_DESCRIPTION));
        }
        if(savedInstanceState.containsKey(Constants.KEY_OCCUPATION)){
            occupation.setText((String)savedInstanceState.get((Constants.KEY_OCCUPATION)));
        }
    }

    @Override
    //saving form info
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_NAME, editText.getText().toString());
        outState.putString(Constants.KEY_EMAIL, emailEditText.getText().toString());
        outState.putString(Constants.KEY_BIRTHDAY, ageEditText.getText().toString());
        outState.putString(Constants.KEY_AGE, ageText.getText().toString());
        outState.putString(Constants.KEY_USERNAME, usernameEditText.getText().toString());
        outState.putString(Constants.KEY_OCCUPATION, occupation.getText().toString());
        outState.putString(Constants.KEY_DESCRIPTION, description.getText().toString());

    }

}
