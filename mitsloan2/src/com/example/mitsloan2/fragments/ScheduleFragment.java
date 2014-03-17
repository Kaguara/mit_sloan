package com.example.mitsloan2.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.example.mitsloan2.FullImageActivity;
import com.example.mitsloan2.R;
import com.example.mitsloan2.R.id;
import com.example.mitsloan2.SpeakerDetailBioActivity;
import com.example.mitsloan2.adapters.LazyAdapter;
import com.example.mitsloan2.constants.ScheduleElement;
import com.example.mitsloan2.sqlite.helper.DatabaseHelper;
import com.example.mitsloan2.sqlite.model.ScheduleItem;
import com.example.mitsloan2.supportclasses.ImageLoader;
import com.example.mitsloan2.supportclasses.XMLParser;

import org.w3c.dom.Element;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ScheduleFragment extends Fragment{
	public static final String LOG_ID = "SpeakersList";
	private static final String SONG_URL = "http://api.androidhive.info/music/music.xml";
    private LazyAdapter adapter;
    private static final boolean DEVELOPER_MODE = true;
    private Exception exception;
    private DatabaseHelper dbHelper;
    //ScheduleItem Table
    private ScheduleItem scheduleItem;
    //Imageview
    ImageButton imageOne; 
	
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
    	//initialize dbhelper
    	dbHelper = new DatabaseHelper(rootView.getContext());
    	
    	/*imageOne = (ImageButton) rootView.findViewById(R.id.list_image2);
    	
    	*//**
         * On Click event for Single ImageButton Item
         * *//*
        imageOne.setOnClickListener(new OnClickListener() {
      
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
        		// Sending image id to FullScreenActivity
        		int image_id = v.findViewById(R.id.list_image2).getId();
                Intent i = new Intent(v.getContext(), SpeakerDetailBioActivity.class);
                // passing array index
                i.putExtra("image_id", image_id);
                startActivity(i);	
			}
        });
    	*/
    	
    	MyAsyncTask getdata = new MyAsyncTask();   //  For Launching the 
    	getdata.execute(SONG_URL);                 //   async thread
    	
    	/*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  	    StrictMode.setThreadPolicy(policy); //shortcut-should use Async Task here=execute RecieveSchedule
  	    
		List<Map<String, String>> scheduleList = new ArrayList<Map<String, String>>();
	    XMLParser parser = new XMLParser();
	    String xml = parser.getXmlFromUrl(SONG_URL);
	    Document doc = parser.getDomElement(xml);
	    NodeList nodeList = doc.getElementsByTagName(ScheduleElement.NODE_NAME);
	    int count = dbHelper.getScheduleItemCount();
	    String countString = Integer.toString(count);
	    Log.d(LOG_ID, "ScheduleTable Items Count:"+countString);
	    //for (int i = 0, len = nodeList.getLength(); i < len; i++) {
	    //instead of hard-coding 22, use the getItemCount method to return the number of items in the ScheduleItem's Table
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
     		schedule_element.put(ScheduleElement.TITLE, dbHelper.getScheduleItem(i).getLocation());
    		schedule_element.put(ScheduleElement.ARTIST, dbHelper.getScheduleItem(i).getPanelDescription());
    		schedule_element.put(ScheduleElement.DURATION, dbHelper.getScheduleItem(i).getStartTime());
    		schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));
	        // add a scheduleItem to List
	        scheduleList.add(schedule_element);
	    }
	    Log.d(LOG_ID, scheduleList.toString());
	
	    ListView listView = (ListView) rootView.findViewById(R.id.list);
	    // get an adapter by passing xml data list
	    adapter = new LazyAdapter(getActivity(), scheduleList);
	    //adapter = new LazyAdapter(this.getApplicationContext(), songList);
	    listView.setAdapter(adapter);
	    
	    // click event for single list row
	    listView.setOnItemClickListener(itemClickListener);*/
	      
    	
        return rootView;
	}
    

    @Override
	public void onResume() {
		// TODO Auto-generated method stub
    	super.onResume();
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

    @Override
    public void onDestroy() {
    	super.onDestroy();

    	// release resource
    	adapter.clear();
    }
    
    /* This function starts the asynchronous task to get the 
     * images and data stored in the back-end.
     * */
 
    //Async Class to asynchronously load the data from our backend
    public class MyAsyncTask extends AsyncTask<String, Void, List<Map<String, String>>>{
    	private Exception exception;
    	protected List<Map<String, String>> doInBackground(String...urls){
    		List<Map<String, String>> scheduleList = new ArrayList<Map<String, String>>();
    		for (String url : urls){
    			try{
    				XMLParser parser = new XMLParser();
    				String xml = parser.getXmlFromUrl(url);
    				Document doc = parser.getDomElement(xml);
    				NodeList nodeList = doc.getElementsByTagName(ScheduleElement.NODE_NAME);
    				int count = dbHelper.getScheduleItemCount();
 
    				String countString = Integer.toString(count);
    			    Log.d(LOG_ID, "ScheduleTable Items Count:"+countString);
    			    //for (int i = 0, len = nodeList.getLength(); i < len; i++) {
    			    //instead of hard-coding 22, use the getItemCount method to return the number of items in the ScheduleItem's Table
    				for (int i = 1, len = 26; i < len; i++) {
    					Map<String, String> schedule_element = new TreeMap<String, String>();
    					Element e = (Element) nodeList.item(i);
    					//add each child node to Map key => value
    					
//    					schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID));
//    					schedule_element.put(ScheduleElement.TITLE, parser.getValue(e, ScheduleElement.TITLE));
//    					schedule_element.put(ScheduleElement.ARTIST, parser.getValue(e, ScheduleElement.ARTIST));
//    					schedule_element.put(ScheduleElement.DURATION, parser.getValue(e, ScheduleElement.DURATION));
//    					schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));
    					
    					schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID)); //TODO: change to schduleItem ID
    		     		schedule_element.put(ScheduleElement.TITLE, dbHelper.getScheduleItem(i).getLocation());
    		    		schedule_element.put(ScheduleElement.ARTIST, dbHelper.getScheduleItem(i).getPanelDescription());
    		    		schedule_element.put(ScheduleElement.DURATION, dbHelper.getScheduleItem(i).getStartTime());
    		    		schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));
    			        // add a scheduleItem to List
    			        scheduleList.add(schedule_element);

    				}
    				Log.d(LOG_ID, scheduleList.toString());

    				return scheduleList;
    			}catch(Exception e) {
    				this.exception = e;
    				return null;
    			}
    		}
    		return scheduleList;
    	}

	@Override
	protected void onPostExecute(List<Map<String, String>> result) {
		    ListView listView = (ListView) getActivity().findViewById(R.id.list);
		    // get an adapter by passing xml data list
		    adapter = new LazyAdapter(getActivity(), result);
		    //adapter = new LazyAdapter(this.getApplicationContext(), songList);
		    listView.setAdapter(adapter);
		    
		    // click event for single list row
		    listView.setOnItemClickListener(itemClickListener);
		super.onPostExecute(result);
	}
       
       
    }
	
}
