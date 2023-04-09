package com.example.yourlinkapp.dialogfragments;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnAppClickListener;
import com.example.yourlinkapp.utils.Constant;

public class AppLockDialogFragment extends DialogFragment {
	private Button btnLockApp;
	private Button btnCancelLockApp;
	private android.widget.Spinner spinnerLockAppEntries;
	private LinearLayout layoutLockAppTime;
	private EditText txtLockAppHours;
	private EditText txtLockAppMinutes;
	private android.widget.TextView txtLockAppHeader;
	private OnAppClickListener onAppClickListener;
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog_lock_app, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		onAppClickListener = (OnAppClickListener) getTargetFragment();
		android.os.Bundle bundle = getArguments();
		String appName = bundle.getString(Constant.CHILD_APP_NAME_EXTRA);
		String headerText = "Block " + appName;
		
		txtLockAppHeader = view.findViewById(R.id.txtLockAppHeader);
		txtLockAppHeader.setText(headerText);
		
		btnLockApp = view.findViewById(R.id.btnLockApp);
		btnCancelLockApp = view.findViewById(R.id.btnCancelLockApp);
		layoutLockAppTime = view.findViewById(R.id.layoutLockAppTime);
		txtLockAppHours = view.findViewById(R.id.txtLockAppHours);
		txtLockAppMinutes = view.findViewById(R.id.txtLockAppMinutes);
		spinnerLockAppEntries = view.findViewById(R.id.spinnerLockAppEntries);
		spinnerLockAppEntries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
				if (position == 0) {
					layoutLockAppTime.setVisibility(android.view.View.GONE);
				} else if (position == 1) {
					layoutLockAppTime.setVisibility(android.view.View.VISIBLE);
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				layoutLockAppTime.setVisibility(android.view.View.GONE);
				
			}
		});
		
		btnLockApp.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (spinnerLockAppEntries.getSelectedItemPosition() == 0) {
					//onAppClickListener.onLockAppSet(0, 0);
					dismiss();
					
				} else if (spinnerLockAppEntries.getSelectedItemPosition() == 1) {
					if (isValid()) {
						int hours = Integer.parseInt(txtLockAppHours.getText().toString());
						int minutes = Integer.parseInt(txtLockAppMinutes.getText().toString());
						//onAppClickListener.onLockAppSet(hours, minutes);
						dismiss();
					}
					
				}
			}
		});
		
		btnCancelLockApp.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				//onAppClickListener.onLockCanceled();
				dismiss();
			}
		});
	}
	
	private boolean isValid() {
		if (txtLockAppHours.getText().toString().equals("")) {
			txtLockAppHours.setError(getString(R.string.enter_a_valid_number));
			return false;
		}
		if (txtLockAppMinutes.getText().toString().equals("")) {
			txtLockAppMinutes.setError(getString(R.string.enter_a_valid_number));
			return false;
		}
		if (Integer.parseInt(txtLockAppHours.getText().toString()) > 23) {
			txtLockAppHours.setError(getString(R.string.maximum_is_23_hours));
			return false;
		}
		if (Integer.parseInt(txtLockAppMinutes.getText().toString()) > 59) {
			txtLockAppMinutes.setError(getString(R.string.maximum_is_59_minutes));
			return false;
		}
		
		return true;
	}
}
