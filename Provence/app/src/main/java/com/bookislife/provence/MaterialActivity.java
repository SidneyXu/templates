package com.bookislife.provence;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class MaterialActivity extends AppCompatActivity {

    protected Context context;
    protected Toolbar toolbar;
    protected ActionBar actionBar;
    protected FloatingActionButton floatingActionButton;
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void startActivity(Class<?> targetClass) {
        startActivity(new Intent(context, targetClass));
    }

    public void setUpToolbar(int id, int homeAsUpIndicator) {
        toolbar = (Toolbar) findViewById(id);
        if (toolbar == null) return;

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            if (homeAsUpIndicator != -1) {
                actionBar.setHomeAsUpIndicator(homeAsUpIndicator);
            }
        }
    }

    protected void setUpFloatActionButton(int id) {
        floatingActionButton = (FloatingActionButton) findViewById(id);
    }

    protected void setUpDrawer(int navigationViewId, int drawerLayoutId) {
        drawerLayout = (DrawerLayout) findViewById(drawerLayoutId);
        if (drawerLayout == null) return;

        navigationView = (NavigationView) findViewById(navigationViewId);
        if (navigationView == null) return;
    }


}
