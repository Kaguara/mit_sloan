package com.example.mitsloan2;

import com.example.mitsloan2.adapters.ImageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class GridActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		GridView gridView = (GridView) findViewById(R.id.grid_view);
		 
	    // Instance of ImageAdapter Class
	    gridView.setAdapter(new ImageAdapter(this));
	}
		

}
