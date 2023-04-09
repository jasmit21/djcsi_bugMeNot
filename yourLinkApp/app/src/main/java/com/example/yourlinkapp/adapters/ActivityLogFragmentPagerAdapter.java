package com.example.yourlinkapp.adapters;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ActivityLogFragmentPagerAdapter extends FragmentPagerAdapter {
	private java.util.List<androidx.fragment.app.Fragment> fragmentList = new ArrayList<>();
	private java.util.List<String> fragmentTitleList = new ArrayList<>();
	
	public ActivityLogFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	public void addFragment(androidx.fragment.app.Fragment fragment, String title) {
		fragmentList.add(fragment);
		fragmentTitleList.add(title);
	}
	
	@Override
	public androidx.fragment.app.Fragment getItem(int i) {
		return fragmentList.get(i);
	}
	
	@Override
	public int getCount() {
		return fragmentList.size();
	}
	
	@androidx.annotation.Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return fragmentTitleList.get(position);
	}
}
