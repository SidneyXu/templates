package com.example.android.lib;

import android.content.Context;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by mrseasons on 2015/09/18.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, manifest = "./src/main/AndroidManifest.xml",
        constants = BuildConfig.class)
public class RobolectricTest {

    @Before
    public void setup() {
    }

    @After
    public void teardown() {
    }

    @Test
    public void sample() {
        Context context = RuntimeEnvironment.application.getApplicationContext();
        assertThat(
                context.getString(R.string.app_name), is("example-android-lib"));
    }
}
