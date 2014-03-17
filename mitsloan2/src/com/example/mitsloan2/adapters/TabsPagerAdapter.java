package com.example.mitsloan2.adapters;

import com.example.mitsloan2.fragments.MapFragment;
import com.example.mitsloan2.fragments.ScheduleFragment;
import com.example.mitsloan2.fragments.MyScheduleFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		// TODO Auto-generated method stub
		switch (index) {
        case 0:
            // Top Rated fragment activity
            return new ScheduleFragment();
        case 1:
            // Games fragment activity
            return new MyScheduleFragment();
        case 2:
            // Movies fragment activity
            return new MapFragment();
        }
 
        return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	
	

}
