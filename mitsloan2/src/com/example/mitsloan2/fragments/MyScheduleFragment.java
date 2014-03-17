package com.example.mitsloan2.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.example.mitsloan2.GridActivity;
import com.example.mitsloan2.R;
import com.example.mitsloan2.SponsorsActivity;
import com.example.mitsloan2.adapters.ImageAdapter;
import com.example.mitsloan2.adapters.LazyAdapter;
import com.example.mitsloan2.constants.ScheduleElement;
import com.example.mitsloan2.sqlite.helper.DatabaseHelper;
import com.example.mitsloan2.sqlite.model.MyScheduleItem;
import com.example.mitsloan2.sqlite.model.ScheduleItem;
import com.example.mitsloan2.supportclasses.ImageLoader;
import com.example.mitsloan2.supportclasses.XMLParser;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MyScheduleFragment extends Fragment{
	private DatabaseHelper dbHelper;
	private static final String SONG_URL = "http://api.androidhive.info/music/music.xml";
    //myScheduleItem Table
    private MyScheduleItem myScheduleItem;
    public static final String LOG_ID = "MySpeakersList";
    private LazyAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//Intent displayImageGrid = new Intent(getActivity(), GridActivity.class);
        //startActivity(displayImageGrid);
		
		View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
    	//initialize dbhelper
    	dbHelper = new DatabaseHelper(rootView.getContext());
    	
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  	    StrictMode.setThreadPolicy(policy); //shortcut-should use Async Task here=execute RecieveSchedule
  	    
		List<Map<String, String>> scheduleList = new ArrayList<Map<String, String>>();
	    XMLParser parser = new XMLParser();
	    String xml = parser.getXmlFromUrl(SONG_URL);
	    Document doc = parser.getDomElement(xml);
	    NodeList nodeList = doc.getElementsByTagName(ScheduleElement.NODE_NAME);
	    int count = dbHelper.getMyScheduleItemCount();
	    String countString = Integer.toString(count);
	    Log.d(LOG_ID, countString);
	    //for (int i = 0, len = nodeList.getLength(); i < len; i++) {
	    //instead of hard-coding 22, use the getItemCount method to return the number of items in the myScheduleItem's Table
	    for (int i = 1, len = count+1; i < len; i++) {
	        Map<String, String> schedule_element = new TreeMap<String, String>();
	        Element e = (Element) nodeList.item(i);
	        //add each child node to Map key => value
//	        schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID));
//	        schedule_element.put(ScheduleElement.TITLE, parser.getValue(e, ScheduleElement.TITLE));
//	        schedule_element.put(ScheduleElement.ARTIST, parser.getValue(e, ScheduleElement.ARTIST));
//	        schedule_element.put(ScheduleElement.DURATION, parser.getValue(e, ScheduleElement.DURATION));
//	        schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));

            schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID)); //TODO: change to schduleItem ID
     		schedule_element.put(ScheduleElement.TITLE, dbHelper.getMyScheduleItem(i).getLocation());
    		schedule_element.put(ScheduleElement.ARTIST, dbHelper.getMyScheduleItem(i).getPanelDescription());
    		schedule_element.put(ScheduleElement.DURATION, dbHelper.getMyScheduleItem(i).getStartTime());
    		schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));
	        // add a myScheduleItem to List
	        scheduleList.add(schedule_element);
	    }
	    Log.d(LOG_ID, scheduleList.toString());
	
	    ListView listView = (ListView) rootView.findViewById(R.id.list);
	    // get an adapter by passing xml data list
	    adapter = new LazyAdapter(getActivity(), scheduleList);
	    //adapter = new LazyAdapter(this.getApplicationContext(), songList);
	    listView.setAdapter(adapter);
	    
	    // click event for single list row
	    listView.setOnItemClickListener(itemClickListener);
    	
    	return rootView;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		dbHelper = new DatabaseHelper(getView().getContext());
    	
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  	    StrictMode.setThreadPolicy(policy); //shortcut-should use Async Task here=execute RecieveSchedule
  	    
		List<Map<String, String>> scheduleList = new ArrayList<Map<String, String>>();
	    XMLParser parser = new XMLParser();
	    String xml = parser.getXmlFromUrl(SONG_URL);
	    Document doc = parser.getDomElement(xml);
	    NodeList nodeList = doc.getElementsByTagName(ScheduleElement.NODE_NAME);
	    int count = dbHelper.getMyScheduleItemCount();
	    String countString = Integer.toString(count);
	    Log.d(LOG_ID, countString);
	    //for (int i = 0, len = nodeList.getLength(); i < len; i++) {
	    //instead of hard-coding 22, use the getItemCount method to return the number of items in the myScheduleItem's Table
	    for (int i = 1, len = count+1; i < len; i++) {
	        Map<String, String> schedule_element = new TreeMap<String, String>();
	        Element e = (Element) nodeList.item(i);
	        //add each child node to Map key => value
//	        schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID));
//	        schedule_element.put(ScheduleElement.TITLE, parser.getValue(e, ScheduleElement.TITLE));
//	        schedule_element.put(ScheduleElement.ARTIST, parser.getValue(e, ScheduleElement.ARTIST));
//	        schedule_element.put(ScheduleElement.DURATION, parser.getValue(e, ScheduleElement.DURATION));
//	        schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));

            schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID)); //TODO: change to schduleItem ID
     		schedule_element.put(ScheduleElement.TITLE, dbHelper.getMyScheduleItem(i).getLocation());
    		schedule_element.put(ScheduleElement.ARTIST, dbHelper.getMyScheduleItem(i).getPanelDescription());
    		schedule_element.put(ScheduleElement.DURATION, dbHelper.getMyScheduleItem(i).getStartTime());
    		schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));
	        // add a myScheduleItem to List
	        scheduleList.add(schedule_element);
	    }
	    Log.d(LOG_ID, scheduleList.toString());
	
	    ListView listView = (ListView) getView().findViewById(R.id.list);
	    // get an adapter by passing xml data list
	    adapter = new LazyAdapter(getActivity(), scheduleList);
	    //adapter = new LazyAdapter(this.getApplicationContext(), songList);
	    listView.setAdapter(adapter);
	    
	    // click event for single list row
	    listView.setOnItemClickListener(itemClickListener);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Toast.makeText(getView().getContext(), "OnPause Initiated", Toast.LENGTH_SHORT);
		dbHelper = new DatabaseHelper(getView().getContext());
    	
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  	    StrictMode.setThreadPolicy(policy); //shortcut-should use Async Task here=execute RecieveSchedule
  	    
		List<Map<String, String>> scheduleList = new ArrayList<Map<String, String>>();
	    XMLParser parser = new XMLParser();
	    String xml = parser.getXmlFromUrl(SONG_URL);
	    Document doc = parser.getDomElement(xml);
	    NodeList nodeList = doc.getElementsByTagName(ScheduleElement.NODE_NAME);
	    int count = dbHelper.getMyScheduleItemCount();
	    String countString = Integer.toString(count);
	    Log.d(LOG_ID, countString);
	    //for (int i = 0, len = nodeList.getLength(); i < len; i++) {
	    //instead of hard-coding 22, use the getItemCount method to return the number of items in the myScheduleItem's Table
	    for (int i = 1, len = count+1; i < len; i++) {
	        Map<String, String> schedule_element = new TreeMap<String, String>();
	        Element e = (Element) nodeList.item(i);
	        //add each child node to Map key => value
//	        schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID));
//	        schedule_element.put(ScheduleElement.TITLE, parser.getValue(e, ScheduleElement.TITLE));
//	        schedule_element.put(ScheduleElement.ARTIST, parser.getValue(e, ScheduleElement.ARTIST));
//	        schedule_element.put(ScheduleElement.DURATION, parser.getValue(e, ScheduleElement.DURATION));
//	        schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));

            schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID)); //TODO: change to schduleItem ID
     		schedule_element.put(ScheduleElement.TITLE, dbHelper.getMyScheduleItem(i).getLocation());
    		schedule_element.put(ScheduleElement.ARTIST, dbHelper.getMyScheduleItem(i).getPanelDescription());
    		schedule_element.put(ScheduleElement.DURATION, dbHelper.getMyScheduleItem(i).getStartTime());
    		schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));
	        // add a myScheduleItem to List
	        scheduleList.add(schedule_element);
	    }
	    Log.d(LOG_ID, scheduleList.toString());
	
	    ListView listView = (ListView) getView().findViewById(R.id.list);
	    // get an adapter by passing xml data list
	    adapter = new LazyAdapter(getActivity(), scheduleList);
	    //adapter = new LazyAdapter(this.getApplicationContext(), songList);
	    listView.setAdapter(adapter);
	    
	    // click event for single list row
	    listView.setOnItemClickListener(itemClickListener);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		dbHelper = new DatabaseHelper(getView().getContext());
    	
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  	    StrictMode.setThreadPolicy(policy); //shortcut-should use Async Task here=execute RecieveSchedule
  	    
		List<Map<String, String>> scheduleList = new ArrayList<Map<String, String>>();
	    XMLParser parser = new XMLParser();
	    String xml = parser.getXmlFromUrl(SONG_URL);
	    Document doc = parser.getDomElement(xml);
	    NodeList nodeList = doc.getElementsByTagName(ScheduleElement.NODE_NAME);
	    int count = dbHelper.getMyScheduleItemCount();
	    String countString = Integer.toString(count);
	    Log.d(LOG_ID, countString);
	    //for (int i = 0, len = nodeList.getLength(); i < len; i++) {
	    //instead of hard-coding 22, use the getItemCount method to return the number of items in the myScheduleItem's Table
	    for (int i = 1, len = count+1; i < len; i++) {
	        Map<String, String> schedule_element = new TreeMap<String, String>();
	        Element e = (Element) nodeList.item(i);
	        //add each child node to Map key => value
//	        schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID));
//	        schedule_element.put(ScheduleElement.TITLE, parser.getValue(e, ScheduleElement.TITLE));
//	        schedule_element.put(ScheduleElement.ARTIST, parser.getValue(e, ScheduleElement.ARTIST));
//	        schedule_element.put(ScheduleElement.DURATION, parser.getValue(e, ScheduleElement.DURATION));
//	        schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));

            schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID)); //TODO: change to schduleItem ID
     		schedule_element.put(ScheduleElement.TITLE, dbHelper.getMyScheduleItem(i).getLocation());
    		schedule_element.put(ScheduleElement.ARTIST, dbHelper.getMyScheduleItem(i).getPanelDescription());
    		schedule_element.put(ScheduleElement.DURATION, dbHelper.getMyScheduleItem(i).getStartTime());
    		schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));
	        // add a myScheduleItem to List
	        scheduleList.add(schedule_element);
	    }
	    Log.d(LOG_ID, scheduleList.toString());
	
	    ListView listView = (ListView) getView().findViewById(R.id.list);
	    // get an adapter by passing xml data list
	    adapter = new LazyAdapter(getActivity(), scheduleList);
	    //adapter = new LazyAdapter(this.getApplicationContext(), songList);
	    listView.setAdapter(adapter);
	    
	    // click event for single list row
	    listView.setOnItemClickListener(itemClickListener);
	}
	
	

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	    dbHelper = new DatabaseHelper(getView().getContext());
    	
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  	    StrictMode.setThreadPolicy(policy); //shortcut-should use Async Task here=execute RecieveSchedule
  	    
		List<Map<String, String>> scheduleList = new ArrayList<Map<String, String>>();
	    XMLParser parser = new XMLParser();
	    String xml = parser.getXmlFromUrl(SONG_URL);
	    Document doc = parser.getDomElement(xml);
	    NodeList nodeList = doc.getElementsByTagName(ScheduleElement.NODE_NAME);
	    int count = dbHelper.getMyScheduleItemCount();
	    String countString = Integer.toString(count);
	    Log.d(LOG_ID, countString);
	    //for (int i = 0, len = nodeList.getLength(); i < len; i++) {
	    //instead of hard-coding 22, use the getItemCount method to return the number of items in the myScheduleItem's Table
	    for (int i = 1, len = count+1; i < len; i++) {
	        Map<String, String> schedule_element = new TreeMap<String, String>();
	        Element e = (Element) nodeList.item(i);
	        //add each child node to Map key => value
//	        schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID));
//	        schedule_element.put(ScheduleElement.TITLE, parser.getValue(e, ScheduleElement.TITLE));
//	        schedule_element.put(ScheduleElement.ARTIST, parser.getValue(e, ScheduleElement.ARTIST));
//	        schedule_element.put(ScheduleElement.DURATION, parser.getValue(e, ScheduleElement.DURATION));
//	        schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));

            schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID)); //TODO: change to schduleItem ID
     		schedule_element.put(ScheduleElement.TITLE, dbHelper.getMyScheduleItem(i).getLocation());
    		schedule_element.put(ScheduleElement.ARTIST, dbHelper.getMyScheduleItem(i).getPanelDescription());
    		schedule_element.put(ScheduleElement.DURATION, dbHelper.getMyScheduleItem(i).getStartTime());
    		schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));
	        // add a myScheduleItem to List
	        scheduleList.add(schedule_element);
	    }
	    Log.d(LOG_ID, scheduleList.toString());
	
	    ListView listView = (ListView) getView().findViewById(R.id.list);
	    // get an adapter by passing xml data list
	    adapter = new LazyAdapter(getActivity(), scheduleList);
	    //adapter = new LazyAdapter(this.getApplicationContext(), songList);
	    listView.setAdapter(adapter);
	    
	    // click event for single list row
	    listView.setOnItemClickListener(itemClickListener);
		
	}



	AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
    	@Override
    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    		Map<String, String> song = (Map<String, String>) adapter.getItem(position);
    		if (song != null) {
    			Log.d(LOG_ID, song.toString());

    			ImageView thumbImage = (ImageView) view.findViewById(R.id.list_image);
    			ImageLoader imageLoader = adapter.getImageLoader();
    			imageLoader.DisplayImage(song.get(ScheduleElement.THUMB_URL), thumbImage);
    			adapter.notifyDataSetInvalidated();
    		}
    	}
    };
	
}
