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

public class SMSPermissionsFragment extends androidx.fragment.app.Fragment implements CompoundButton.OnCheckedChangeListener, OnPermissionExplanationListener {
	private android.widget.Switch switchSendSmsPermission;
	private android.widget.Switch switchReadSmsPermission;
	private android.widget.Switch switchReceiveSmsPermission;
	private Context context;
	private Activity activity;
	private android.view.View layout;
	private FragmentManager fragmentManager;
	private OnFragmentChangeListener onFragmentChangeListener;
	private Button btnPermissionsSmsNext;
	private Button btnPermissionsSmsPrev;
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
				case R.id.switchSendSmsPermission:
					requestSendSmsPermission();
					break;
				
				case R.id.switchReadSmsPermission:
					requestReadSmsPermission();
					break;
				
				case R.id.switchReceiveSmsPermission:
					requestReceiveSmsPermission();
					break;
			}
		}
	}
	
	//TODO:: use ActivityCompat when in an activity and requestPermissions when in a fragment
	private void requestSendSmsPermission() {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
			if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS)) {
				startPermissionExplanationFragment(Constant.SEND_SMS_PERMISSION_REQUEST_CODE, switchSendSmsPermission.getId());
			} else {
				requestPermissions(new String[]{Manifest.permission.SEND_SMS}, Constant.SEND_SMS_PERMISSION_REQUEST_CODE);
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
	
	private void requestReadSmsPermission() {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
			if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_SMS)) {
				startPermissionExplanationFragment(Constant.READ_SMS_PERMISSION_REQUEST_CODE, switchReadSmsPermission.getId());
				
			} else {
				requestPermissions(new String[]{Manifest.permission.READ_SMS}, Constant.READ_SMS_PERMISSION_REQUEST_CODE);
			}
			
		}
	}
	
	private void requestReceiveSmsPermission() {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
			if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_SMS)) {
				startPermissionExplanationFragment(Constant.RECEIVE_SMS_PERMISSION_REQUEST_CODE, switchReceiveSmsPermission.getId());
			} else {
				requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, Constant.RECEIVE_SMS_PERMISSION_REQUEST_CODE);
			}
			
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
		switch (requestCode) {
			case Constant.SEND_SMS_PERMISSION_REQUEST_CODE:
				if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Toast.makeText(context, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show();
					switchSendSmsPermission.setChecked(true);
					//switchSendSmsPermission.setEnabled(false);
					
				} else {
					Toast.makeText(context, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
					switchSendSmsPermission.setChecked(false);
				}
				
				break;
			
			case Constant.READ_SMS_PERMISSION_REQUEST_CODE:
				if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Toast.makeText(context, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show();
					switchReadSmsPermission.setChecked(true);
					//switchReadSmsPermission.setEnabled(false);
					
				} else {
					Toast.makeText(context, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
					switchReadSmsPermission.setChecked(false);
				}
				
				break;
			
			case Constant.RECEIVE_SMS_PERMISSION_REQUEST_CODE:
				if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Toast.makeText(context, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show();
					switchReceiveSmsPermission.setChecked(true);
					//switchReceiveSmsPermission.setEnabled(false);
					
				} else {
					Toast.makeText(context, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
					switchReceiveSmsPermission.setChecked(false);
				}
				
				break;
		}
	}
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_permissions_sms, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		context = getContext();
		activity = getActivity();
		layout = view;
		fragmentManager = getFragmentManager();
		onFragmentChangeListener = (OnFragmentChangeListener) activity;
		
		btnPermissionsSmsNext = view.findViewById(R.id.btnPermissionsSmsNext);
		btnPermissionsSmsNext.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (checkAllPermissions())
					onFragmentChangeListener.onFragmentChange(Constant.PERMISSIONS_PHONE_CALLS_FRAGMENT);
				else startInformationDialogFragment(getString(R.string.please_allow_permissions));
			}
		});
		
		btnPermissionsSmsPrev = view.findViewById(R.id.btnPermissionsSmsPrev);
		btnPermissionsSmsPrev.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				onFragmentChangeListener.onFragmentChange(Constant.PERMISSIONS_MAIN_FRAGMENT);
			}
		});
		
		switchSendSmsPermission = view.findViewById(R.id.switchSendSmsPermission);
		switchSendSmsPermission.setChecked(isSendSmsPermissionGranted());
		switchSendSmsPermission.setOnCheckedChangeListener(this);
		switchReadSmsPermission = view.findViewById(R.id.switchReadSmsPermission);
		switchReadSmsPermission.setChecked(isReadSmsPermissionGranted());
		switchReadSmsPermission.setOnCheckedChangeListener(this);
		switchReceiveSmsPermission = view.findViewById(R.id.switchReceiveSmsPermission);
		switchReceiveSmsPermission.setChecked(isReceiveSmsPermissionGranted());
		switchReceiveSmsPermission.setOnCheckedChangeListener(this);
		
		
	}
	
	private boolean checkAllPermissions() {
		return isReadSmsPermissionGranted() && isSendSmsPermissionGranted() && isReceiveSmsPermissionGranted();
	}
	
	private boolean isSendSmsPermissionGranted() {
		return ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
	}
	
	private boolean isReadSmsPermissionGranted() {
		return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
	}
	
	private boolean isReceiveSmsPermissionGranted() {
		return ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
		
	}
	
	private void startInformationDialogFragment(String message) {
		InformationDialogFragment informationDialogFragment = new InformationDialogFragment();
		android.os.Bundle bundle = new android.os.Bundle();
		bundle.putString(Constant.INFORMATION_MESSAGE, message);
		informationDialogFragment.setArguments(bundle);
		informationDialogFragment.setCancelable(false);
		informationDialogFragment.show(getChildFragmentManager(), Constant.INFORMATION_DIALOG_FRAGMENT_TAG);
	}
	
	@Override
	public void onOk(int requestCode) {
		switch (requestCode) {
			case Constant.SEND_SMS_PERMISSION_REQUEST_CODE:
				requestPermissions(new String[]{Manifest.permission.SEND_SMS}, Constant.SEND_SMS_PERMISSION_REQUEST_CODE);
				break;
			case Constant.READ_SMS_PERMISSION_REQUEST_CODE:
				requestPermissions(new String[]{Manifest.permission.READ_SMS}, Constant.READ_SMS_PERMISSION_REQUEST_CODE);
				break;
			case Constant.RECEIVE_SMS_PERMISSION_REQUEST_CODE:
				requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, Constant.RECEIVE_SMS_PERMISSION_REQUEST_CODE);
				break;
		}
		
	}
	
	@Override
	public void onCancel(int switchId) {
		android.widget.Switch pressedSwitch = layout.findViewById(switchId);
		pressedSwitch.setChecked(false);
	}
}
