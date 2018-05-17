package com.example.mvien.assignment_1;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

//we have to put threads to sleep and wait for the ui to populate now

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
    public void checkImage()  throws InterruptedException{
        Thread.sleep(4000);
        onView(withText(R.string.profile))
                .perform(click());
        onView(withId(R.id.profilePic)).check(matches(isDisplayed()));
    }

    @Test
    //verifies message
    public void setsRightUserNameBasedOnIntentExtra() throws InterruptedException{
        Thread.sleep(4000);
        onView(withText(R.string.profile))
                .perform(click());
        onView(withId(R.id.usersName))
                .check(matches(withText("mviencek")));
    }

    @Test
    //verifies message
    public void setsRightAgeBasedOnIntentExtra() throws InterruptedException {
        Thread.sleep(4000);
        onView(withText(R.string.profile))
                .perform(click());
        onView(withId(R.id.usersAge))
                .check(matches(withText("65")));
    }

    @Test
    //verifies message
    public void setsRightOccupationBasedOnIntentExtra() throws InterruptedException{
        Thread.sleep(4000);
        onView(withText(R.string.profile))
                .perform(click());
        onView(withId(R.id.job))
                .check(matches(withText("Chef")));
    }

    @Test
    //verifies message
    public void setsRightDescriptBasedOnIntentExtra() throws InterruptedException {
        Thread.sleep(4000);
        onView(withText(R.string.profile))
                .perform(click());
        onView(withId(R.id.descript))
                .check(matches(withText("Inputting test data!")));
    }


    @Test
    public void testButtonFav() throws InterruptedException
    {
        Thread.sleep(4000);
        onView(withText(R.string.matches))
                .perform(click());
        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(0, R.id.favorite_button))
                .perform(click());
    }

    @Test
    public void testButtonFavAgain() throws InterruptedException
    {
        Thread.sleep(4000);
        onView(withText(R.string.matches))
                .perform(click());
        onView(withRecyclerView(R.id.my_recycler_view)
                .atPositionOnView(0, R.id.favorite_button))
                .perform(click());
    }
    @Test
    public void testMatches() throws InterruptedException
    {
        Thread.sleep(4000);
        onView(withText(R.string.matches))
                .perform(click());
        onView(withRecyclerView(R.id.my_recycler_view).atPosition(0))
                .check(matches(hasDescendant(withText("Cool Guy Mike"))));
    }


    //helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void testSettings() throws InterruptedException
    {
        Thread.sleep(4000);
        onView(withText(R.string.settings))
                .perform(click());
        onView(withText(R.string.settings_go_here))
                .check(matches(withText("Settings go here")));
    }
}