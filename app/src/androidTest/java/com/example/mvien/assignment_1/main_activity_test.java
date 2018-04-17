package com.example.mvien.assignment_1;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.CoreMatchers.not;
import android.content.pm.ActivityInfo;
import static android.support.test.espresso.action.ViewActions.typeText;


import android.support.test.espresso.contrib.PickerActions;
import android.widget.DatePicker;


import org.hamcrest.Matchers;


import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;





@RunWith(AndroidJUnit4.class)
public class main_activity_test {


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);


    @Test
    //makes sure the image is not there after menu click
    public void removeCheck() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.home))
                .perform(click());
        onView(withId(R.id.imageView)).check(matches(not(isDisplayed())));
    }

    @Test
    public void canEnterNameAndRotate() {
        onView(withId(R.id.nameEditText)).perform(typeText("Mike"));
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Make sure text view still has Mike on rotate
        onView(withId(R.id.nameEditText))
                .check(matches(withText("Mike")));
    }

    @Test
    //make sure the image is not there to begin with
    public void initialCheck(){

        onView(withId(R.id.imageView)).check(matches(not(isDisplayed())));
    }

    @Test
    public void canEnterUserNameAndRotate() {
        onView(withId(R.id.usernameEditText)).perform(typeText("mikeTest"));
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Make sure text view still has Mike on rotate
        onView(withId(R.id.usernameEditText))
                .check(matches(withText("mikeTest")));
    }


    @Test
    //makes sure the image is there after menu click
    public void displayCheck()
    {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.swapText))
                .perform(click());
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
    }

    @Test
    public void canEnterEmailAndRotate() {
        onView(withId(R.id.emailEditText)).perform(typeText("mike@test.com"));
        activityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Make sure text view still has Mike on rotate
        onView(withId(R.id.emailEditText))
                .check(matches(withText("mike@test.com")));
    }

    @Test
    public void checkBirthDate(){
        int year = 2000;
        int month = 2;
        int day = 1;
        onView(withId(R.id.button1)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month + 1, day)).check(matches(not(withText("mike@test.com"))));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.ageEditText))
                .check(matches(withText("3/1/2000")));
    }
    @Test
    public void checkBadBirthDate(){
        int year = 2010;
        int month = 2;
        int day = 1;
        onView(withId(R.id.button1)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month + 1, day)).check(matches(not(withText("mike@test.com"))));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.secondActivityBtn)).check(matches(not(isEnabled())));
    }
    @Test
    public void checkGoodBirthDate(){
        int year = 2000;
        int month = 2;
        int day = 1;
        onView(withId(R.id.button1)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month + 1, day)).check(matches(not(withText("mike@test.com"))));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.secondActivityBtn)).check(matches(isEnabled()));
    }

}
