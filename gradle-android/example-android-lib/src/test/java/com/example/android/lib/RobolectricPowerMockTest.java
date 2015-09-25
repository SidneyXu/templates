package com.example.android.lib;

import android.content.Context;
import com.example.android.lib.util.Calc;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by mrseasons on 2015/09/18.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21,
        constants = BuildConfig.class, manifest = "build/intermediates/bundles/debug/AndroidManifest.xml")
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest(Calc.class)
public class RobolectricPowerMockTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Before
    public void setup() {
    }

    @After
    public void teardown() {
    }

    @Test
    public void sample() {
        Context context = RuntimeEnvironment.application.getApplicationContext();
        assertThat(context.getString(R.string.app_name)).isEqualTo("example-android-lib");
    }

    @Test
    public void testCalc() {
        PowerMockito.mockStatic(Calc.class);
        Mockito.when(Calc.add(1, 2)).thenReturn(10);

        assertThat(Calc.add(1, 2)).isEqualTo(10);
    }
}
