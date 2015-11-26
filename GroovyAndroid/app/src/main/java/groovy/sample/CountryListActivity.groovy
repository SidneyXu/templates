package groovy.sample

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic
import org.json.JSONArray
import org.json.JSONObject

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by SidneyXu on 2015/11/25.
 */
@CompileStatic
class CountryListActivity extends AppCompatActivity {

    ExecutorService service = Executors.newSingleThreadExecutor()
    Context context = this

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        ListView listView = new ListView(this)
        setContentView(listView)

        findCountries {  List<String> names, Exception e ->
            listView.post(new Runnable() {
                @Override
                void run() {
                    if (e) {
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        return
                    }
//                    Apt apt = new Apt(owner as Context, names)
//                    listView.adapter = apt
                }
            })

        }
    }

    void findCountries(Closure callback) {
        service.execute(new Runnable() {
            @Override
            void run() {
                try {
                    def result = new URL("https://restcountries.eu/rest/v1/all").getBytes()
                    def countries = new JsonSlurper().parse(result, "UTF-8")

                    println("coutry is "+countries)
                    println("count11outry is "+countries as JSONArray)

                    List<String> names = []
                    countries.each {
                        it["name"].each { String n ->
                            names << n
                        }
                    }
                    callback(names, null)
                } catch (e) {
                    callback(null, e)
                }
            }
        })

//
//        def asyncTask= [
//                doInBackground:{params->
//
//                },
//                onPostExecute:{String result->
//
//                }
//        ]as AsyncTask
    }

    class Apt extends ArrayAdapter<String> {

        Apt(final Context context, final List<String> data) {
            super(context, android.R.layout.simple_list_item_1, android.R.id.text1, data)
        }

        @Override
        String getItem(final int position) {
            return super.getItem(position)
        }

        @Override
        View getView(final int position, final View convertView, final ViewGroup parent) {
            View view = super.getView(position, convertView, parent)
            def title = view.findViewById(android.R.id.text1) as TextView
            title.setText(getItem(position))
            view
        }
    }
}
