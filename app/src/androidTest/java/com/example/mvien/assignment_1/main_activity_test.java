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
@RunWith(AndroidJUnit4.class)
public class main_activity_test {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);


    @Test
    //make sure the image is not there to begin with
    public void initialCheck(){

        onView(withId(R.id.imageView)).check(matches(not(isDisplayed())));
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
    //makes sure the image is not there after menu click
    public void removeCheck() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.home))
                .perform(click());
        onView(withId(R.id.imageView)).check(matches(not(isDisplayed())));
    }



}
