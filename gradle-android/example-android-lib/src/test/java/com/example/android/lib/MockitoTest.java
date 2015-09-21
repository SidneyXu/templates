package com.example.android.lib;

import android.content.Context;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by mrseasons on 2015/09/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Mock
    public Context context;

    @Before
    public void setup() {
    }

    @After
    public void teardown() {
    }

    @Test
    public void sample() {
        Mockito.when(context.getString(R.string.app_name)).thenReturn("example-android-lib");
        assertThat(
                context.getString(R.string.app_name), is("example-android-lib"));


    }

}
