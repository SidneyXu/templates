package com.bookislife.provence.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.bookislife.provence.MaterialActivity;
import com.bookislife.provence.R;

public abstract class BaseActivity extends MaterialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processArguments(getIntent() == null
                || getIntent().getExtras() == null ? new Bundle() :
                getIntent().getExtras());
    }

    protected void processArguments(Bundle bundle) {
    }


    public void setUpToolbar(boolean isHome) {
        if (isHome) {
            super.setUpToolbar(R.id.toolbar, R.mipmap.ic_menu_white_24dp);
        } else {
            super.setUpToolbar(R.id.toolbar, -1);
        }
    }

    public void setUpFloatActionButton() {
        super.setUpFloatActionButton(R.id.fab);
    }

    public void setUpDrawer() {
        super.setUpDrawer(R.id.nav_view, R.id.drawer_layout);

        if (navigationView == null) return;

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_item_home:
                        break;
                    case R.id.nav_item_bookmark:
                        break;
                    case R.id.nav_item_feedback:
                        break;
                    case R.id.nav_item_help:
                        break;
                    case R.id.nav_item_settings:
                        break;
                    case R.id.nav_item_tag:
                        break;
                    case R.id.nav_item_trash:
                        break;
                }

                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                return true;
            }
        });
    }
}
