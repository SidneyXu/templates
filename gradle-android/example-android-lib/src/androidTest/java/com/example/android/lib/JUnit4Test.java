package com.example.android.lib;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.example.android.lib.util.Calc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by mrseasons on 2015/09/17.
 */
//http://qiita.com/shikato/items/071a4c879b5c3f0b46f0#espresso
//http://developer.android.com/intl/ja/training/testing/unit-testing/instrumented-unit-tests.html
//
@RunWith(AndroidJUnit4.class)
public class JUnit4Test {

    private Context mContext;

    @Before
    public void setUp() throws Exception {
        // Contextを取得
        mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void add() {
        assertThat(Calc.add(3, 5), is(8));
    }

    @Test
    public void context() {
        // Contextを使ったテスト
        assertThat(mContext.getString(R.string.app_name), is("example-android-lib"));
    }

}
