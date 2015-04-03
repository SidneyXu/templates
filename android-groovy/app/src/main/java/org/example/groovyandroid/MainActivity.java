package org.example.groovyandroid;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;
import groovy.json.JsonSlurper;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import groovy.lang.Singleton;
import groovy.transform.CompileStatic;
import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;
import org.example.groovyandroid.bean.Speaker;

import java.io.File;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //in framgents
//        mCallbacks?.onItemSelected(((SessionListActivity) activity).sessionListAdapter.getItem(position).id)

//        ListView.INVALID_POSITION

//        def values = Application.instance.sessions.findAll {
//            it.slot.date == constraint
//        }
//        if (trackName) {
//            values = values.findAll {
//                it.slot.trackName == trackName
//            }
//        }
//        if (speakerId) {
//            values = values.findAll {
//                it.speakerId == speakerId
//            }
//        }

//        supportFragmentManager

//        rootView.post {
//            rootView.fullScroll(ScrollView.FOCUS_UP)
//        }

//        actionBar.displayHomeAsUpEnabled = true

//        @Singleton
// todo: replace with proper data content provider
//        class Application

//        edit {
//            putStringSet("favorites", favorites.collect { it.toString() } as Set)
//        }
//        private void edit(@DelegatesTo(SharedPreferences.Editor) Closure cl) {
//            def edit = prefs().edit()
//            cl.delegate = edit
//            cl()
//            edit.commit()
//        }
//        private SharedPreferences prefs() {
//            SharedPreferences sharedPref = applicationContext.getSharedPreferences(getString(R.string.favorites_list), Context.MODE_PRIVATE)
//            sharedPref
//        }

//        @CompileStatic
//        @ToString(includeNames = true)
//        @EqualsAndHashCode
//        class Status implements Serializable


//        boolean shouldUpdate(Context context) {
//            def statusFile = new File(context.getCacheDir(), STATUS_FILE)
//            Status old = new Status()
//            if (statusFile.exists()) {
//                statusFile.withObjectInputStream { ObjectInputStream oin ->
//                    try {
//                        old = (Status) oin.readObject()
//                    } catch (InvalidClassException e) {
//                        old = null
//                    }
//                }
//            }
//            try {
//                def json = (Map) new JsonSlurper().parse([:], new URL("$baseApiUrl/api2/status/$CONFERENCE_ID"), 'utf-8')
//                Status status = new Status(
//                        talks: (String) json.talks,
//                        speakers: (String) json.speakers,
//                        agenda: (String) json.agenda,
//                        favorites: (String) json.favorites
//                )
//                statusFile.withObjectOutputStream { it.writeObject(status) }
//
//                status != old
//            } catch (Exception e) {
//                Log.e("AgendaClient", "Unable to fetch status. Network down?")
//                false
//            }
//        }


//        void fetchAgenda(Context ctx, Closure callback) {
//            File agenda = fetchAndCacheAgenda(ctx)
//            if (agenda.exists()) {
//                def feed = (Map) new JsonSlurper().parse(agenda, 'utf-8')
//                List<Session> sessions = []
//                List<Speaker> speakers = []
//                feed.agendaDays.each { Map day ->
//                    day.tracks.each { Map track ->
//                        String color = track.color
//                        String name = track.name
//                        track.agendaItems.collect(sessions) { Map item ->
//                            item.trackColor = color
//                            item.trackName = name
//                            def speaker = toSpeaker((Map) item.speaker)
//                            if (speaker) {
//                                speakers << speaker
//                            }
//                            def session = toSession(item)
//                            session.speakerId = speaker?.id
//
//                                    session
//                        }
//                    }
//                }
//                speakers.each { speaker ->
//                        speaker.talks = new ArrayList<Session>(sessions.findAll {
//                            it.speakerId == speaker.id
//                        })
//                }
//
//                callback(speakers, sessions.sort { it.slot.startTime }.unique { it.id })
//            } else {
//                Toast.makeText(ctx, "Unable to fetch agenda. Please check connectivity.", Toast.LENGTH_SHORT).show()
//            }
//        }


//        def button = findViewById(R.id.newTodoButton)
//        button.onClickListener = {
//        ...
//        }


//        @CompileStatic
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
