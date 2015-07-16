package com.bookislife.provence.activities;

import android.os.Bundle;

import com.bookislife.provence.R;

public class NoteListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        setUpToolbar(true);
        setUpFloatActionButton();
        setUpDrawer();
    }
}
