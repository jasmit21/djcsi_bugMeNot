package com.example.yourlinkapp.dialogfragments;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnPasswordResetListener;
import com.example.yourlinkapp.utils.Validators;

public class RecoverPasswordDialogFragment extends DialogFragment {
	private EditText txtRecoveryEmail;
	private Button btnRecoverPassword;
	private Button btnCancelRecoverPassword;
	private OnPasswordResetListener onPasswordResetListener;
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog_recover_password, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		onPasswordResetListener = (OnPasswordResetListener) getActivity();
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		txtRecoveryEmail = view.findViewById(R.id.txtRecoveryEmail);
		
		
		btnRecoverPassword = view.findViewById(R.id.btnRecoverPassword);
		btnRecoverPassword.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				String email = txtRecoveryEmail.getText().toString();
				if (Validators.isValidEmail(email)) {
					onPasswordResetListener.onOkClicked(email);
					dismiss();
				} else {
					txtRecoveryEmail.setError(getString(R.string.enter_valid_email));
					txtRecoveryEmail.requestFocus();
				}
			}
		});
		
		btnCancelRecoverPassword = view.findViewById(R.id.btnCancelRecoverPassword);
		btnCancelRecoverPassword.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				onPasswordResetListener.onCancelClicked();
				dismiss();
			}
		});
	}
}
