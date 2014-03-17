package com.example.mitsloan2;

import com.example.mitsloan2.adapters.ImageAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class SponsorsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sponsors_grid);
		
		//setting the app icon as the UP button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		GridView gridView = (GridView) findViewById(R.id.grid_view);
		 
        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));
        
        /**
         * On Click event for Single Gridview Item
         * */
        gridView.setOnItemClickListener(new OnItemClickListener() {
            
        	@Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
 
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent home_intent = new Intent(this, HomeScreen.class);
    	startActivity(home_intent);
	}
	
	
	
}
