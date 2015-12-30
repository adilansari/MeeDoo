package com.adil.meedoo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.adil.meedoo.helpers.DatabaseHelper;
import com.adil.meedoo.models.Priority;
import com.adil.meedoo.models.ToDo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ToDo> todoItems;
    ListStoryAdapter todoAdapter;
    ListView lvItems;
    EditText etEditText;
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
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                todoAdapter.notifyDataSetChanged();
                writeItems();
                return false;
            }
        });

        db.closeDB();
    }

    public void populateArrayItems(){
        readItems();
        todoAdapter = new ListStoryAdapter(this, R.layout.list_story_view, todoItems);
    }

    public void onAddItem(View view) {
        ToDo td = new ToDo(etEditText.getText().toString(), new Date(), Priority.LOW);
        db.createToDo(td);
//        todoAdapter.add(etEditText.getText().toString());
        etEditText.setText("");
//        writeItems();
        todoAdapter.notifyDataSetChanged();
    }

    private void readItems(){
        Log.e(LOG, "reading items from db");
        try {
            todoItems = db.getAllToDos();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void writeItems(){
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(file, todoItems);
        } catch (IOException e) {

        }
    }
}
