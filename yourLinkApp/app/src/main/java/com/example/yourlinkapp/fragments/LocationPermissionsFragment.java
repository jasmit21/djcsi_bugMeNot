package com.example.yourlinkapp.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.dialogfragments.InformationDialogFragment;
import com.example.yourlinkapp.dialogfragments.PermissionExplanationDialogFragment;
import com.example.yourlinkapp.interfaces.OnFragmentChangeListener;
import com.example.yourlinkapp.interfaces.OnPermissionExplanationListener;
import com.example.yourlinkapp.utils.Constant;

public class LocationPermissionsFragment extends androidx.fragment.app.Fragment implements OnPermissionExplanationListener {
	private android.widget.Switch switchLocationPermission;
	private Context context;
	private Activity activity;
	private android.view.View layout;
	private FragmentManager fragmentManager;
	private OnFragmentChangeListener onFragmentChangeListener;
	private Button btnPermissionsLocationPrev;
	private Button btnPermissionsLocationNext;
	
	@Override
	public void onOk(int requestCode) {
		if (requestCode == Constant.LOCATION_PERMISSION_REQUEST_CODE) {
			requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, Constant.LOCATION_PERMISSION_REQUEST_CODE);
			
		}
		
	}
	
	@Override
	public void onCancel(int switchId) {
		android.widget.Switch pressedSwitch = layout.findViewById(switchId);
		pressedSwitch.setChecked(false);
		
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
		if (requestCode == Constant.LOCATION_PERMISSION_REQUEST_CODE) {
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				Toast.makeText(context, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show();
				switchLocationPermission.setChecked(true);
				//switchLocationPermission.setEnabled(false);
				
			} else {
				Toast.makeText(context, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
				switchLocationPermission.setChecked(false);
			}
			
		}
	}
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_permissions_location, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		context = getContext();
		activity = getActivity();
		layout = view;
		fragmentManager = getFragmentManager();
		onFragmentChangeListener = (OnFragmentChangeListener) activity;
		
		btnPermissionsLocationNext = view.findViewById(R.id.btnPermissionsLocationNext);
		btnPermissionsLocationNext.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (isLocationPermissionGranted() /*|| isLocationPermissionDeniedForEver()*/)//TODO:: OK?
					onFragmentChangeListener.onFragmentChange(Constant.PERMISSIONS_SETTINGS_FRAGMENT);
				else
					startInformationDialogFragment(getString(R.string.please_allow_location_permission));
				
				
			}
		});
		
		btnPermissionsLocationPrev = view.findViewById(R.id.btnPermissionsLocationPrev);
		btnPermissionsLocationPrev.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				onFragmentChangeListener.onFragmentChange(Constant.PERMISSIONS_PHONE_CALLS_FRAGMENT);
			}
		});
		
		switchLocationPermission = view.findViewById(R.id.switchLocationPermission);
		switchLocationPermission.setChecked(isLocationPermissionGranted());
		switchLocationPermission.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					requestLocationPermissions();
				}
			}
		});
		
	}
	
	private void requestLocationPermissions() {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
				startPermissionExplanationFragment(Constant.LOCATION_PERMISSION_REQUEST_CODE, switchLocationPermission.getId());
			} else {
				requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, Constant.LOCATION_PERMISSION_REQUEST_CODE);
			}
			
		}
	}
	
	private void startPermissionExplanationFragment(int requestCode, int id) {
		PermissionExplanationDialogFragment explanationFragment = new PermissionExplanationDialogFragment();
		android.os.Bundle bundle = new android.os.Bundle();
		bundle.putInt(Constant.PERMISSION_REQUEST_CODE, requestCode);
		bundle.putInt(Constant.SWITCH_ID, id);
		explanationFragment.setArguments(bundle);
		explanationFragment.setCancelable(false);
		explanationFragment.setTargetFragment(this, Constant.PERMISSION_EXPLANATION_FRAGMENT);
		explanationFragment.show(fragmentManager, Constant.PERMISSION_EXPLANATION_FRAGMENT_TAG);
	}
	
	private boolean isLocationPermissionGranted() {
		return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
	}
	
	private void startInformationDialogFragment(String message) {
		InformationDialogFragment informationDialogFragment = new InformationDialogFragment();
		android.os.Bundle bundle = new android.os.Bundle();
		bundle.putString(Constant.INFORMATION_MESSAGE, message);
		informationDialogFragment.setArguments(bundle);
		informationDialogFragment.setCancelable(false);
		informationDialogFragment.show(getChildFragmentManager(), Constant.INFORMATION_DIALOG_FRAGMENT_TAG);
	}
	
	private boolean isLocationPermissionDeniedForEver() {
		return !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION);
	}
}
