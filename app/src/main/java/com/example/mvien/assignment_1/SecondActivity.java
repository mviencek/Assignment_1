package com.example.mvien.assignment_1;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.example.mvien.assignment_1.models.MatchesModel;
import com.example.mvien.assignment_1.viewmodels.MatchesViewModel;
import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity implements Matches.OnListFragmentInteractionListener {
    private MatchesViewModel viewModel;
    private ProgressDialog mProgressDialog;

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
        viewModel = new MatchesViewModel();
        showProgressDialog();
        viewModel.getMatchedItems(
                (ArrayList<MatchesModel> matches) -> {
                    Intent intent = getIntent();
                    Bundle b = intent.getExtras();
                    //place the parcelable in the bundle
                    b.putParcelableArrayList("matches", matches);
                    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                    FragAdapter adapter = new FragAdapter(this, getSupportFragmentManager(), b);
                    // set the adapter
                    viewPager.setAdapter(adapter);
                    // add viewpager
                    TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
                    tabLayout.setupWithViewPager(viewPager);
                    hideProgressDialog();
                }
        );

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