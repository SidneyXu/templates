package com.example.android.lib;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import com.example.java.lib.Person;

/**
 * Created by mrseasons on 2015/4/25.
 */
public class HelloActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int id = getResources().getIdentifier("text", "id", getPackageName());
        TextView textView = (TextView) findViewById(id);
        Person person = new Person("Tom");
        textView.setText(person.hello());
    }

    protected void createFragment() {

    }
}
