package com.example.mvien.assignment_1;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mvien.assignment_1.entity.UserSettings;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;


public class Settings extends Fragment {

    TextView minAge;
    EditText editMinAge;
    TextView maxAge;
    EditText editMaxAge;
    TextView maxDistance;
    EditText editMaxDistance;
    TextView gender;
    Spinner editGender;
    TextView isPrivate;
    Switch editPrivate;
    TextView reminder;
    Button editReminder;
    Button updateSets;
    static EditText setTime;
    static String email;

    public Settings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        minAge = rootView.findViewById(R.id.minAge);
        editMinAge = rootView.findViewById(R.id.editMinAge);
        maxAge = rootView.findViewById(R.id.maxAge);
        editMaxAge = rootView.findViewById(R.id.editMaxAge);
        maxDistance = rootView.findViewById(R.id.maxDistance);
        editMaxDistance = rootView.findViewById(R.id.editMaxDist);
        gender = rootView.findViewById(R.id.gender);
        editGender = rootView.findViewById(R.id.editGender);
        if (savedInstanceState != null) {
            editGender.setSelection(savedInstanceState.getInt("gender", 0));
        }
        isPrivate = rootView.findViewById(R.id.is_private);
        editPrivate = rootView.findViewById(R.id.switch_private);
        reminder = rootView.findViewById(R.id.time);
        setTime = rootView.findViewById(R.id.setTime);
        setTime.setFocusable(false);
        editReminder = rootView.findViewById(R.id.editTime);
        editReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker mTimePicker = new TimePicker();
                mTimePicker.show(getFragmentManager(), "Select time");
            }
        });

        updateSets = rootView.findViewById(R.id.updateSettings);
        updateSets.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                UserSettings user = new UserSettings();
                if(editMinAge.getText().toString().trim().length() != 0) {
                    user.setMinAge(Integer.parseInt(editMinAge.getText().toString()));
                }
                else{
                    editMinAge.setError("Must set a minimum age!");
                    return;
                }
                if(editMaxAge.getText().toString().trim().length() != 0) {
                    user.setMaxAge(Integer.parseInt(editMaxAge.getText().toString()));
                }
                else{
                    editMaxAge.setError("Must set maximum age!");
                    return;
                }
                if(editMaxDistance.getText().toString().trim().length() != 0) {
                    user.setDistance(Integer.parseInt(editMaxDistance.getText().toString()));
                }
                else{
                    editMaxDistance.setError("Must set maximum distance!");
                    return;
                }

                user.setGender(String.valueOf(editGender.getSelectedItem()));
                user.setEmail(email);
                user.setPrivate(editPrivate.isChecked());
                if(setTime.getText().toString().trim().length() != 0) {
                    user.setReminder(setTime.getText().toString());
                }
                else{
                    setTime.setError("Must select a reminder time!");
                    return;
                }
                new InsertUserTask(getActivity(), user).execute();
            }
        });
        Bundle b = this.getArguments();
        if(b != null && b.containsKey(Constants.KEY_EMAIL))
        {
            email = getArguments().getString(Constants.KEY_EMAIL);
            new GetSettingsTask(getActivity(),this).execute();
        }
        return rootView;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("gender", editGender.getSelectedItemPosition());
        super.onSaveInstanceState(savedInstanceState);

    }

    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            String min ="";
            if(minute < 10)
            {
                min = "0" + String.valueOf(minute);
            }
            else
            {
                min = String.valueOf(minute);
            }
            if(hourOfDay > 12)
            {
                //we'll parse the string later if we need to do something with it
                setTime.setText(String.valueOf(hourOfDay - 12) + " : " + min + " PM");

            }
            else{
                if(hourOfDay!=0) {
                    //we'll parse the string later if we need to do something with it
                    setTime.setText(String.valueOf(hourOfDay) + " : " + min + " AM");
                }
                else{
                    //we'll parse the string later if we need to do something with it
                    setTime.setText("12 : " + min + " AM");
                }
            }
        }
    }

    private static class GetSettingsTask extends AsyncTask<Void, Void, UserSettings> {

        private WeakReference<Activity> weakActivity;
        private Settings settings;
        private WeakReference<Settings> weakFragment;

        public GetSettingsTask(Activity activity, Settings s) {
            this.weakActivity = new WeakReference<Activity>(activity);
            this.weakFragment = new WeakReference<Settings>(s);
        }

        @Override
        protected UserSettings doInBackground(Void... voids) {
            Activity activity = weakActivity.get();
            if(activity == null) {
                return null;
            }

            AppDatabase db = AppDatabaseSingleton.getDatabase(activity.getApplicationContext());
            List<UserSettings> users = db.userSettings().getUserSettings(email);
            if(users.size() <= 0 || users.get(0) == null) {
                return null;
            }
            return users.get(0);
        }

        @Override
        protected void onPostExecute(UserSettings user) {
            this.settings = weakFragment.get();
            if(user == null || this.settings == null) {
                return;
            }
            settings.editMinAge.setText(Integer.toString(user.getMinAge()));
            settings.editMaxAge.setText(Integer.toString(user.getMaxAge()));
            settings.editMaxDistance.setText(Integer.toString(user.getDistance()));
            settings.editGender.setSelection(((ArrayAdapter)settings.editGender.getAdapter()).getPosition(user.getGender()));
            settings.editPrivate.setChecked(user.isPrivate());
            settings.setTime.setText(user.getReminder());
        }
    }

    private static class InsertUserTask extends AsyncTask<Void, Void, UserSettings> {

        private WeakReference<Activity> weakActivity;
        private UserSettings user;

        public InsertUserTask(Activity activity, UserSettings user) {
            weakActivity = new WeakReference<>(activity);
            this.user = user;
        }

        @Override
        protected UserSettings doInBackground(Void... voids) {
            Activity activity = weakActivity.get();
            if(activity == null || user == null) {
                return null;
            }
            AppDatabase db = AppDatabaseSingleton.getDatabase(activity.getApplicationContext());
            db.userSettings().insertSettings(user);
            return user;
        }

        @Override
        protected void onPostExecute(UserSettings user) {
            if(user == null) {
                Toast.makeText(weakActivity.get().getApplicationContext(), "Unable To Update Settings", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(weakActivity.get().getApplicationContext(), "Settings Updated", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

