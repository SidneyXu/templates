package com.example.android.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by mrseasons on 2015/09/17.
 */
public class CalculatorActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        findViewById(R.id.calc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int number1 = Integer.valueOf(((EditText) findViewById(R.id.number1)).getText().toString());
                int number2 = Integer.valueOf(((EditText) findViewById(R.id.number2)).getText().toString());
                Intent intent = new Intent(CalculatorActivity.this, ResultActivity.class);
                intent.putExtra(ResultActivity.EXTRA_NUMBER_1, number1);
                intent.putExtra(ResultActivity.EXTRA_NUMBER_2, number2);
                startActivity(intent);
            }
        });
    }
}
