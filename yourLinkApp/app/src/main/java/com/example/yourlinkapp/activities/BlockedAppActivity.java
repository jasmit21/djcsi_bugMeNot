package com.example.yourlinkapp.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yourlinkapp.R;

import static com.example.yourlinkapp.services.MainForegroundService.BLOCKED_APP_NAME_EXTRA;

public class BlockedAppActivity extends AppCompatActivity {
	private android.widget.TextView txtBlockedAppName;
	
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blocked_app);
		
		txtBlockedAppName = findViewById(R.id.txtBlockedAppName);
		Intent intent = getIntent();
		String blockedAppName = intent.getStringExtra(BLOCKED_APP_NAME_EXTRA);
		txtBlockedAppName.setText(blockedAppName);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
