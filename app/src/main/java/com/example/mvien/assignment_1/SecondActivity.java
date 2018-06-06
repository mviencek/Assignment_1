package com.example.mvien.assignment_1;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.mvien.assignment_1.entity.UserSettings;
import com.example.mvien.assignment_1.models.MatchesModel;
import com.example.mvien.assignment_1.viewmodels.MatchesViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends AppCompatActivity implements Matches.OnListFragmentInteractionListener {
    private MatchesViewModel viewModel;
    private ProgressDialog mProgressDialog;
    private LocationManager locationManager;
    private Location myLocation;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragAdapter adapter;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content of the activity
        setContentView(R.layout.activity_second);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // add viewpager
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewModel = new MatchesViewModel();
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if (checkLocation()) {
            networkUpdates();
        }

    }

    private boolean checkLocation() {
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public void networkUpdates() {
        if (!checkLocation()) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            showProgressDialog();
            Intent intent = getIntent();
            Bundle b = intent.getExtras();
            viewModel.getMatchedItems(
                    (ArrayList<MatchesModel> matches) -> {
                        //place the parcelable in the bundle
                        b.putParcelableArrayList("matches", matches);
                        if(myLocation != null) {
                            b.putDouble("myLat", myLocation.getLatitude());
                            b.putDouble("myLong", myLocation.getLongitude());
                        }
                        if(adapter == null) {
                            adapter = new FragAdapter(SecondActivity.this, getSupportFragmentManager(), b);
                        }
                        else{
                            adapter.setData(b);
                            adapter.notifyDataSetChanged();
                        }
                        // set the adapter
                        viewPager.setAdapter(adapter);
                        tabLayout.setupWithViewPager(viewPager);
                        hideProgressDialog();
                    }

            );
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 1000, locationListenerNetwork);
        }

}
    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            myLocation = location;
            viewModel.clear();
            Intent intent = getIntent();
            Bundle b = intent.getExtras();
            viewModel.getMatchedItems(
                    (ArrayList<MatchesModel> matches) -> {
                        //place the parcelable in the bundle
                        b.putParcelableArrayList("matches", matches);
                        if(myLocation != null) {
                            b.putDouble("myLat", myLocation.getLatitude());
                            b.putDouble("myLong", myLocation.getLongitude());
                        }
                        if(adapter == null) {
                            adapter = new FragAdapter(SecondActivity.this, getSupportFragmentManager(), b);
                        }
                        else{
                            adapter.setData(b);
                            adapter.notifyDataSetChanged();
                        }
                        // set the adapter
                        viewPager.setAdapter(adapter);
                        tabLayout.setupWithViewPager(viewPager);
                    }

            );
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };

    //erases form on back button press
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
        finish();
    }

    //clears the view model on pause
    @Override
    protected void onPause() {
        viewModel.clear();
        super.onPause();
    }

    //listener
    @Override
    public void onListFragmentInteraction(MatchesModel person) {
        //updates person in database
        viewModel.updateMatchesItem(person);
    }


}