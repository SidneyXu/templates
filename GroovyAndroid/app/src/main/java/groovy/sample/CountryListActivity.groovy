package groovy.sample

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by SidneyXu on 2015/11/25.
 */
@CompileStatic
class CountryListActivity extends AppCompatActivity {

    ExecutorService service = Executors.newSingleThreadExecutor()
    Context context

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        ListView listView = new ListView(this)
        setContentView(listView)

        context = this

        findCountries { List names, Exception e ->
            listView.post(new Runnable() {
                @Override
                void run() {
                    if (e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show()
                        return
                    }
                    Apt apt = new Apt(context, names)
                    listView.adapter = apt
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        void onItemClick(
                                final AdapterView<?> parent,
                                final View view, final int position, final long id) {
                            Toast.makeText(context, "${names.get(position)}", Toast.LENGTH_SHORT).show()
                        }
                    })

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
                    def countries = new JsonSlurper().parse(result, "UTF-8") as ArrayList

                    List names = []
                    countries.each { country ->
                        names.add(country["name"])
                    }
                    callback(names, null)
                } catch (e) {
                    callback(null, e)
                }
            }
        })
    }

    @Override
    protected void onDestroy() {
        super.onDestroy()
        service.shutdown
    }
}
