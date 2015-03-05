package org.hello;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.appcompat.R;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import org.joda.time.LocalTime;


/**
 * Created by mrseasons on 1/21/15.
 */
public class HelloActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_layout);

        System.out.println("onCreate "+getString(R.string.app_name));

        System.out.println(3);

        Log.i("myapp" , "onCreate "+getString(R.string.app_name));

        Log.i("myapp" , "3");

//        Button button=findViewById(R.id.button1);
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalTime currentTime = new LocalTime();
        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText("The current local time is: " + currentTime);
    }

}