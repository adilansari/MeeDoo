package com.adil.meedoo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.adil.meedoo.models.Priority;

public class ListItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        Spinner prioritySpinner = (Spinner) findViewById(R.id.prioritySpinner);
        prioritySpinner.setAdapter(new ArrayAdapter<Priority>(this, android.R.layout.simple_spinner_item, Priority.values()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.item_action_bar_menu, menu);
        return true;
    }

    public void onSaveActionClick(MenuItem item) {
    }

    public void onDeleteActionClick(MenuItem item) {
    }
}
