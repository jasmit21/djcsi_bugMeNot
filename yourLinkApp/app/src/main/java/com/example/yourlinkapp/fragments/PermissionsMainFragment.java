package com.example.yourlinkapp.fragments;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnFragmentChangeListener;
import com.example.yourlinkapp.utils.Constant;

public class PermissionsMainFragment extends androidx.fragment.app.Fragment {
	private OnFragmentChangeListener onFragmentChangeListener;
	private Button btnPermissionsMainNext;
	
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_permissions_main, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		onFragmentChangeListener = (OnFragmentChangeListener) getActivity();
		
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			ImageView imgSecondDot = view.findViewById(R.id.imgSecondDot);
			imgSecondDot.setVisibility(android.view.View.GONE);
			ImageView imgThirdDot = view.findViewById(R.id.imgThirdDot);
			imgThirdDot.setVisibility(android.view.View.GONE);
			ImageView imgFourthDot = view.findViewById(R.id.imgFourthDot);
			imgFourthDot.setVisibility(android.view.View.GONE);
		}
		
		btnPermissionsMainNext = view.findViewById(R.id.btnPermissionsMainNext);
		btnPermissionsMainNext.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
					onFragmentChangeListener.onFragmentChange(Constant.PERMISSIONS_SETTINGS_FRAGMENT);
				else onFragmentChangeListener.onFragmentChange(Constant.PERMISSIONS_SMS_FRAGMENT);
				
			}
		});
		
		
	}
}
