package com.example.android.lib;

import android.test.suitebuilder.annotation.SmallTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by mrseasons on 2015/07/27.
 */
@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class)
public class BeanTest {

    @Test
    public void testGetCount() throws Exception {
        new Bean();
        new Bean();
        int count = Bean.getCount();
//        assertEquals(2, count);
//
//        new Bean();
//        assertEquals(3, count);
//
//        assertEquals(4, count);
    }
}