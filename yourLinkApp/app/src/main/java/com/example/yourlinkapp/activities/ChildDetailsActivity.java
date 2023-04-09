package com.example.yourlinkapp.activities;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.yourlinkapp.R;
import com.example.yourlinkapp.fragments.ActivityLogFragment;
import com.example.yourlinkapp.fragments.AppsFragment;
import com.example.yourlinkapp.fragments.LocationFragment;
import com.example.yourlinkapp.models.App;

import java.util.ArrayList;

import static com.example.yourlinkapp.activities.ParentSignedInActivity.APPS_EXTRA;
import static com.example.yourlinkapp.activities.ParentSignedInActivity.CHILD_NAME_EXTRA;

public class ChildDetailsActivity extends AppCompatActivity {
	private static final String TAG = "ChildDetailsTAG";
	private ArrayList<App> apps;
	private ImageButton btnBack;
	private ImageButton btnSettings;
	private android.widget.TextView txtTitle;
	
	
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_child_details);
		
		btnBack = findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				onBackPressed();
			}
		});
		btnSettings = findViewById(R.id.btnSettings);
		btnSettings.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				Intent intent = new Intent(ChildDetailsActivity.this, SettingsActivity.class);
				startActivity(intent);
			}
		});
		txtTitle = findViewById(R.id.txtTitle);
		
		Intent intent = getIntent();
		String childName = intent.getStringExtra(CHILD_NAME_EXTRA);
		//final String childEmail = intent.getStringExtra(CHILD_EMAIL_EXTRA);
		apps = intent.getParcelableArrayListExtra(APPS_EXTRA);
        /*for (App app : apps) {
            Log.i(TAG, "onItemClick: appName: " + app.getAppName() + " " + "packageName" + app.getPackageName());

        }*/
		
		//setTitle(childName + "'s device");
		String title = childName + getString(R.string.upper_dot_s) + " " + getString(R.string.device);
		txtTitle.setText(title);
		
		getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new AppsFragment()).commit();
		
		BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
		bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@androidx.annotation.NonNull MenuItem menuItem) {
				androidx.fragment.app.Fragment selectedFragment = null;
				
				android.os.Bundle bundle = new android.os.Bundle();
				
				switch (menuItem.getItemId()) {
					case R.id.navApps:
						selectedFragment = new AppsFragment();
						//bundle.putParcelableArrayList(APPS_EXTRA, apps);  //not needed since we're sending it from
						//selectedFragment.setArguments(bundle);            //the ParentSignedInActivity
						break;
					case R.id.navLocation:
						selectedFragment = new LocationFragment();
						//bundle.putString(CHILD_EMAIL_EXTRA, childEmail);
						//selectedFragment.setArguments(bundle);
						break;
					case R.id.navActivityLog:
						selectedFragment = new ActivityLogFragment();
						break;
					
				}
				
				getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
				return true;
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		startActivity(new Intent(this, ParentSignedInActivity.class));
	}
}
