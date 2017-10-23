package com.mindworks.shoppingraga;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MainExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Context curContext = InstrumentationRegistry.getContext();
        assertEquals("com.mindworks.shoppingraga.pale.usa.debug", appContext.getPackageName());
//        assertEquals("/data/app/com.mindworks.shoppingraga.pale.usa.debug-ApmxuQXxvMttyACt38lCIg==/base.apk", appContext.getPackageCodePath());
        assertEquals("com.mindworks.shoppingraga.test", curContext.getPackageName());
//        assertEquals("/data/app/com.mindworks.shoppingraga.test-U1BxiUM6cQ_vR1AzTCCW5Q==/base.apk", curContext.getPackageCodePath());
    }
}