package com.example.mvien.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();
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
        if (b.containsKey(Constants.KEY_AGE)) {

        }
        if (b.containsKey(Constants.KEY_USERNAME)) {
            String username = b.getString(Constants.KEY_USERNAME);

        }
        if (b.containsKey(Constants.KEY_NAME)) {
            String email = b.getString(Constants.KEY_EMAIL);

        }
        if (b.containsKey(Constants.KEY_BIRTHDAY)) {
            String birthday = b.getString(Constants.KEY_BIRTHDAY);

        }

        textView.setText(msg);

        Log.i(TAG, "onCreate()");
    }

    public void goToMainActivity(View view)
    {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
    }
}