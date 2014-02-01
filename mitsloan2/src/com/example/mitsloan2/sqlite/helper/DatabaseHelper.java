package com.example.mitsloan2.sqlite.helper;

import com.example.mitsloan2.sqlite.model.ScheduleModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	// Logcat tag
    private static final String LOG = "DatabaseHelper";
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "scheduleManager";
 
    // Table Names
    private static final String TABLE_SCHEDULE = "schedule";
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
    
 // Speaker table create statement
    private static final String CREATE_TABLE_SPEAKER = "CREATE TABLE "
            + TABLE_SPEAKER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_TITLE + " TEXT," + KEY_DESCRIPTION + " TEXT," + 
            KEY_CONTACTS + " TEXT," + KEY_PHOTO + " TEXT" + ")";
    
 // Speaker_Schedule_Rel table create statement
    private static final String CREATE_TABLE_SPEAKER_SCHEDULE_REL = "CREATE TABLE "
            + TABLE_SPEAKER_SCHEDULE_REL + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_START_TIME
            + " TEXT," + KEY_SPEAKER_ID + " INTEGER," + KEY_SCHEDULE_ID + " INTEGER," + ")";
 
 
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        // creating required tables
        db.execSQL(CREATE_TABLE_SCHEDULE);
        db.execSQL(CREATE_TABLE_SPEAKER);
        db.execSQL(CREATE_TABLE_SPEAKER_SCHEDULE_REL);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPEAKER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPEAKER_SCHEDULE_REL);     
     // create new tables
        onCreate(db);
    }
    
    //****CRUD (Create, Read, Update and Delete) Operations*****
    
 // ------------------------ "schedule" table methods ----------------//
    
    /**
    * Creating a todo
    */
   public long createToDo(ScheduleModel schedule, long[] tag_ids) {
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

}

	
