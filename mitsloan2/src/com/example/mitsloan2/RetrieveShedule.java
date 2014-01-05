package com.example.mitsloan2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.example.mitsloan2.adapters.LazyAdapter;
import com.example.mitsloan2.constants.ScheduleElement;
import com.example.mitsloan2.supportclasses.XMLParser;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class RetrieveShedule extends AsyncTask< String, String,String>{
	private static final String SONG_URL = "http://api.androidhive.info/music/music.xml";
	private Exception exception;
	public static final String LOG_ID = "SpeakersList";
	
	@Override
	protected String doInBackground(String... params) {
	/*	try{
			List<Map<String, String>> songList = new ArrayList<Map<String, String>>();
		    XMLParser parser = new XMLParser();
		    String xml = parser.getXmlFromUrl(SONG_URL);
		    Document doc = parser.getDomElement(xml);
		    NodeList nodeList = doc.getElementsByTagName(ScheduleElement.NODE_NAME);
		    for (int i = 0, len = nodeList.getLength(); i < len; i++) {
		        Map<String, String> schedule_element = new TreeMap<String, String>();
		        Element e = (Element) nodeList.item(i);
		        //add each child node to Map key => value
		        schedule_element.put(ScheduleElement.ID, parser.getValue(e, ScheduleElement.ID));
		        schedule_element.put(ScheduleElement.TITLE, parser.getValue(e, ScheduleElement.TITLE));
		        schedule_element.put(ScheduleElement.ARTIST, parser.getValue(e, ScheduleElement.ARTIST));
		        schedule_element.put(ScheduleElement.DURATION, parser.getValue(e, ScheduleElement.DURATION));
		        schedule_element.put(ScheduleElement.THUMB_URL, parser.getValue(e, ScheduleElement.THUMB_URL));
		        // add a song to List
		        songList.add(schedule_element);
		    }
		    Log.d(LOG_ID, songList.toString());
		
		    ListView listView = (ListView) rootView.findViewById(R.id.list);
		    // get an adapter by passing xml data list
		    adapter = new LazyAdapter(getActivity(), songList);
		    //adapter = new LazyAdapter(this.getApplicationContext(), songList);
		    listView.setAdapter(adapter);
		    
		    // click event for single list row
		    listView.setOnItemClickListener(itemClickListener);
		      
	    	
	        return rootView;
			
			return null;
		}catch(Exception e) {
            this.exception = e;
            return null;
        }*/	return null;	
	}


	
}
