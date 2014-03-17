package com.example.mitsloan2;

import com.example.mitsloan2.adapters.TabsPagerAdapter;
import com.example.mitsloan2.sqlite.helper.DatabaseHelper;
import com.example.mitsloan2.sqlite.model.MyScheduleItem;
import com.example.mitsloan2.sqlite.model.ScheduleItem;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreen extends ActionBarActivity implements TabListener{

	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private String HOMESCREEN = "HomeScreen";
    private ImageButton select_item;
	// Tab titles
    private String[] tabs = { "Schedule", "My Schedule", "Map" };
    //ScheduleItem 
    private ScheduleItem scheduleItem;
    //MyScheduleItem 
    private MyScheduleItem my_scheduleItem;
    
    //ListView UI Members
    private TextView scheduleTitle;
    private TextView scheduleTime;
    private TextView scheduleLocation;
    private ImageButton imageOne;
    private ImageButton imageTwo;
    
    //DATA
    private String[] startTimesArray = {"07:45 - 08:45","08:45 - 08:50",
    		"08:50 - 09:00","09:00 - 09:45", "9:45 - 09:55", 
    		"09:55 - 10:10", "10:10 - 10:55","10:55 - 11.05",
    		"11:05 - 11:15","11:15 - 12:15","12:15 - 12:30",
    		"12:30 - 13:30","13:30 - 14:30","14:30 - 14:45",
    		"14:45 - 14:55","14:55 - 15:40","15:40 - 15:55",
    		"15:55 - 16:10","16:10 - 17:10","17:10 - 17:25",
    		"17:25 - 17:40","17:40 - 18:10","18:10 - 18:25",
    		"18:25 - 18:40","18:40 - 20:10"}; //temporarily holds start and end time as one string
    private String[] endTimesArray = {" "," "," "," ", " ", 
    		" ", " ", " "," "," "," "," ","","",
    		" "," "," "," "," "," "," "," ","","",""};
    private String[] panelTitleArray = {""," "," "," ", " ", 
    		" ", " ", " "," "," ",
    		" "," "," ",
    		" "," ", " "," ",
    		" "," "," ",
    		" "," "," "," "," "};
    private String[] panelDescriptionArray = {"Registration and Breakfast","Opening Remarks","Welcome Address, Dean",
    		"Opening Keynote", "Vision Talk 1: Aboyeji Iyinoluwa", "Coffee Break", "PANEL: Incubators and Tech Hubs", 
    		"Panel Transition", "PANEL: Energy","Panel Transition","Vision Talk 2: Perihan Abouzeid","PANEL: Women in Africa",
    		"LUNCH","Vision Talk","Business Plan Introduction and Pitches","Panel Transition","PANEL: Healthcare",
    		"PANEL: Technology","Keynote #2","Business Plan Results", "Closing Remarks", "Networking & Career Reception"," "," "," "};
    private String[] speakersArray = {" "," "," "," ", " ", 
    		" ", " ", " "," "," "," "," "," "," ",
    		" "," "," "," "," "," "," "," "," "," "," "}; //should be array of imageByte[]'s
    private String[] locationArray = {"Registration and Breakfast"+"\n"+"Location: 6th Floor Reception","ABC Welcome Address"+"\n"+"Location: Main Room",
    		"Welcome Address"+"\n"+"Location: Main Room","Address 1: Ashish Thakkar"+"\n"+"Location: Main Room", 
    		"Vision Talk 1: Aboyeji Iyinolwa"+"\n"+"Location: Main Room","Coffee Break"+"\n"+ "Location: 6th Floor Reception", 
    		"Address 2: Madam Folorunso Alakija"+"\n"+"Location: Main Room", "Vision Talk 2: Perihan Abouzeid"+"\n"+"Location: Main Room",
    		"Coffee Break"+"\n"+"Location: 6th Floor Reception","Incubator Panel; EnergyPanel"+"\n"+"Location: TBD",
    		"Panel Transition"+"\n"+" ","Women in Africa Panel;Technology Panel"+"\n"+"Location: TBD",
    		"LUNCH"+"\n"+"Location: 6th Floor Reception","Transition"+"\n"+" ",
    		"Vision Talk 3: Ladi Delano"+"\n"+"Location: Main Room","Address 3: Alex Cummings"+"\n"+"Location: Main Room",
    		"Business Plan Pitches"+"\n"+"Location: Main Room","Panel Transition"+"\n"+" ",
    		"Finance Panel;Healthcare Panel"+"\n"+"Location:TBD","Panel Transition"+"\n"+" ",
    		"Vision Talk 4"+"\n"+"Location: Main Room", "Final Address: Bola Tinubu"+"\n"+ "Location: Main Room",
    		"Business Plan Results"+"\n"+"Location: Main Room", "Closing Remarks"+"\n"+"Location: Main Room",
    		"Networking & Career Reception"+"\n"+"Location: 6th Floor Reception" };
    private long schedItem_id;
 	
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        
        //initializing database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        
        Log.d(HOMESCREEN,"Inserting Schedule Items to Table");
        //TODO: Add Loading Spinner until this process is complete
        //      and/or start a new thread to Handle this
        //initializing Schedule Table and
        //inserting all items into schedule Table
        for (int i=0; i<((startTimesArray.length)-1); i++){
        	scheduleItem = new ScheduleItem(startTimesArray[i],
        			endTimesArray[i],
        			panelTitleArray[i], 
        			panelDescriptionArray[i],
        			speakersArray[i],
        			locationArray[i]);
        	
        	// Inserting ScheduleItems to Database
            Log.d(HOMESCREEN, "Inserting Master Schedule Items"); 
            //Log.d(HOMESCREEN, ((startTimesArray.length)-1).toString());
            Log.d(HOMESCREEN, scheduleItem.getStartTime().toString());
            Log.d(HOMESCREEN, scheduleItem.getPanelTitle().toString());
            schedItem_id = dbHelper.createScheduleItem(scheduleItem, null);
        }
        
        /*****************inserting into my_schedule table********************//*
        //initializing database
        DatabaseHelper dbHelper2 = new DatabaseHelper(this);
        
        Log.d(HOMESCREEN,"Inserting Schedule Items to Table");
        //TODO: Add Loading Spinner until this process is complete
        //      and/or start a new thread to Handle this
        //initializing Schedule Table and
        //inserting all items into schedule Table
        for (int i=0; i<3; i++){
        	my_scheduleItem = new MyScheduleItem(startTimesArray[i],
        			endTimesArray[i],
        			panelTitleArray[i], 
        			panelDescriptionArray[i],
        			speakersArray[i],
        			locationArray[i]);
        	
        	// Inserting ScheduleItems to Database
            Log.d(HOMESCREEN, "Inserting Master Schedule Items"); 
            //Log.d(HOMESCREEN, ((startTimesArray.length)-1).toString());
            Log.d(HOMESCREEN, my_scheduleItem.getStartTime().toString());
            Log.d(HOMESCREEN, my_scheduleItem.getPanelTitle().toString());
            schedItem_id = dbHelper2.createMyScheduleItem(my_scheduleItem, null);
        }
        *//********************************************************************/
         
        // Initialization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); 
        
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }
        
        /*select_item = (ImageButton) findViewById(R.id.feedback_thumbnail);
        select_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeScreen.this, "Item Added to MySchedule", Toast.LENGTH_SHORT).show();
            }
        });*/
        
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
       	 
    	    @Override
    	    public void onPageSelected(int position) {
    	        // on changing the page
    	        // make respected tab selected
    	        actionBar.setSelectedNavigationItem(position);
    	    }
    	 
    	    @Override
    	    public void onPageScrolled(int arg0, float arg1, int arg2) {
    	    }
    	 
    	    @Override
    	    public void onPageScrollStateChanged(int arg0) {
    	    }
    	});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflator = getMenuInflater();
        //inflator.inflate(R.menu.home_screen, menu);
        inflator.inflate(R.menu.main_activity_actions, menu);     
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
		if (itemId == R.id.action_search) {
			showSponors();
			return true;
		} else if (itemId == R.id.action_settings) {
			openSettings();
			return true;
		} else if (itemId == R.id.action_panels) {
			showPanels();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
    }
    
    public void showSpeakers(){
    	Intent speakers_intent = new Intent(this, SponsorsActivity.class);
    	startActivity(speakers_intent);
    }
    
    public void showSponors(){
    	Intent sponsors_intent = new Intent(this, SponsorsActivity.class);
    	startActivity(sponsors_intent);
    }
    
    public void showPanels(){
    	Intent panels_intent = new Intent(this, PanelSelector.class);
    	startActivity(panels_intent);
    }
    
    public void openSettings(){
    	Toast.makeText(this, "No Settings Available", Toast.LENGTH_SHORT);
    }


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	public void addToMySchedule(View v) {
		scheduleTitle = (TextView) findViewById(R.id.title);
		String schedule_title = (String) scheduleTitle.getText();
	    scheduleTime = (TextView) findViewById(R.id.duration);
	    String schedule_time = (String) scheduleTime.getText();
	    scheduleLocation = (TextView) findViewById(R.id.artist);
	    String schedule_location = (String) scheduleLocation.getText();
	    //imageOne = (ImageButton) findViewById(R.id.thumbnail);
	    //imageTwo = (ImageButton) findViewById(R.id.thumbnail2);
	    
	
		/*****************inserting into my_schedule table********************/
        //initializing database
        DatabaseHelper dbHelper2 = new DatabaseHelper(this);
        
        Log.d(HOMESCREEN,"Inserting Schedule Items to MyScheduleTable");
        //TODO: Add Loading Spinner until this process is complete
        //      and/or start a new thread to Handle this
        //initializing Schedule Table and
        //inserting all items into schedule Table
       
        my_scheduleItem = new MyScheduleItem(schedule_time," ", schedule_title, " "," ", schedule_location);        	
       	// Inserting ScheduleItems to Database
        Log.d(HOMESCREEN, "Inserting Master Schedule Items"); 
        //Log.d(HOMESCREEN, ((startTimesArray.length)-1).toString());
        Log.d(HOMESCREEN, "StartTime:"+my_scheduleItem.getStartTime().toString());
        Log.d(HOMESCREEN, "Location:"+my_scheduleItem.getPanelTitle().toString());
        schedItem_id = dbHelper2.createMyScheduleItem(my_scheduleItem, null);
       
        /********************************************************************/
		
        Toast.makeText(HomeScreen.this, "Item Added to MySchedule", Toast.LENGTH_SHORT).show();
    }
    
}
