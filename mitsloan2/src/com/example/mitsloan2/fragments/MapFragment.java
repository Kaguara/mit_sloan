package com.example.mitsloan2.fragments;

import com.example.mitsloan2.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MapFragment extends Fragment{
	
	// Google Map
    private GoogleMap googleMap;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_indoor_map, container, false);
		//Initialize the Map.
		/*try {
            // Loading map
            initilizeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }*/
		return rootView;
	}
	
	/**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
    	
    	android.support.v4.app.FragmentManager myFragmentManager = getFragmentManager();
        SupportMapFragment mySupportMapFragment = (SupportMapFragment)myFragmentManager.findFragmentById(R.id.map);


         googleMap = mySupportMapFragment.getMap();
         if(googleMap!=null){
        	 googleMap.setMyLocationEnabled(true);
         }
        /*if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap(); //.getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(this.getActivity(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show(); //getApplicationContext()
            }
        }*/
    }
 
    @Override
	public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        
    }
    
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            googleMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (googleMap != null) {
            	initilizeMap();
            }
        }
    }

	

}
