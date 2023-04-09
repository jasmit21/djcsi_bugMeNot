package com.example.yourlinkapp.dialogfragments;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnConfirmationListener;
import com.example.yourlinkapp.utils.Constant;

public class ConfirmationDialogFragment extends DialogFragment {
	private Button btnConfirm;
	private Button btnCancelConfirm;
	private android.widget.TextView txtConfirmationBody;
	private OnConfirmationListener onConfirmationListener;
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog_confirmation, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		onConfirmationListener = (OnConfirmationListener) getActivity();
		
		android.os.Bundle bundle = getArguments();
		String confirmationMessage = bundle.getString(Constant.CONFIRMATION_MESSAGE);
		
		txtConfirmationBody = view.findViewById(R.id.txtConfirmationBody);
		txtConfirmationBody.setText(confirmationMessage);
		
		btnConfirm = view.findViewById(R.id.btnConfirm);
		btnConfirm.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				onConfirmationListener.onConfirm();
				dismiss();
			}
			
		});
		
		btnCancelConfirm = view.findViewById(R.id.btnCancelConfirm);
		btnCancelConfirm.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				onConfirmationListener.onConfirmationCancel();
				dismiss();
			}
		});
		
	}
}
