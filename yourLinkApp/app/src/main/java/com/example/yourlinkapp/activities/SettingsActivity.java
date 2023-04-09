package com.example.yourlinkapp.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.dialogfragments.AccountDeleteDialogFragment;
import com.example.yourlinkapp.dialogfragments.ConfirmationDialogFragment;
//import com.example.yourlinkapp.dialogfragments.LanguageSelectionDialogFragment;
import com.example.yourlinkapp.dialogfragments.PasswordChangingDialogFragment;
import com.example.yourlinkapp.interfaces.OnConfirmationListener;
import com.example.yourlinkapp.interfaces.OnDeleteAccountListener;
import com.example.yourlinkapp.interfaces.OnLanguageSelectionListener;
import com.example.yourlinkapp.interfaces.OnPasswordChangeListener;
import com.example.yourlinkapp.utils.AccountUtils;
import com.example.yourlinkapp.utils.Constant;
import com.example.yourlinkapp.utils.LocaleUtils;
import com.example.yourlinkapp.utils.SharedPrefsUtils;

public class SettingsActivity extends AppCompatActivity implements  OnConfirmationListener, OnPasswordChangeListener {

	private Button btnLogout;
	private Button btnChangePassword;

	//private Button btnRateUs;   //Won't be uploaded to the play store duo to violation of privacy
	private ImageButton btnBack;
	private ImageButton btnSettings;
	private android.widget.TextView txtTitle;
	private FrameLayout toolbar;

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		toolbar = findViewById(R.id.toolbar);
		btnBack = findViewById(R.id.btnBack);
		btnBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back));
		btnBack.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				onBackPressed();
			}
		});
		btnSettings = findViewById(R.id.btnSettings);
		btnSettings.setVisibility(android.view.View.GONE);
		txtTitle = findViewById(R.id.txtTitle);
		txtTitle.setText(getString(R.string.settings));


		btnLogout = findViewById(R.id.btnLogout);
		btnLogout.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				logout();
			}
		});


		btnChangePassword = findViewById(R.id.btnChangePassword);
		btnChangePassword.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				changePassword();
			}
		});





		/*btnRateUs = findViewById(R.id.btnRateUs);
		btnRateUs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				rateTheApp();
			}
		});*/




	}



	/*private void rateTheApp() {
		Toast.makeText(this, "rateTheApp", Toast.LENGTH_SHORT).show();

	}*/





	private void changePassword() {
		PasswordChangingDialogFragment passwordChangingDialogFragment = new PasswordChangingDialogFragment();
		passwordChangingDialogFragment.setCancelable(false);
		passwordChangingDialogFragment.show(getSupportFragmentManager(), Constant.PASSWORD_CHANGING_DIALOG_FRAGMENT_TAG);
	}

	private void logout() {
		ConfirmationDialogFragment confirmationDialogFragment = new ConfirmationDialogFragment();
		android.os.Bundle bundle = new android.os.Bundle();
		bundle.putString(Constant.CONFIRMATION_MESSAGE, getString(R.string.do_you_want_to_logout));
		confirmationDialogFragment.setArguments(bundle);
		confirmationDialogFragment.setCancelable(false);
		confirmationDialogFragment.show(getSupportFragmentManager(), Constant.CONFIRMATION_FRAGMENT_TAG);
	}





//	private void restartApp() {
//		Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
//		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);
//
//	}

	@Override
	public void onConfirm() {
		AccountUtils.logout(this);
	}

	@Override
	public void onConfirmationCancel() {
		//DO NOTHING
	}

	@Override
	public void onPasswordChange(String newPassword) {
		AccountUtils.changePassword(this, newPassword);

	}

}
