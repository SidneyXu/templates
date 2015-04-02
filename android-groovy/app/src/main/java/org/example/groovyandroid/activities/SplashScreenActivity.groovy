package org.example.groovyandroid.activities

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import org.example.groovyandroid.R

/**
 * Created by mrseasons on 4/2/15.
 */
//@CompileStatic
class SplashScreenActivity extends Activity {

//    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        void onReceive(Context context, Intent intent) {
//            def next = new Intent(context, SessionListActivity)
//            context.startActivity(next)
//            finish()
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash);

//        Intent intent = new Intent(this, AgendaService)
//        startService(intent)
//        def intentFilter = new IntentFilter(AgendaService.SESSION_LIST_RESPONSE)
//        intentFilter.addCategory(AgendaService.CATEGORY)
//        registerReceiver(broadcastReceiver, intentFilter)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy()
//        unregisterReceiver(broadcastReceiver);
    }

}