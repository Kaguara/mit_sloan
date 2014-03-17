package com.example.mitsloan2.sqlite.helper;

import java.util.ArrayList;
import java.util.List;

import com.example.mitsloan2.sqlite.model.MyScheduleItem;
import com.example.mitsloan2.sqlite.model.ScheduleItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	// Logcat tag
    private static final String LOG = "DatabaseHelper";
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "scheduleManager";
 
    // Table Names
    private static final String TABLE_SCHEDULE = "schedule";
    private static final String TABLE_MY_SCHEDULE = "my_schedule";
    private static final String TABLE_SPEAKER = "speaker";
    private static final String TABLE_SPEAKER_SCHEDULE_REL = "speaker_schedule_rel";
    
 
    // Common Table column names
    private static final String KEY_ID = "id";
    
    // SCHEDULE Table column names
    private static final String KEY_START_TIME = "start_time";
    private static final String KEY_END_TIME = "end_time";
    private static final String KEY_PANEL_TITLE = "panel_title";
    private static final String KEY_PANEL_DESCRIPTION = "panel_description";
    private static final String KEY_SPEAKERS = "speakers";
    private static final String KEY_LOCATION = "location";
    
    // MY_SCHEDULE Table column names
    private static final String MY_KEY_START_TIME = "my_start_time";
    private static final String MY_KEY_END_TIME = "my_end_time";
    private static final String MY_KEY_PANEL_TITLE = "my_panel_title";
    private static final String MY_KEY_PANEL_DESCRIPTION = "my_panel_description";
    private static final String MY_KEY_SPEAKERS = "my_speakers";
    private static final String MY_KEY_LOCATION = "my_location";
    
    // SPEAKER Table column names
    private static final String KEY_NAME = "speaker_name";
    private static final String KEY_TITLE = "speaker_title";
    private static final String KEY_DESCRIPTION = "speaker_description";
    private static final String KEY_CONTACTS = "speaker_contacts";
    private static final String KEY_PHOTO = "speaker_photo";
    
    // SPEAKER_SCHEDULE_REL Table column names
    private static final String KEY_SPEAKER_ID = "speaker_id";
    private static final String KEY_SCHEDULE_ID = "schedule_id";
       
    // Table Create Statements
    // Schedule table create statement
    private static final String CREATE_TABLE_SCHEDULE = "CREATE TABLE "
            + TABLE_SCHEDULE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_START_TIME
            + " TEXT," + KEY_END_TIME + " TEXT," + KEY_PANEL_TITLE + " TEXT," + 
            KEY_PANEL_DESCRIPTION + " TEXT," + KEY_SPEAKERS + " TEXT," + KEY_LOCATION
            + " TEXT" + ")"; //date_time format " DATETIME"
    
   // My_Schedule table create statement
    private static final String CREATE_TABLE_MY_SCHEDULE = "CREATE TABLE "
            + TABLE_MY_SCHEDULE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + MY_KEY_START_TIME
            + " TEXT," + MY_KEY_END_TIME + " TEXT," + MY_KEY_PANEL_TITLE + " TEXT," + 
            MY_KEY_PANEL_DESCRIPTION + " TEXT," + MY_KEY_SPEAKERS + " TEXT," + MY_KEY_LOCATION
            + " TEXT" + ")"; //date_time format " DATETIME"
    
    // Speaker table create statement
    private static final String CREATE_TABLE_SPEAKER = "CREATE TABLE "
            + TABLE_SPEAKER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_TITLE + " TEXT," + KEY_DESCRIPTION + " TEXT," + 
            KEY_CONTACTS + " TEXT," + KEY_PHOTO + " TEXT" + ")";
    
    // Speaker_Schedule_Rel table create statement
    private static final String CREATE_TABLE_SPEAKER_SCHEDULE_REL = "CREATE TABLE "
            + TABLE_SPEAKER_SCHEDULE_REL + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_START_TIME
            + " TEXT," + KEY_SPEAKER_ID + " INTEGER," + KEY_SCHEDULE_ID + " INTEGER" + ")";
 
 
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        // creating required tables
        db.execSQL(CREATE_TABLE_SCHEDULE);
        db.execSQL(CREATE_TABLE_MY_SCHEDULE);
        db.execSQL(CREATE_TABLE_SPEAKER);
        db.execSQL(CREATE_TABLE_SPEAKER_SCHEDULE_REL);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPEAKER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPEAKER_SCHEDULE_REL);     
     // create new tables
        onCreate(db);
    }
    
    //****CRUD (Create, Read, Update and Delete) Operations*****
    
  // ------------------------ "schedule" table methods ----------------//
    
  /**
   * Creating a scheduleItem
   */
   public long createScheduleItem(ScheduleItem schedule, long[] tag_ids) {
       SQLiteDatabase db = this.getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(KEY_START_TIME, schedule.getStartTime());
       values.put(KEY_END_TIME, schedule.getEndTime());
       values.put(KEY_PANEL_TITLE, schedule.getPanelTitle());
       values.put(KEY_PANEL_DESCRIPTION, schedule.getPanelDescription());
       values.put(KEY_SPEAKERS, schedule.getSpeakers());
       values.put(KEY_LOCATION, schedule.getLocation());

       // insert row
       long schedule_item_id = db.insert(TABLE_SCHEDULE, null, values);

       return schedule_item_id;
   }
   
   /**
    * get a single ScheduleItem
    */
   public ScheduleItem getScheduleItem(long sched_id) {
       SQLiteDatabase db = this.getReadableDatabase();

       String selectQuery = "SELECT  * FROM " + TABLE_SCHEDULE + " WHERE "
               + KEY_ID + " = " + sched_id;

       Log.e(LOG, selectQuery);

       Cursor c = db.rawQuery(selectQuery, null);

       if (c != null)
           c.moveToFirst();

       ScheduleItem scheduleItem = new ScheduleItem();
       scheduleItem.setId(c.getInt(c.getColumnIndex(KEY_ID))); //**
       scheduleItem.setStartTime((c.getString(c.getColumnIndex(KEY_START_TIME))));
       scheduleItem.setEndTime(c.getString(c.getColumnIndex(KEY_END_TIME)));
       scheduleItem.setPanelTitle(c.getString(c.getColumnIndex(KEY_PANEL_TITLE)));
       scheduleItem.setPanelDescription(c.getString(c.getColumnIndex(KEY_PANEL_DESCRIPTION)));
       scheduleItem.setSpeakers(c.getString(c.getColumnIndex(KEY_SPEAKERS)));
       scheduleItem.setLocation(c.getString(c.getColumnIndex(KEY_LOCATION)));
   
       return scheduleItem;
   }

   /**
    * getting all scheduleItems
    * */
   public List<ScheduleItem> getAllScheduleItems() {
       List<ScheduleItem> schedule_items = new ArrayList<ScheduleItem>();
       String selectQuery = "SELECT  * FROM " + TABLE_SCHEDULE;

       Log.e(LOG, selectQuery);

       SQLiteDatabase db = this.getReadableDatabase();
       Cursor c = db.rawQuery(selectQuery, null);

       // looping through all rows and adding to list
       if (c.moveToFirst()) {
           do {
        	   ScheduleItem scheduleItem = new ScheduleItem();
        	   scheduleItem.setId(c.getInt(c.getColumnIndex(KEY_ID))); //***
               scheduleItem.setStartTime((c.getString(c.getColumnIndex(KEY_START_TIME))));
               scheduleItem.setEndTime(c.getString(c.getColumnIndex(KEY_END_TIME)));
               scheduleItem.setPanelTitle(c.getString(c.getColumnIndex(KEY_PANEL_TITLE)));
               scheduleItem.setPanelDescription(c.getString(c.getColumnIndex(KEY_PANEL_DESCRIPTION)));
               scheduleItem.setSpeakers(c.getString(c.getColumnIndex(KEY_SPEAKERS)));
               scheduleItem.setLocation(c.getString(c.getColumnIndex(KEY_LOCATION)));

               // adding to scheduleItem list
               schedule_items.add(scheduleItem);
           } while (c.moveToNext());
       }

       return schedule_items;
   }

   /**
    * getting all schedule items under a single tag
    * */
   /* public List<ScheduleItem> getAllToDosByTag(String tag_name) {
       List<ScheduleItem> todos = new ArrayList<ScheduleItem>();

       String selectQuery = "SELECT  * FROM " + TABLE_TODO + " td, "
               + TABLE_TAG + " tg, " + TABLE_TODO_TAG + " tt WHERE tg."
               + KEY_TAG_NAME + " = '" + tag_name + "'" + " AND tg." + KEY_ID
               + " = " + "tt." + KEY_TAG_ID + " AND td." + KEY_ID + " = "
               + "tt." + KEY_TODO_ID;

       Log.e(LOG, selectQuery);

       SQLiteDatabase db = this.getReadableDatabase();
       Cursor c = db.rawQuery(selectQuery, null);

       // looping through all rows and adding to list
       if (c.moveToFirst()) {
           do {
               Todo td = new Todo();
               td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
               td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
               td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

               // adding to todo list
               todos.add(td);
           } while (c.moveToNext());
       }

       return todos;
   }*/

   /**
    * getting schedule Item
    */
   public int getScheduleItemCount() {
       String countQuery = "SELECT  * FROM " + TABLE_SCHEDULE;
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(countQuery, null);

       int count = cursor.getCount();
       cursor.close();

       // return count
       return count;
   }

   /**
    * Updating a scheduleItem
    */
   public int updateScheduleItem(ScheduleItem scheduleItem) {
       SQLiteDatabase db = this.getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(KEY_START_TIME, scheduleItem.getStartTime());
       values.put(KEY_END_TIME, scheduleItem.getEndTime());
       values.put(KEY_PANEL_TITLE, scheduleItem.getPanelTitle());
       values.put(KEY_PANEL_DESCRIPTION, scheduleItem.getPanelDescription());
       values.put(KEY_SPEAKERS, scheduleItem.getSpeakers());
       values.put(KEY_LOCATION, scheduleItem.getLocation());

       // updating row
       return db.update(TABLE_SCHEDULE, values, KEY_ID + " = ?",
               new String[] { String.valueOf(scheduleItem.getId()) });
   }

   /**
    * Deleting a scheduleItem
    */
   public void deleteScheduleItem(long scheduleItem_id) {
       SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE_SCHEDULE, KEY_ID + " = ?",
               new String[] { String.valueOf(scheduleItem_id) });
   }
   
