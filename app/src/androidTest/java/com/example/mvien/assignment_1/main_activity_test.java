package com.example.mvien.assignment_1;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.CoreMatchers.not;
import static android.support.test.espresso.action.ViewActions.typeText;
import android.support.test.espresso.contrib.PickerActions;
import android.widget.DatePicker;
import org.hamcrest.Matchers;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import android.support.test.espresso.intent.Intents;
import java.util.Calendar;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;

@RunWith(AndroidJUnit4.class)
public class main_activity_test {


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);


    @Test
    //make sure log in button isnt enabled to begin with
    public void notEnabled(){
        onView(withId(R.id.secondActivityBtn)).check(matches(not(isEnabled())));
    }


    @Test
    //makes sure fields are maintained on screen rotate
    public void canEnterAndRotate() {
        onView(withId(R.id.nameEditText)).perform(typeText("Mike")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.description)).perform(typeText("Test inputting a description")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.occupation)).perform(typeText("Chef")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.usernameEditText)).perform(typeText("mikeTest")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.emailEditText)).perform(typeText("mike@test.com")).perform(ViewActions.closeSoftKeyboard());
        TestUtils.rotateScreen(activityTestRule.getActivity());
        // Make sure text view still has Mike on rotate
        onView(withId(R.id.nameEditText))
                .check(matches(withText("Mike")));
        onView(withId(R.id.description))
                .check(matches(withText("Test inputting a description")));
        onView(withId(R.id.occupation))
                .check(matches(withText("Chef")));
        onView(withId(R.id.usernameEditText))
                .check(matches(withText("mikeTest")));
        onView(withId(R.id.emailEditText))
                .check(matches(withText("mike@test.com")));
    }

    @Test
    //checks the birthdate field
    public void checkBirthDate(){
        onView(withId(R.id.nameEditText)).perform(typeText("Mike")).perform(ViewActions.closeSoftKeyboard());
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
    //makes sure the button is disabled on invalid birthday
    public void checkBadBirthDate(){
        onView(withId(R.id.nameEditText)).perform(typeText("Mike")).perform(ViewActions.closeSoftKeyboard());
        int year = 2000;
        int month = 4;
        int day = 30;
        onView(withId(R.id.button1)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month + 1, day)).check(matches(not(withText("mike@test.com"))));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.secondActivityBtn)).check(matches(not(isEnabled())));
    }


    @Test
    //makes sure the button is disabled on invalid (month) birthday
    public void checkBadBirthDate2(){
        onView(withId(R.id.nameEditText)).perform(typeText("Mike")).perform(ViewActions.closeSoftKeyboard());
        int year = 2000;
        int month = 6;
        int day = 30;
        onView(withId(R.id.button1)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month + 1, day)).check(matches(not(withText("mike@test.com"))));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.secondActivityBtn)).check(matches(not(isEnabled())));
    }

    @Test
    //checks to see if the button is enabled on valid birthday
    public void checkGoodBirthDate(){
        onView(withId(R.id.nameEditText)).perform(typeText("Mike")).perform(ViewActions.closeSoftKeyboard());
        int year = 2000;
        int month = 2;
        int day = 1;
        onView(withId(R.id.button1)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month + 1, day)).check(matches(not(withText("mike@test.com"))));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.secondActivityBtn)).check(matches(isEnabled()));
    }

    @Test
    //checks age
    public void checkAge(){
        onView(withId(R.id.nameEditText)).perform(typeText("Mike")).perform(ViewActions.closeSoftKeyboard());
        int years = Calendar.getInstance().get(Calendar.YEAR);
        int year = years - 18;
        int month = 0;
        int day = 1;
        onView(withId(R.id.button1)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month + 1, day)).check(matches(not(withText("mike@test.com"))));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.ageText)).check(matches(withText("18")));
    }

    @Test
    //loads intents and travels to second activity
    public void canGoToSecondActivity() throws InterruptedException{
        onView(withId(R.id.nameEditText))
                .perform(typeText("Mikal Viencek")).perform(ViewActions.closeSoftKeyboard());

        int year = 2000;
        int month = 2;
        int day = 1;
        onView(withId(R.id.button1)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month + 1, day)).check(matches(not(withText("mike@test.com"))));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.usernameEditText))
                .perform(typeText("mviencek")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.emailEditText))
                .perform(typeText("mviencek@test.com")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.description))
                .perform(typeText("Inputting test information!")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.occupation))
                .perform(typeText("Chef")).perform(ViewActions.closeSoftKeyboard());
        Intents.init();
        onView(withId(R.id.secondActivityBtn)).perform(click());
        intended(hasComponent(SecondActivity.class.getName()));
        intended(hasExtra(Constants.KEY_NAME, "Mikal Viencek"));
        intended(hasExtra(Constants.KEY_USERNAME, "mviencek"));
        intended(hasExtra(Constants.KEY_EMAIL, "mviencek@test.com"));
        intended(hasExtra(Constants.KEY_OCCUPATION, "Chef"));
        intended(hasExtra(Constants.KEY_DESCRIPTION, "Inputting test information!"));
        Intents.release();
        Thread.sleep(6000);
        //now go back and make sure the form is empty
        Espresso.pressBack();
        onView(withId(R.id.ageEditText)).check(matches(withText("")));
        onView(withId(R.id.usernameEditText)).check(matches(withText("")));
        onView(withId(R.id.emailEditText)).check(matches(withText("")));
        onView(withId(R.id.ageText)).check(matches(withText("")));
        onView(withId(R.id.nameEditText)).check(matches(withText("")));
    }

    @Test
    public void checkIfElses()
    {
        int year = 2000;
        int month = 2;
        int day = 1;
        onView(withId(R.id.button1)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month + 1, day)).check(matches(not(withText("mike@test.com"))));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.secondActivityBtn)).check(matches(isEnabled()));
        onView(withId(R.id.nameEditText)).perform(replaceText("")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.secondActivityBtn)).perform(click());

        onView(withId(R.id.nameEditText)).perform(typeText("Mike")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.secondActivityBtn)).perform(click());

        onView(withId(R.id.usernameEditText)).perform(typeText("mikeTest")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.secondActivityBtn)).perform(click());


        onView(withId(R.id.emailEditText)).perform(typeText("mike@test.com")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.secondActivityBtn)).perform(click());


        onView(withId(R.id.occupation)).perform(typeText("Chef")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.secondActivityBtn)).perform(click());

    }



}
