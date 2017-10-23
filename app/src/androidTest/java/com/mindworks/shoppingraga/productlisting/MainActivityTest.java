package com.mindworks.shoppingraga.productlisting;

import android.content.Intent;
import android.provider.Settings;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import com.mindworks.shoppingraga.R;
import com.mindworks.shoppingraga.productdetail.ProductDetailActivity;
import com.mindworks.shoppingraga.testhelper.RecyclerViewItemCountAssertion;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mindworks.shoppingraga.testhelper.TestUtils.getActivityInstance;

/**
 * Created by taru on 10/22/2017.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule mMainActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void checkRecylerViewContent() {
        onView(withId(R.id.rv_products)).check(new RecyclerViewItemCountAssertion(5));
    }

    @Test
    public void onFabClicked() throws Exception {
        onView(withId(R.id.fab)).perform(ViewActions.click());
        // Check if activity is launched
        Assert.assertEquals(getActivityInstance().getClass(), ProductDetailActivity.class);
        // check if activity is launched in editable mode
        onView(withId(R.id.ll_details_edit)).check(matches(isDisplayed()));
    }

    @Test
    public void onOptionsItemSelected() throws Exception {
        Intents.init();
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.action_settings)).perform(ViewActions.click());
        intended(hasAction(Settings.ACTION_LOCALE_SETTINGS));
    }
}