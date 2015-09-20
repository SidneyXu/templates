package com.example.android.lib;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by mrseasons on 2015/09/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityInstrumentationTestCaseTest extends ActivityInstrumentationTestCase2<CalculatorActivity> {

    public ActivityInstrumentationTestCaseTest() {
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
        EditText num1EditText = (EditText) getActivity().findViewById(R.id.number1);
        TouchUtils.clickView(this, num1EditText);
        sendKeys(KeyEvent.KEYCODE_1);

        EditText num2EditText = (EditText) getActivity().findViewById(R.id.number2);
        TouchUtils.clickView(this, num2EditText);
        sendKeys(KeyEvent.KEYCODE_1);
        sendKeys(KeyEvent.KEYCODE_0);

        //  Monitor ResultActivity
        Instrumentation.ActivityMonitor monitor
                = new Instrumentation.ActivityMonitor(ResultActivity.class.getCanonicalName(), null, false);
        getInstrumentation().addMonitor(monitor);

        Button calcButton = (Button) getActivity().findViewById(R.id.calc);
        TouchUtils.clickView(this, calcButton);

        Activity resultActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertThat(monitor.getHits(), is(1));
        assertThat(resultActivity, notNullValue());

        TextView resultTextView = (TextView) resultActivity.findViewById(R.id.result);
        assertThat(resultTextView.getText().toString(), is("11"));
    }
}
