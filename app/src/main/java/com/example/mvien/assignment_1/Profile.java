package com.example.mvien.assignment_1;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Profile extends Fragment {

    TextView usersName;
    TextView usersAge;
    TextView job;
    TextView descript;

        //empty constructor
        public Profile() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle b = this.getArguments();
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            if (b.containsKey(Constants.KEY_USERNAME)) {
                String userName = getArguments().getString(Constants.KEY_USERNAME);
                TextView usersName =  (TextView) rootView.findViewById(R.id.usersName);
                usersName.setText(userName);
            }
            if (b.containsKey(Constants.KEY_AGE)) {
                String age = getArguments().getString(Constants.KEY_AGE);
                TextView usersAge =  (TextView) rootView.findViewById(R.id.usersAge);
                usersAge.setText(age);
            }
            if (b.containsKey(Constants.KEY_OCCUPATION)) {
                String occupation = getArguments().getString(Constants.KEY_OCCUPATION);
                TextView job =  (TextView) rootView.findViewById(R.id.job);
                job.setText(occupation);
            }

            if (b.containsKey(Constants.KEY_DESCRIPTION)) {
                String description = getArguments().getString(Constants.KEY_DESCRIPTION);
                TextView descript =  (TextView) rootView.findViewById(R.id.descript);
                descript.setText(description);
            }

            return rootView;
        }
    }