package scala.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.{Menu, MenuItem, View}
import android.widget.Button
import org.scaloid.common._

class MainActivity extends AppCompatActivity with SActivity {

    protected override def onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        find[Button](android.R.id.button1).onClick((v: View) =>
            startActivity[CountryListActivity]
        )
    }

    override def onCreateOptionsMenu(menu: Menu): Boolean = {
        getMenuInflater.inflate(R.menu.menu_main, menu)
        true
    }

    override def onOptionsItemSelected(item: MenuItem): Boolean = {
        val id: Int = item.getItemId
        if (id == R.id.action_settings) {
            return true
        }
        super.onOptionsItemSelected(item)
    }
}
