package com.example.android.lib;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import com.robotium.solo.Solo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by mrseasons on 2015/09/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RobotiumTest extends ActivityInstrumentationTestCase2<CalculatorActivity> {

    public RobotiumTest() {
        super(CalculatorActivity.class);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testAdd() {
        Solo solo = new Solo(getInstrumentation(), getActivity());

        solo.enterText(0, "3");
        solo.enterText(1, "30");

        solo.clickOnButton("=");

        solo.assertCurrentActivity("Current is ResultActivity", ResultActivity.class);

        assertThat(solo.searchText("33"), is(true));
    }
}
