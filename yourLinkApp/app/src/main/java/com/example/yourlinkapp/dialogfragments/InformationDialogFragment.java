package com.example.yourlinkapp.dialogfragments;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.utils.Constant;

public class InformationDialogFragment extends DialogFragment {
	private android.widget.TextView txtInformationBody;
	private Button btnInformationOk;
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog_information, container, false);
	}
	
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		
		android.os.Bundle bundle = getArguments();
		String message = bundle.getString(Constant.INFORMATION_MESSAGE);
		
		txtInformationBody = view.findViewById(R.id.txtInformationBody);
		txtInformationBody.setText(message);
		btnInformationOk = view.findViewById(R.id.btnInformationOk);
		btnInformationOk.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				dismiss();
			}
		});
	}
}
