package com.example.mvien.assignment_1;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
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
            return testIntent;
        }
    };

    @Test
    //verifies message
    public void setsRightMessageBasedOnIntentExtra() {
        onView(withId(R.id.textView))
                .check(matches(withText("Thanks for signing up Mikal Viencek!\n")));
    }

    @Test
    //tests the go back button and makes sure the form is empty
    public void goBack(){
        onView(withId(R.id.mainActivityButton)).perform(click());
        onView(withId(R.id.ageEditText)).check(matches(withText("")));
        onView(withId(R.id.usernameEditText)).check(matches(withText("")));
        onView(withId(R.id.emailEditText)).check(matches(withText("")));
        onView(withId(R.id.ageText)).check(matches(withText("")));
        onView(withId(R.id.nameEditText)).check(matches(withText("")));
    }

    @Test
    //tests the go back button and makes sure the form is empty
    public void goBackButton() {
        onView(withId(R.id.textView))
                .check(matches(withText("Thanks for signing up Mikal Viencek!\n")));
        Espresso.pressBack();
        onView(withId(R.id.ageEditText)).check(matches(withText("")));
        onView(withId(R.id.usernameEditText)).check(matches(withText("")));
        onView(withId(R.id.emailEditText)).check(matches(withText("")));
        onView(withId(R.id.ageText)).check(matches(withText("")));
        onView(withId(R.id.nameEditText)).check(matches(withText("")));
    }
}