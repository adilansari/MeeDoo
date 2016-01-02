package com.adil.meedoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    public static final String INTENT_EXTRA_OBJECT = "toDoObject";
    private static final String LOG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(getApplicationContext());

        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.list_story_view);
        lvItems.setAdapter(todoAdapter);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG, "getting to create new intent.");
                ToDo td = (ToDo) parent.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, ListItemActivity.class);
                Log.d(LOG, "sending intent to load current activity");
                intent.putExtra(INTENT_EXTRA_OBJECT, td);
                startActivity(intent);
            }
        });

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
        Log.d(LOG, "debug reading items from db");
        Log.e(LOG, "error reading items from db");
        Log.i(LOG, "info reading items from db");
        Log.v(LOG, "verbose reading items from db");
        try {
            todoItems = db.getAllToDos();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
