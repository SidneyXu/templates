package kotlin.sample

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import org.json.JSONArray
import java.net.URL
import java.util.concurrent.Executors

/**
 * Created by SidneyXu on 2015/11/30.
 */
public class CountryListActivity : AppCompatActivity() {

    val service = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = ListView(this)
        setContentView(listView)

        findCountries { list, e ->
            listView.post {
                if (e != null) {
                    toast(e.message)
                    return@post
                }
                val apt = Apt(this, list!!)
                listView.adapter = apt
                listView.onItemClickListener = AdapterView.OnItemClickListener() { adapterView, view, i, l ->
                    toast("${list[i]}")
                }
            }
        }
    }

    fun findCountries(callback: (List<String>?, Exception?) -> Unit) {
        service.execute {
            using {
                try {
                    val inputStream = URL("https://restcountries.eu/rest/v1/all").openStream().autoClose()
                    val reader = inputStream.bufferedReader("UTF-8").autoClose()
                    val countries = reader.readText()
                    val json = JSONArray(countries)
                    val names = linkedListOf<String>()

                    val length = json.length()
                    var i = 0
                    while (i < length) {
                        with(json.getJSONObject(i)) {
                            names.add(getString("name"))
                        }
                        i++
                    }

                    callback(names, null)
                } catch(e: Exception) {
                    callback(null, e)
                }
            }
        }
    }

    //  Extension
    public fun Activity.toast(message: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    class ResourceHolder : AutoCloseable {
        val resources = arrayListOf<AutoCloseable>()

        fun <T : AutoCloseable> T.autoClose(): T {
            resources.add(this)
            return this
        }

        override fun close() {
            resources.reversed().forEach { it.close() }
        }
    }

    fun <R> using(block: ResourceHolder.() -> R): R {
        val holder = ResourceHolder()
        try {
            return holder.block()
        } finally {
            holder.close()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service.shutdown()
    }
}