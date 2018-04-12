package com.example.mvien.assignment_1;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
}
