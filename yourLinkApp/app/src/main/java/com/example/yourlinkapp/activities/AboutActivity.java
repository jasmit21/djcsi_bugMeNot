//package com.example.yourlinkapp.activities;
//
//import android.content.Intent;
//import android.widget.FrameLayout;
//import android.widget.ImageButton;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.yourlinkapp.R;
//
//public class AboutActivity extends AppCompatActivity {
//	private ImageButton btnBack;
//	private ImageButton btnSettings;
//	private android.widget.TextView txtTitle;
//	private FrameLayout toolbar;
//
//	@Override
//	protected void onCreate(android.os.Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_about);
//		toolbar = findViewById(R.id.toolbar);
//		btnBack = findViewById(R.id.btnBack);
//		btnBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back));
//		btnBack.setOnClickListener(new android.view.View.OnClickListener() {
//			@Override
//			public void onClick(android.view.View v) {
//				onBackPressed();
//			}
//		});
//		btnSettings = findViewById(R.id.btnSettings);
//		btnSettings.setOnClickListener(new android.view.View.OnClickListener() {
//			@Override
//			public void onClick(android.view.View v) {
//				Intent intent = new Intent(AboutActivity.this, SettingsActivity.class);
//				startActivity(intent);
//			}
//		});
//		txtTitle = findViewById(R.id.txtTitle);
//		txtTitle.setText(getString(R.string.about));
//	}
//}
