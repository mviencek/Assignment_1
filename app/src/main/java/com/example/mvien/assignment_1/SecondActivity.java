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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);

        StringBuilder msg = new StringBuilder("Thanks for signing up ");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        assert b != null;
        if (b.containsKey(Constants.KEY_NAME)) {
            String name = b.getString(Constants.KEY_NAME);
            msg.append(name).append("!\n");
        }
       /* if (b.containsKey(Constants.KEY_AGE)) {
            //todo:  something with age
            //String age = b.getString(Constants.KEY_AGE);
        }
        if (b.containsKey(Constants.KEY_USERNAME)) {
            //todo:  something with username
           // String username = b.getString(Constants.KEY_USERNAME);

        }
        if (b.containsKey(Constants.KEY_EMAIL)) {
            //todo:  something with email
            //String email = b.getString(Constants.KEY_EMAIL);

        }
        if (b.containsKey(Constants.KEY_BIRTHDAY)) {
            //todo:  something with birthday
            //String birthday = b.getString(Constants.KEY_BIRTHDAY);

        }*/

        textView.setText(msg);
    }

    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
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