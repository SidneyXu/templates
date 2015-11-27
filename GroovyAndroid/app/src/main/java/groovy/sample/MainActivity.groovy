package groovy.sample

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import groovy.transform.CompileStatic

/**
 * Created by SidneyXu on 2015/11/25.
 */
@CompileStatic
class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        contentView = R.layout.activity_main

        findViewById(android.R.id.button1).onClickListener = {
            startActivity(new Intent(this, CountryListActivity.class))
        }

    }

    @Override
    boolean onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate(R.menu.menu_main, menu)
        true
    }

    @Override
    boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        def id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            true
        }

        super.onOptionsItemSelected(item)
    }
}