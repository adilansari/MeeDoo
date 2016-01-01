package com.adil.meedoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.adil.meedoo.helpers.DatabaseHelper;
import com.adil.meedoo.models.ToDo;

import java.text.ParseException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ToDo> todoItems;
    ListStoryAdapter todoAdapter;
    ListView lvItems;
    DatabaseHelper db;

    // Logcat tag
    private static final String LOG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(getApplicationContext());

        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.list_story_view);
        lvItems.setAdapter(todoAdapter);

        db.closeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_action_bar_menu, menu);
        return true;
    }

    @Override
    public void onRestart(){
        super.onRestart();
        this.finish();
        this.startActivity(getIntent());
    }

    public void onAddActionClick(MenuItem item) {
        Intent intent = new Intent(this, ListItemActivity.class);
        startActivity(intent);
    }

    public void populateArrayItems(){
        readItems();
        todoAdapter = new ListStoryAdapter(this, R.layout.list_story_view, todoItems);
    }

    private void readItems(){
        Log.e(LOG, "reading items from db");
        try {
            todoItems = db.getAllToDos();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
