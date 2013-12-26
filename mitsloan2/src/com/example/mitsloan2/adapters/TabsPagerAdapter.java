package com.example.mitsloan2.adapters;

import com.example.mitsloan2.fragments.ConnectionsFragment;
import com.example.mitsloan2.fragments.ScheduleFragment;
import com.example.mitsloan2.fragments.SpeakersFragment;

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
            return new SpeakersFragment();
        case 2:
            // Movies fragment activity
            return new ConnectionsFragment();
        }
 
        return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	
	

}
