package com.example.yourlinkapp.dialogfragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.yourlinkapp.R;

public class LoadingDialogFragment extends DialogFragment {
	@androidx.annotation.Nullable
	@Override
	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog_loading, container, false);
	}
	
	
	@Override
	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
		// NOTHING
	}
}
