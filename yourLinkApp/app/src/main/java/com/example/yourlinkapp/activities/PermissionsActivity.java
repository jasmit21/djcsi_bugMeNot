package com.example.yourlinkapp.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.fragments.LocationPermissionsFragment;
import com.example.yourlinkapp.fragments.PermissionsMainFragment;
import com.example.yourlinkapp.fragments.PhoneCallsPermissionsFragment;
import com.example.yourlinkapp.fragments.SMSPermissionsFragment;
import com.example.yourlinkapp.fragments.SettingsPermissionsFragment;
import com.example.yourlinkapp.interfaces.OnFragmentChangeListener;
import com.example.yourlinkapp.utils.Constant;
import com.example.yourlinkapp.utils.SharedPrefsUtils;

public class PermissionsActivity extends AppCompatActivity implements OnFragmentChangeListener {
	
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_permissions);
		
		getSupportFragmentManager().beginTransaction().replace(R.id.permissionFragmentContainer, new PermissionsMainFragment()).commit();
		
		
	}
	
	@Override
	public void onFragmentChange(int id) {
		androidx.fragment.app.Fragment selectedFragment = null;
		switch (id) {
			case Constant.PERMISSIONS_MAIN_FRAGMENT:
				selectedFragment = new PermissionsMainFragment();
				break;
			case Constant.PERMISSIONS_SMS_FRAGMENT:
				selectedFragment = new SMSPermissionsFragment();
				break;
			case Constant.PERMISSIONS_PHONE_CALLS_FRAGMENT:
				selectedFragment = new PhoneCallsPermissionsFragment();
				break;
			case Constant.PERMISSIONS_LOCATION_FRAGMENT:
				selectedFragment = new LocationPermissionsFragment();
				break;
			case Constant.PERMISSIONS_SETTINGS_FRAGMENT:
				selectedFragment = new SettingsPermissionsFragment();
				break;
			case Constant.PERMISSIONS_FRAGMENTS_FINISH:
				SharedPrefsUtils.setBooleanPreference(this, Constant.CHILD_FIRST_LAUNCH, false);
				Intent intent = new Intent(this, ChildSignedInActivity.class);
				startActivity(intent);
				break;
		}
		
		if (selectedFragment != null)
			getSupportFragmentManager().beginTransaction().replace(R.id.permissionFragmentContainer, selectedFragment).commit();
		
	}
	
	@Override
	public void onBackPressed() {
		//NO going back to childSignedIn activity
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}
