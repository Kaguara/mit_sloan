package com.example.mitsloan2.fragments;

import com.example.mitsloan2.GridActivity;
import com.example.mitsloan2.R;
import com.example.mitsloan2.SpeakersActivity;
import com.example.mitsloan2.adapters.ImageAdapter;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class SpeakersFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//Intent displayImageGrid = new Intent(getActivity(), GridActivity.class);
        //startActivity(displayImageGrid);
		
		View rootView = inflater.inflate(R.layout.fragment_myschedule, container, false);
		return rootView;
		
	}
	
}
