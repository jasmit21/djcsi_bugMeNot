package com.example.yourlinkapp.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.example.yourlinkapp.R;
import com.example.yourlinkapp.adapters.ActivityLogFragmentPagerAdapter;

public class ActivityLogFragment extends androidx.fragment.app.Fragment {
	public static final String TAG = "ActivityLogTAG";
    /*private HashMap<String, Message> messages;
    private HashMap<String, Call> calls;
    private ArrayList<Contact> contacts;*/
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_activity_log, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		//getData();
		
		ViewPager viewPager = view.findViewById(R.id.activityLogViewPager);
		viewPager.setAdapter(setupActivityLogFragmentPagerAdapter());
		
		TabLayout tabLayout = view.findViewById(R.id.activityLogTabLayout);
		tabLayout.setupWithViewPager(viewPager);
		
		
	}

    /*private void getData() {
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            messages = (HashMap<String, Message>) bundle.getSerializable(CHILD_MESSAGES_EXTRA);
            calls = (HashMap<String, Call>) bundle.getSerializable(CHILD_CALLS_EXTRA);
            contacts = bundle.getParcelableArrayList(Constant.CHILD_CONTACTS_EXTRA);

        }

        for (String key : messages.keySet()) {
            Log.i(TAG, "getData: messageBody: " + messages.get(key).getMessageBody());
            Log.i(TAG, "getData: senderPhoneNumber: " + messages.get(key).getSenderPhoneNumber());
            Log.i(TAG, "getData: timeReceived: " + messages.get(key).getTimeReceived());
        }
    }*/
	
	private PagerAdapter setupActivityLogFragmentPagerAdapter() {
		ActivityLogFragmentPagerAdapter pagerAdapter = new ActivityLogFragmentPagerAdapter(getActivity().getSupportFragmentManager());
		pagerAdapter.addFragment(new CallsFragment(), getResources().getString(R.string.calls));
		pagerAdapter.addFragment(new MessagesFragment(), getResources().getString(R.string.messages));
		pagerAdapter.addFragment(new ContactsFragment(), getResources().getString(R.string.contacts));
		
		return pagerAdapter;
	}
}