//**********************************************************************//
// ------------------------ "my_schedule" table methods ----------------//
   
  /**
   * Creating a scheduleItem
   */
   public long createMyScheduleItem(MyScheduleItem schedule, long[] tag_ids) {
       SQLiteDatabase db = this.getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(MY_KEY_START_TIME, schedule.getStartTime());
       values.put(MY_KEY_END_TIME, schedule.getEndTime());
       values.put(MY_KEY_PANEL_TITLE, schedule.getPanelTitle());
       values.put(MY_KEY_PANEL_DESCRIPTION, schedule.getPanelDescription());
       values.put(MY_KEY_SPEAKERS, schedule.getSpeakers());
       values.put(MY_KEY_LOCATION, schedule.getLocation());

       // insert row
       long schedule_item_id = db.insert(TABLE_MY_SCHEDULE, null, values);

       return schedule_item_id;
   }
   
   /**
    * get a single ScheduleItem
    */
   public ScheduleItem getMyScheduleItem(long sched_id) {
       SQLiteDatabase db = this.getReadableDatabase();

       String selectQuery = "SELECT  * FROM " + TABLE_MY_SCHEDULE + " WHERE "
               + KEY_ID + " = " + sched_id;

       Log.e(LOG, selectQuery);

       Cursor c = db.rawQuery(selectQuery, null);

       if (c != null)
           c.moveToFirst();

       ScheduleItem scheduleItem = new ScheduleItem();
       scheduleItem.setId(c.getInt(c.getColumnIndex(KEY_ID))); //**
       scheduleItem.setStartTime((c.getString(c.getColumnIndex(MY_KEY_START_TIME))));
       scheduleItem.setEndTime(c.getString(c.getColumnIndex(MY_KEY_END_TIME)));
       scheduleItem.setPanelTitle(c.getString(c.getColumnIndex(MY_KEY_PANEL_TITLE)));
       scheduleItem.setPanelDescription(c.getString(c.getColumnIndex(MY_KEY_PANEL_DESCRIPTION)));
       scheduleItem.setSpeakers(c.getString(c.getColumnIndex(MY_KEY_SPEAKERS)));
       scheduleItem.setLocation(c.getString(c.getColumnIndex(MY_KEY_LOCATION)));
   
       return scheduleItem;
   }

   /**
    * getting all scheduleItems
    * */
   public List<ScheduleItem> getAllMyScheduleItems() {
       List<ScheduleItem> schedule_items = new ArrayList<ScheduleItem>();
       String selectQuery = "SELECT  * FROM " + TABLE_MY_SCHEDULE;

       Log.e(LOG, selectQuery);

       SQLiteDatabase db = this.getReadableDatabase();
       Cursor c = db.rawQuery(selectQuery, null);

       // looping through all rows and adding to list
       if (c.moveToFirst()) {
           do {
        	   ScheduleItem scheduleItem = new ScheduleItem();
        	   scheduleItem.setId(c.getInt(c.getColumnIndex(KEY_ID))); //***
               scheduleItem.setStartTime((c.getString(c.getColumnIndex(MY_KEY_START_TIME))));
               scheduleItem.setEndTime(c.getString(c.getColumnIndex(MY_KEY_END_TIME)));
               scheduleItem.setPanelTitle(c.getString(c.getColumnIndex(MY_KEY_PANEL_TITLE)));
               scheduleItem.setPanelDescription(c.getString(c.getColumnIndex(MY_KEY_PANEL_DESCRIPTION)));
               scheduleItem.setSpeakers(c.getString(c.getColumnIndex(MY_KEY_SPEAKERS)));
               scheduleItem.setLocation(c.getString(c.getColumnIndex(MY_KEY_LOCATION)));

               // adding to scheduleItem list
               schedule_items.add(scheduleItem);
           } while (c.moveToNext());
       }

       return schedule_items;
   }

   /**
    * getting all schedule items under a single tag
    * */
   /* public List<ScheduleItem> getAllMyToDosByTag(String tag_name) {
       List<ScheduleItem> todos = new ArrayList<ScheduleItem>();

       String selectQuery = "SELECT  * FROM " + TABLE_MY_SCHEDULE + " td, "
               + TABLE_TAG + " tg, " + TABLE_TODO_TAG + " tt WHERE tg."
               + KEY_TAG_NAME + " = '" + tag_name + "'" + " AND tg." + KEY_ID
               + " = " + "tt." + KEY_TAG_ID + " AND td." + KEY_ID + " = "
               + "tt." + KEY_TODO_ID;

       Log.e(LOG, selectQuery);

       SQLiteDatabase db = this.getReadableDatabase();
       Cursor c = db.rawQuery(selectQuery, null);

       // looping through all rows and adding to list
       if (c.moveToFirst()) {
           do {
               Todo td = new Todo();
               td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
               td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
               td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

               // adding to todo list
               todos.add(td);
           } while (c.moveToNext());
       }

       return todos;
   }*/

   /**
    * getting schedule Item
    */
   public int getMyScheduleItemCount() {
       String countQuery = "SELECT  * FROM " + TABLE_MY_SCHEDULE;
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(countQuery, null);

       int count = cursor.getCount();
       cursor.close();

       // return count
       return count;
   }

   /**
    * Updating a scheduleItem
    */
   public int updateMyScheduleItem(ScheduleItem scheduleItem) {
       SQLiteDatabase db = this.getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(MY_KEY_START_TIME, scheduleItem.getStartTime());
       values.put(MY_KEY_END_TIME, scheduleItem.getEndTime());
       values.put(MY_KEY_PANEL_TITLE, scheduleItem.getPanelTitle());
       values.put(MY_KEY_PANEL_DESCRIPTION, scheduleItem.getPanelDescription());
       values.put(MY_KEY_SPEAKERS, scheduleItem.getSpeakers());
       values.put(MY_KEY_LOCATION, scheduleItem.getLocation());

       // updating row
       return db.update(TABLE_MY_SCHEDULE, values, KEY_ID + " = ?",
               new String[] { String.valueOf(scheduleItem.getId()) });
   }

   /**
    * Deleting a scheduleItem
    */
   public void deleteMyScheduleItem(long scheduleItem_id) {
       SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE_MY_SCHEDULE, KEY_ID + " = ?",
               new String[] { String.valueOf(scheduleItem_id) });
   }

}

	
