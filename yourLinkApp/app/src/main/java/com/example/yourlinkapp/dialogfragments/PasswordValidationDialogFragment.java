package com.example.yourlinkapp.dialogfragments;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnPasswordValidationListener;
import com.example.yourlinkapp.utils.Constant;
import com.example.yourlinkapp.utils.SharedPrefsUtils;

public class PasswordValidationDialogFragment extends DialogFragment {
	private EditText txtValidationPassword;
	private Button btnValidation;
	private Button btnCancelValidation;
	private OnPasswordValidationListener onPasswordValidationListener;
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog_password_validation, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		onPasswordValidationListener = (OnPasswordValidationListener) getActivity();
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		
		txtValidationPassword = view.findViewById(R.id.txtValidationPassword);
		btnValidation = view.findViewById(R.id.btnValidation);
		btnCancelValidation = view.findViewById(R.id.btnCancelValidation);
		final String passwordPrefs = SharedPrefsUtils.getStringPreference(getContext(), Constant.PASSWORD, "");
		
		btnValidation.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				if (txtValidationPassword.getText().toString().equals(passwordPrefs)) {
					onPasswordValidationListener.onValidationOk();
					dismiss();
				} else {
					txtValidationPassword.requestFocus();
					txtValidationPassword.setError(getString(R.string.wrong_password));
				}
				
			}
		});
		
		
		btnCancelValidation.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				dismiss();
			}
		});
		
		
	}
}
