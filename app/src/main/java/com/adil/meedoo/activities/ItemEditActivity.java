package com.adil.meedoo.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.adil.meedoo.R;
import com.adil.meedoo.helpers.ToDoItemsDbHelper;
import com.adil.meedoo.helpers.DateHelper;
import com.adil.meedoo.models.Priority;
import com.adil.meedoo.models.ToDo;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class ItemEditActivity extends AppCompatActivity {

    Spinner prioritySpinner;
    ArrayAdapter<Priority> listStoryAdapter;
    EditText toDoEditText;
    EditText dateEditText;
    DatePickerDialog dateDialog;
    ToDo toDoObject;
    ToDoItemsDbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        findViewsById();
        db = new ToDoItemsDbHelper(getApplicationContext());

        toDoObject = null;
        Intent intent = this.getIntent();
        if (intent.hasExtra(MainActivity.INTENT_EXTRA_OBJECT)){
            toDoObject = (ToDo) intent.getSerializableExtra(MainActivity.INTENT_EXTRA_OBJECT);
            preloadViewData(toDoObject);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.item_action_bar_menu, menu);
        return true;
    }

    private void findViewsById(){
        listStoryAdapter = new ArrayAdapter<Priority>(this, android.R.layout.simple_spinner_item, Priority.values());

        prioritySpinner = (Spinner) findViewById(R.id.prioritySpinner);
        prioritySpinner.setAdapter(listStoryAdapter);

        toDoEditText = (EditText) findViewById(R.id.itemDescription);

        dateEditText = (EditText) findViewById(R.id.dateDialog);
        dateEditText.setInputType(InputType.TYPE_NULL);
        dateEditText.requestFocus();

        setDateField();
    }

    private void preloadViewData(ToDo td){
        toDoEditText.setText(td.getText());
        prioritySpinner.setSelection(listStoryAdapter.getPosition(td.getPriority()));
        dateEditText.setText(DateHelper.getDateAsString(td.getDueDate()));
    }

    private void setDateField(){
        Calendar newCalendar = Calendar.getInstance();
        dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateEditText.setText(DateHelper.getDateAsString(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    public void onSaveActionClick(MenuItem item) throws ParseException {
        String text = toDoEditText.getText().toString();
        Date date = DateHelper.getStringAsDate(dateEditText.getText().toString());
        Priority priority = (Priority) prioritySpinner.getSelectedItem();

        if (toDoObject == null) {
            toDoObject = new ToDo(text, date, priority);
            db.createToDo(toDoObject);
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        } else {
            toDoObject.setText(text);
            toDoObject.setDueDate(date);
            toDoObject.setPriority(priority);
            db.updateToDo(toDoObject);
            Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
        }

        this.finish();
    }

    public void onDeleteActionClick(MenuItem item) {
        if (toDoObject != null){
            db.deleteToDo(toDoObject.getId());
        }

        this.finish();
    }

    public void onDateDialogClick(View view) {
        dateDialog.show();
    }
}
