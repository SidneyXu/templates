package com.example.android.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.android.lib.util.Calc;

/**
 * Created by mrseasons on 2015/09/17.
 */
public class ResultActivity extends Activity {

    public static final String EXTRA_NUMBER_1 = "num1";
    public static final String EXTRA_NUMBER_2 = "num2";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        TextView resultTextView = (TextView) findViewById(R.id.text);
        int result = Calc.add(intent.getIntExtra(EXTRA_NUMBER_1, 0), intent.getIntExtra(EXTRA_NUMBER_2, 0));
        resultTextView.setText("" + result);
    }
}
