package com.adil.meedoo.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.adil.meedoo.models.Priority;
import com.adil.meedoo.models.ToDo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by adil on 12/29/15.
 */
public class ToDoItemsDbHelper extends SQLiteOpenHelper{

    // Logcat tag
    private static final String LOG = ToDoItemsDbHelper.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "toDoDB";

    // Table Name
    private static final String TABLE_TODO = "todo";

    // Column names
    private static final String KEY_ID = "id";
    private static final String KEY_TEXT = "text";
    private static final String KEY_DUE_DATE = "due_date";
    private static final String KEY_PRIORITY = "priority";

    // Table Create Statements
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TEXT
            + " TEXT," + KEY_PRIORITY + " INTEGER," + KEY_DUE_DATE
            + " DATETIME" + ")";

    public ToDoItemsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    /**
     * Creating a todo
     */
    public long createToDo(ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEXT, todo.getText());
        values.put(KEY_PRIORITY, todo.getPriority().getPriorityCode());
        values.put(KEY_DUE_DATE, DateHelper.getDateAsString(todo.getDueDate()));

        long todo_id = db.insert(TABLE_TODO, null, values);

        return todo_id;
    }

    /**
     * get single todo
     */
    public ToDo getToDo(long todo_id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE "
                + KEY_ID + " = " + todo_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        int id = c.getInt(c.getColumnIndex(KEY_ID));
        String text = c.getString(c.getColumnIndex(KEY_TEXT));
        Date date = DateHelper.getStringAsDate(c.getString(c.getColumnIndex(KEY_DUE_DATE)));
        Priority priority =  Priority.getPriority(c.getInt(c.getColumnIndex(KEY_PRIORITY)));

        return new ToDo(id, text, date, priority);
    }

    /**
     * getting all todos
     * */
    public List<ToDo> getAllToDos() throws ParseException {
        List<ToDo> todos = new ArrayList<ToDo>();
        String selectQuery = "SELECT  * FROM " + TABLE_TODO;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex(KEY_ID));
                String text = c.getString(c.getColumnIndex(KEY_TEXT));
                Date date = DateHelper.getStringAsDate(c.getString(c.getColumnIndex(KEY_DUE_DATE)));
                Priority priority =  Priority.getPriority(c.getInt(c.getColumnIndex(KEY_PRIORITY)));

                todos.add(new ToDo(id, text, date, priority));
            } while (c.moveToNext());
        }

        return todos;
    }

    public int updateToDo(ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEXT, todo.getText());
        values.put(KEY_PRIORITY, todo.getPriority().getPriorityCode());
        values.put(KEY_DUE_DATE, DateHelper.getDateAsString(todo.getDueDate()));

        return db.update(TABLE_TODO, values, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getId()) });
    }

    /**
     * Deleting a todo
     */
    public void deleteToDo(long todo_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_ID + " = ?",
                new String[] { String.valueOf(todo_id) });
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
