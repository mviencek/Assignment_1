package com.example.mvien.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView textView;
    TextView usersName;
    TextView usersAge;
    TextView job;
    TextView descript;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        usersName = findViewById(R.id.usersName);
        usersAge = findViewById(R.id.usersAge);
        job = findViewById(R.id.job);
        descript = findViewById(R.id.descript);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b.containsKey(Constants.KEY_NAME)) {
            String name = b.getString(Constants.KEY_NAME);
        }
        if (b.containsKey(Constants.KEY_AGE)) {
            String age = b.getString(Constants.KEY_AGE);
            usersAge.setText(age);
        }
        if (b.containsKey(Constants.KEY_USERNAME)) {
           String userName = b.getString(Constants.KEY_USERNAME);
           if(userName != null) {
               usersName.setText(userName.toUpperCase());
           }

        }
        if (b.containsKey(Constants.KEY_OCCUPATION)) {
            String occupation = b.getString(Constants.KEY_OCCUPATION);
            if(occupation != null) {
                job.setText(occupation.toUpperCase());
            }

        }
        if (b.containsKey(Constants.KEY_DESCRIPTION)) {
            String description = b.getString(Constants.KEY_DESCRIPTION);
            descript.setText(description);

        }
    }

    //erases form on back button press
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
        finish();
}
}