package com.example.yourlinkapp.dialogfragments;

import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnGoogleChildSignUp;
import com.example.yourlinkapp.utils.Validators;

public class GoogleChildSignUpDialogFragment extends DialogFragment {
	private Button btnChildSignUp;
	private Button btnCancelChildSignUp;
	private EditText txtParentEmail;
	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference databaseReference;
	private OnGoogleChildSignUp onGoogleChildSignUp;
	private boolean validParent = false;
	
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog_google_child_sign_up, container, false);
	}
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		
		firebaseDatabase = FirebaseDatabase.getInstance();
		databaseReference = firebaseDatabase.getReference("users");
		onGoogleChildSignUp = (OnGoogleChildSignUp) getActivity();
		
		txtParentEmail = view.findViewById(R.id.txtParentEmail);
		txtParentEmail.addTextChangedListener(new TextWatcher() {//TODO:: need a better way
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				Query query = databaseReference.child("parents").orderByChild("email").equalTo(txtParentEmail.getText().toString());
				query.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
						validParent = dataSnapshot.exists();
					}
					
					@Override
					public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
					
					}
				});
				
			}
		});
		
		btnChildSignUp = view.findViewById(R.id.btnChildSignUp);
		btnChildSignUp.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				if (isValid()) {
					onGoogleChildSignUp.onModeSelected(txtParentEmail.getText().toString().toLowerCase());
					dismiss();
				}
			}
			
		});
		
		btnCancelChildSignUp = view.findViewById(R.id.btnCancelChildSignUp);
		btnCancelChildSignUp.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				dismiss();
			}
		});
	}
	
	private boolean isValid() {
		if (!Validators.isValidEmail(txtParentEmail.getText().toString()) || !validParent) {
			txtParentEmail.setError(getString(R.string.this_email_isnt_registered_as_parent));
			txtParentEmail.requestFocus();
			return false;
		}
		
		return true;
	}
	
}
