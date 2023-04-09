package com.example.yourlinkapp.dialogfragments;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnDeleteAccountListener;
import com.example.yourlinkapp.utils.Constant;
import com.example.yourlinkapp.utils.SharedPrefsUtils;
import com.example.yourlinkapp.utils.Validators;

public class AccountDeleteDialogFragment extends DialogFragment {
	private EditText txtDeleteAccountPassword;
	private Button btnDeleteAccount;
	private Button btnCancelDeleteAccount;
	private OnDeleteAccountListener onDeleteAccountListener;
	
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog_delete_account, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		onDeleteAccountListener = (OnDeleteAccountListener) getActivity();
		
		txtDeleteAccountPassword = view.findViewById(R.id.txtDeleteAccountPassword);
		btnDeleteAccount = view.findViewById(R.id.btnDeleteAccount);
		btnDeleteAccount.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				if (isValid()) {
					onDeleteAccountListener.onDeleteAccount(txtDeleteAccountPassword.getText().toString());
					dismiss();
				}
			}
		});
		
		
		btnCancelDeleteAccount = view.findViewById(R.id.btnCancelDeleteAccount);
		btnCancelDeleteAccount.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				dismiss();
			}
		});
		
		
	}
	
	private boolean isValid() {
		if (!Validators.isValidPassword(txtDeleteAccountPassword.getText().toString())) {
			txtDeleteAccountPassword.setError(getString(R.string.wrong_password));
			txtDeleteAccountPassword.requestFocus();
			return false;
		}
		
		if (!txtDeleteAccountPassword.getText().toString().equals(SharedPrefsUtils.getStringPreference(getContext(), Constant.PASSWORD, ""))) {
			txtDeleteAccountPassword.setError(getString(R.string.wrong_password));
			txtDeleteAccountPassword.requestFocus();
			return false;
		}
		
		return true;
	}
}
