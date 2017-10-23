package com.mindworks.shoppingraga.productdetail;

import android.support.test.annotation.UiThreadTest;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;

import com.mindworks.shoppingraga.R;
import com.mindworks.shoppingraga.datastore.beans.Product;
import com.mindworks.shoppingraga.utils.Constants;
import com.mindworks.shoppingraga.utils.ObjectPool;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

/**
 * Created by taru on 10/22/2017.
 */
@MediumTest
public class ProductDetailActivityTest {

    @Rule
    public ActivityTestRule mDetailActivityRule = new ActivityTestRule(ProductDetailActivity.class);

    @BeforeClass
    public static void setupObjectPool() {
        ObjectPool pool = ObjectPool.INSTANCE;
        pool.setValue(Constants.KEY_PRODUCT_DETAILS_EDITABLE, false);
    }

    public void testEditableOrDisplayedSwitching() {
        onView(withId(R.id.ll_details_no_edit)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).perform(ViewActions.click());
        onView(withId(R.id.ll_details_edit)).check(matches(isDisplayed()));
        onView(withId(R.id.et_product_sale_price)).perform(typeText("12.3"));
        onView(withId(R.id.et_product_regular_price)).perform(typeText("11.2"));
        onView(withId(R.id.et_product_description)).perform(typeText("Test Description One"));
        onView(withId(R.id.et_product_name)).perform(typeText("Test Product One"));
        onView(withId(R.id.fab)).perform(ViewActions.click());
        assertTrue(mDetailActivityRule.getActivity().isFinishing());
    }

    @Test
    public void onFabClicked() throws Exception {
        testEditableOrDisplayedSwitching();
    }

//    @UiThreadTest
    @Test
    public void displayProduct() throws InterruptedException {
        ((ProductDetailActivity) mDetailActivityRule.getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((ProductDetailActivity) mDetailActivityRule.getActivity()).displayProduct(new Product("Test Product 2", "Test Description 2", "12.2", "14.4", ""));
            }
        });
        onView(withId(R.id.tv_product_name)).check(matches(withText("Test Product 2")));
        onView(withId(R.id.tv_product_description)).check(matches(withText("Test Description 2")));
        onView(withId(R.id.tv_product_regular_price)).check(matches(withText("12.2")));
        onView(withId(R.id.tv_product_sale_price)).check(matches(withText("14.4")));
        onView(withId(R.id.et_product_name)).check(matches(withText("Test Product 2")));
        onView(withId(R.id.et_product_description)).check(matches(withText("Test Description 2")));
        onView(withId(R.id.et_product_regular_price)).check(matches(withText("12.2")));
        onView(withId(R.id.et_product_sale_price)).check(matches(withText("14.4")));
        onView(withId(R.id.fab)).perform(ViewActions.click());
        onView(withId(R.id.fab)).perform(ViewActions.click());
    }
}