package com.example.mitsloan2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SpeakersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speakers);
		
		//setting the app icon as the UP button
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.speakers, menu);
		return true;
	}
	
	

}
