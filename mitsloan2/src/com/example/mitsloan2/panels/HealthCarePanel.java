package com.example.mitsloan2.panels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.mitsloan2.FeedbackActivity;
import com.example.mitsloan2.R;
import com.example.mitsloan2.adapters.HealthCarePanelImageAdapter;

public class HealthCarePanel extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speakers_grid);
		
		//setting the app icon as the UP button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		GridView gridView = (GridView) findViewById(R.id.panel_grid_view);
		 
        // Instance of ImageAdapter Class
        gridView.setAdapter(new HealthCarePanelImageAdapter(this));
	}
	
	public void launchFeedack(View v){
		Intent feedback_intent = new Intent(this, FeedbackActivity.class);
    	startActivity(feedback_intent);
	}


}
