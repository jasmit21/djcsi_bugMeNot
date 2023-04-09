package com.example.yourlinkapp.dialogfragments;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnPasswordChangeListener;
import com.example.yourlinkapp.utils.Constant;
import com.example.yourlinkapp.utils.SharedPrefsUtils;
import com.example.yourlinkapp.utils.Validators;

public class PasswordChangingDialogFragment extends DialogFragment {
	private EditText txtOldPassword;
	private EditText txtNewPassword;
	private EditText txtNewPasswordConfirmation;
	private Button btnChangePassword;
	private Button btnCancelChangePassword;
	private OnPasswordChangeListener onPasswordChangeListener;
	
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog_change_password, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		onPasswordChangeListener = (OnPasswordChangeListener) getActivity();
		
		txtOldPassword = view.findViewById(R.id.txtOldPassword);
		txtNewPassword = view.findViewById(R.id.txtNewPassword);
		txtNewPasswordConfirmation = view.findViewById(R.id.txtNewPasswordConfirmation);
		btnChangePassword = view.findViewById(R.id.btnChangePassword);
		btnChangePassword.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				if (isValid()) {
					onPasswordChangeListener.onPasswordChange(txtNewPassword.getText().toString());
					dismiss();
				}
				
			}
		});
		btnCancelChangePassword = view.findViewById(R.id.btnCancelChangePassword);
		btnCancelChangePassword.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				dismiss();
			}
		});
		
	}
	
	
	private boolean isValid() {
		if (!Validators.isValidPassword(txtOldPassword.getText().toString())) {
			txtOldPassword.setError(getString(R.string.wrong_password));
			txtOldPassword.requestFocus();
			return false;
		}
		
		if (!txtOldPassword.getText().toString().equals(SharedPrefsUtils.getStringPreference(getContext(), Constant.PASSWORD, ""))) {
			txtOldPassword.setError(getString(R.string.wrong_password));
			txtOldPassword.requestFocus();
			return false;
		}
		
		
		if (!Validators.isValidPassword(txtNewPassword.getText().toString())) {
			txtNewPassword.setError(getString(R.string.enter_valid_password));
			txtNewPassword.requestFocus();
			return false;
		}
		
		if (!txtNewPasswordConfirmation.getText().toString().equals(txtNewPassword.getText().toString())) {
			txtNewPasswordConfirmation.setError(getString(R.string.new_password_doesnt_match));
			txtNewPasswordConfirmation.requestFocus();
			return false;
		}
		
		return true;
	}
}

