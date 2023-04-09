package com.example.yourlinkapp.dialogfragments;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnGeoFenceSettingListener;
import com.example.yourlinkapp.utils.Validators;

import static com.example.yourlinkapp.activities.ParentSignedInActivity.CHILD_NAME_EXTRA;

public class GeoFenceSettingDialogFragment extends DialogFragment {
	private android.widget.Spinner spinnerGeoFenceEntries;
	private EditText txtGeoFenceDiameter;
	//private TextView txtGeoFenceHeader;
	private android.widget.TextView txtGeoFenceBody;
	private Button btnSetGeoFence;
	private Button btnCancelSetGeoFence;
	private OnGeoFenceSettingListener onGeoFenceSettingListener;
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog_geo_fence, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		onGeoFenceSettingListener = (OnGeoFenceSettingListener) getTargetFragment();
		
		spinnerGeoFenceEntries = view.findViewById(R.id.spinnerGeoFenceEntries);
		txtGeoFenceDiameter = view.findViewById(R.id.txtGeoFenceDiameter);

        /*txtGeoFenceHeader = (TextView) view.findViewById(R.id.txtGeoFenceHeader);
        String header = getResources().getString(R.string.setup_a_geofence) + " " + getString(R.string.on) + " " + getChildName();
        txtGeoFenceHeader.setText(header);*/
		
		txtGeoFenceBody = view.findViewById(R.id.txtGeoFenceBody);
		String body = getResources().getString(R.string.setup_geo_fence_on) + " " + getChildName() + getResources().getString(R.string.if_he_exceeded_it_you_will_be_alerted);
		txtGeoFenceBody.setText(body);
		
		btnSetGeoFence = view.findViewById(R.id.btnSetGeoFence);
		btnSetGeoFence.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				String selectedEntry = spinnerGeoFenceEntries.getSelectedItem().toString();
				String geoFenceDiameter = txtGeoFenceDiameter.getText().toString();
				
				if (!Validators.isValidGeoFenceDiameter(geoFenceDiameter)) {
					txtGeoFenceDiameter.setError(getResources().getString(R.string.please_enter_a_valid_diameter));
					txtGeoFenceDiameter.requestFocus();
				} else {
					onGeoFenceSettingListener.onGeoFenceSet(selectedEntry, Double.parseDouble(geoFenceDiameter));
					dismiss();
				}
				
			}
		});
		
		btnCancelSetGeoFence = view.findViewById(R.id.btnCancelSetGeoFence);
		btnCancelSetGeoFence.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				dismiss();
			}
		});
		
		
	}
	
	private String getChildName() {
		android.os.Bundle bundle = getActivity().getIntent().getExtras();
		String childName = null;
		if (bundle != null) {
			childName = bundle.getString(CHILD_NAME_EXTRA);
		}
		return childName;
	}
}
