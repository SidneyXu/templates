package org.example.groovyandroid.service

import android.app.Application
import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.widget.Toast
import org.example.groovyandroid.R
import org.example.groovyandroid.bean.AgendaClient
import org.example.groovyandroid.bean.Session
import org.example.groovyandroid.bean.Speaker

/**
 * Created by mrseasons on 4/2/15.
 */
class AgendaService extends IntentService {
    public static final String CATEGORY = "${AgendaService}Category"
    public static final String SESSION_LIST_RESPONSE = "${AgendaService}.SessionListResponse"
    public static final String UPDATE_FAVORITES_RESPONSE = "${AgendaService}.UpdateFavoritesResponse"

    public static final String ACTION_FAVORITE = "favorite"
    public static final String SESSION_ID = "id"

    Handler mainThreadHandler = null;

    public AgendaService() {
        super("AgendaService")
        mainThreadHandler = new Handler()
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            switch (intent.action) {
                case ACTION_FAVORITE:
                    doFavorite(intent)
                    break;
                default:
                    doFetchAgenda()
            }
        }
    }

    private void doFavorite(Intent intent) {
        def favorites = Application.instance.favorites
        def sessions = Application.instance.sessions
        def sid = intent.getLongExtra(SESSION_ID, -1)
        if (sid>=0) {
            boolean addToFavorites = !favorites.contains(sid)
            if (addToFavorites) {
                mainThreadHandler.post {
                    Toast.makeText(this, "Added ${sessions.find { it.id == sid }.title} to your sessions", Toast.LENGTH_SHORT).show()
                }
                favorites.add(sid)
            } else {
                mainThreadHandler.post {
                    Toast.makeText(this, "Removed ${sessions.find { it.id == sid }.title} from your sessions", Toast.LENGTH_SHORT).show()
                }
                favorites.remove(sid)
            }
        }
        edit {
            putStringSet("favorites", favorites.collect { it.toString() } as Set)
        }
        def response = new Intent()
        response.setAction(UPDATE_FAVORITES_RESPONSE)
        response.addCategory(CATEGORY)
        sendBroadcast(response)
    }

    private void edit(@DelegatesTo(SharedPreferences.Editor) Closure cl) {
        def edit = prefs().edit()
        cl.delegate = edit
        cl()
        edit.commit()
    }

    private void doFetchAgenda() {
        def client = new AgendaClient('http://cfp.gr8conf.org')
        client.fetchAgenda(applicationContext) { speakers, sessions ->
            Application.instance.sessions = (List<Session>) sessions
            Application.instance.speakers = (List<Speaker>) speakers
        }
        SharedPreferences sharedPref = prefs()
        Application.instance.favorites = sharedPref.getStringSet("favorites", new LinkedHashSet<String>()).collect {
            it.toLong()
        } as Set
        def response = new Intent()
        response.setAction(SESSION_LIST_RESPONSE)
        response.addCategory(CATEGORY)
        sendBroadcast(response)
    }

    private SharedPreferences prefs() {
        SharedPreferences sharedPref = applicationContext.getSharedPreferences(getString(R.string.favorites_list), Context.MODE_PRIVATE)
        sharedPref
    }
}
