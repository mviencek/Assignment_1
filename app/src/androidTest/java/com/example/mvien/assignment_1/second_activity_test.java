package com.example.mvien.assignment_1;
import android.Manifest;
import android.content.Intent;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;

//we have to put threads to sleep and wait for the ui to populate now
// 6 second delay in case of slow internet

public class second_activity_test {
    @Rule
    //supplies the extras for the page
    public ActivityTestRule<SecondActivity> activityTestRule
            = new ActivityTestRule<SecondActivity>(SecondActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent testIntent = new Intent();
            testIntent.putExtra(Constants.KEY_NAME, "Mikal Viencek");
            testIntent.putExtra(Constants.KEY_EMAIL, "test@test.com");
            testIntent.putExtra(Constants.KEY_USERNAME, "mviencek");
            testIntent.putExtra(Constants.KEY_OCCUPATION, "Chef");
            testIntent.putExtra(Constants.KEY_AGE, "65");
            testIntent.putExtra(Constants.KEY_DESCRIPTION, "Inputting test data!");
            return testIntent;
        }
    };

    @Rule public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);
    @Rule public GrantPermissionRule permissionRuleCourse = GrantPermissionRule.grant(Manifest.permission.ACCESS_COARSE_LOCATION);


    @Test
    public void checkImage()  throws InterruptedException{
        Thread.sleep(8000);
        onView(withText(endsWith("Profile")))
                .perform(click());
        onView(withId(R.id.profilePic)).check(matches(isDisplayed()));
    }

    @Test
    //verifies message
    public void setsRightUserNameBasedOnIntentExtra() throws InterruptedException{
        Thread.sleep(8000);
        onView(withText(endsWith("Profile")))
                .perform(click());
        onView(withId(R.id.usersName))
                .check(matches(withText("mviencek")));
    }

    @Test
    //verifies message
    public void setsRightAgeBasedOnIntentExtra() throws InterruptedException {
        Thread.sleep(8000);
        onView(withText(endsWith("Profile")))
                .perform(click());
        onView(withId(R.id.usersAge))
                .check(matches(withText("65")));
    }

    @Test
    //verifies message
    public void setsRightOccupationBasedOnIntentExtra() throws InterruptedException{
        Thread.sleep(8000);
        onView(withText(endsWith("Profile")))
                .perform(click());
        onView(withId(R.id.job))
                .check(matches(withText("Chef")));
    }

    @Test
    //verifies message
    public void setsRightDescriptBasedOnIntentExtra() throws InterruptedException {
        Thread.sleep(8000);
        onView(withText(endsWith("Profile")))
                .perform(click());
        onView(withId(R.id.descript))
                .check(matches(withText("Inputting test data!")));
    }


    //i have no idea how to set circleci's emulators location so im checking if a matches were returned based on location
    @Test
    public void testButtonFav() throws InterruptedException
    {
        Thread.sleep(8000);
        onView(withText(endsWith("Matches")))
                .perform(click());
        if( withRecyclerView(R.id.my_recycler_view).atPosition(0).matches(isDisplayed())) {
            onView(withRecyclerView(R.id.my_recycler_view)
                    .atPositionOnView(0, R.id.favorite_button))
                    .perform(click());
        }

    }

    //i have no idea how to set circleci's emulators location so im checking if a matches were returned based on location
    @Test
    public void testButtonFavAgain() throws InterruptedException
    {
        Thread.sleep(8000);
        onView(withText(endsWith("Matches")))
                .perform(click());
        if( withRecyclerView(R.id.my_recycler_view).atPosition(0).matches(isDisplayed())) {
            onView(withRecyclerView(R.id.my_recycler_view)
                    .atPositionOnView(0, R.id.favorite_button))
                    .perform(click());

        }
    }

    //i have no idea how to set circleci's emulators location so im checking if a matches were returned based on location
    @Test
    public void testMatches() throws InterruptedException
    {
        Thread.sleep(8000);
            onView(withText(endsWith("Matches")))
                    .perform(click());
            if( withRecyclerView(R.id.my_recycler_view).atPosition(0).matches(isDisplayed())) {
        onView(withRecyclerView(R.id.my_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Cool Guy Mike"))));
    }
    }


    //helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void testSettings() throws InterruptedException
    {
        Thread.sleep(8000);
        onView(withText(endsWith("Settings")))
                .perform(click());
        onView(withId(R.id.editMinAge)).perform(replaceText("")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.updateSettings)).perform(click());
        onView(withId(R.id.editMinAge)).perform(typeText("24")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editMaxAge)).perform(replaceText("")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.updateSettings)).perform(click());
        onView(withId(R.id.editMaxAge)).perform(typeText("44")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editMaxDist)).perform(replaceText("")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.updateSettings)).perform(click());
        onView(withId(R.id.editMaxDist)).perform(typeText("10")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.updateSettings)).perform(click());
        onView(withId(R.id.editTime)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.editGender)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.editGender)).check(matches(withSpinnerText(containsString("Male"))));
        onView(withId(R.id.updateSettings)).perform(click());
        //second time to test updating on a fresh install
        onView(withId(R.id.editMinAge)).perform(replaceText("")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editMinAge)).perform(typeText("28")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editMaxAge)).perform(replaceText("")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editMaxAge)).perform(typeText("45")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editMaxDist)).perform(replaceText("")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editMaxDist)).perform(typeText("35")).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editGender)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.updateSettings)).perform(click());
        TestUtils.rotateScreen(activityTestRule.getActivity());

    }
}