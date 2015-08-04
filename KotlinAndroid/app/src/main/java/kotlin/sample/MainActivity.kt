package kotlin.sample

//android extension

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.activity_main.button1
import java.util.NoSuchElementException

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //        R.id.button1.setText("abc")
        button1.setOnClickListener {
            toast("Click a button")
        }

        findViewById(R.id.button1).setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Toast.makeText(this@MainActivity, "Click a button", Toast.LENGTH_SHORT).show()
            }
        })

        findViewById(android.R.id.button2).setOnClickListener(View.OnClickListener {
            println((it as Button).getText())
        })

    }

    //extension
    fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    //    navigate<DetailActivity>("2")
    //reified types can get class not class type
    //    inline public fun <reified T : Activity> Activity.navigate(id: String) {
    //         
    //        val intent = Intent(this, javaClass<T>())
    //         intent.putExtra("id", id)
    //         startActivity(intent) 
    //    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item!!.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun ViewGroup.asSequence(): Sequence<View> = object : Sequence<View> {

        override fun iterator(): Iterator<View> = object : Iterator<View> {
            private var nextValue: View? = null
            private var done = false
            private var position: Int = 0

            override public fun hasNext(): Boolean {
                if (nextValue == null && !done) {
                    nextValue = getChildAt(position)
                    position++
                    if (nextValue == null) done = true
                }
                return nextValue != null
            }

            override fun next(): View {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }
                val answer = nextValue
                nextValue = null
                return answer!!
            }
        }
    }
}
