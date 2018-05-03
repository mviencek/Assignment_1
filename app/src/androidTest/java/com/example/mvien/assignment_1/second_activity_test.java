package com.example.mvien.assignment_1;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class second_activity_test {
    @Rule
    //supplies the extras for the page
    public ActivityTestRule<SecondActivity> activityTestRule
            = new ActivityTestRule<SecondActivity>(SecondActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent testIntent = new Intent();
            testIntent.putExtra(Constants.KEY_NAME, "Mikal Viencek");
            testIntent.putExtra(Constants.KEY_USERNAME, "mviencek");
            testIntent.putExtra(Constants.KEY_OCCUPATION, "Chef");
            testIntent.putExtra(Constants.KEY_AGE, "65");
            testIntent.putExtra(Constants.KEY_DESCRIPTION, "Inputting test data!");
            return testIntent;
        }
    };


    @Test
    public void checkImage(){
        onView(withId(R.id.profilePic)).check(matches(isDisplayed()));
    }
    @Test
    //verifies message
    public void setsRightUserNameBasedOnIntentExtra() {
        onView(withId(R.id.usersName))
                .check(matches(withText("mviencek")));
    }

    @Test
    //verifies message
    public void setsRightAgeBasedOnIntentExtra() {
        onView(withId(R.id.usersAge))
                .check(matches(withText("65")));
    }

    @Test
    //verifies message
    public void setsRightOccupationBasedOnIntentExtra() {
        onView(withId(R.id.job))
                .check(matches(withText("Chef")));
    }

    @Test
    //verifies message
    public void setsRightDescriptBasedOnIntentExtra() {
        onView(withId(R.id.descript))
                .check(matches(withText("Inputting test data!")));
    }

    @Test
    public void testMatches()
    {
        onView(withText(R.string.matches))
                .perform(click());
        onView(withText(R.string.matches_go_here))
                .check(matches(withText("Matches will go here")));
    }

    @Test
    public void testSettings()
    {
        onView(withText(R.string.settings))
                .perform(click());
        onView(withText(R.string.settings_go_here))
                .check(matches(withText("Settings go here")));
    }



}