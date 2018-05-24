package com.example.mvien.assignment_1;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private Bundle b;
    // constructor
    public FragAdapter(Context context, FragmentManager fm, Bundle bund ) {
        super(fm);
        mContext = context;
        if(bund != null)
        {
            this.b = bund;
        }
    }

    // grabs the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if(position ==1) {
            Profile p =  new Profile();
            p.setArguments(this.b);
            return p;
        }
        else if (position == 0){
            Matches m = new Matches();
            m.setArguments(this.b);
            return m;
        }
        else {
            Settings s = new Settings();
            s.setArguments(this.b);
            return s;
        }
    }

    // sets the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // heading for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.matches);
            case 1:
                return mContext.getString(R.string.profile);
            case 2:
                return mContext.getString(R.string.settings);
            default:
                return null;
        }
    }
}