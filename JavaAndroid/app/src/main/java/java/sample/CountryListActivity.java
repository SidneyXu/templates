package java.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SidneyXu on 2016/01/19.
 */
public class CountryListActivity extends AppCompatActivity {

    private ExecutorService service = Executors.newSingleThreadExecutor();
    private Context context;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(this);
        setContentView(listView);


    }

    private void findCountries() {
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = new URL("https://restcountries.eu/rest/v1/all").openStream();
                    // TODO: 1/19/16  
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        service.shutdown();
    }
}
