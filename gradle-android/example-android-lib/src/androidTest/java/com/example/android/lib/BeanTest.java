package com.example.android.lib;

import android.test.suitebuilder.annotation.SmallTest;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;

/**
 * Created by mrseasons on 2015/07/27.
 */
@RunWith(RobolectricGradleTestRunner.class)
@org.robolectric.annotation.Config(constansts = BuildConfig.class)
public class BeanTest {

    @SmallTest
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