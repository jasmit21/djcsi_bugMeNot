package com.example.yourlinkapp.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.utils.Constant;

import de.hdodenhof.circleimageview.CircleImageView;

public class ModeSelectionActivity extends AppCompatActivity {
	private CircleImageView imgParentSignUp;
	private android.widget.TextView txtParentSignUp;
	
	private CircleImageView imgChildSignUp;
	private android.widget.TextView txtChildSignUp;
	
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mode_selection);
		
		txtParentSignUp = findViewById(R.id.txtParentSignUp);
		imgParentSignUp = findViewById(R.id.imgParentSignUp);
		imgParentSignUp.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				startParentSignUp();
			}
		});
		
		
		txtChildSignUp = findViewById(R.id.txtChildSignUp);
		imgChildSignUp = findViewById(R.id.imgChildSignUp);
		imgChildSignUp.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				startChildSignUp();
			}
		});
		
	}
	
	private void startParentSignUp() {
		Intent intent = new Intent(this, SignUpActivity.class);
		intent.putExtra(Constant.PARENT_SIGN_UP, true);
		startActivity(intent);
	}
	
	private void startChildSignUp() {
		Intent intent = new Intent(this, SignUpActivity.class);
		intent.putExtra(Constant.PARENT_SIGN_UP, false);
		startActivity(intent);
		
	}
}
