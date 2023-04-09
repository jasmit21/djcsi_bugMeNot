//package com.example.yourlinkapp.dialogfragments;
//
//import android.graphics.drawable.ColorDrawable;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.Button;
//
//import androidx.fragment.app.DialogFragment;
//
//import com.example.yourlinkapp.R;
//import com.example.yourlinkapp.interfaces.OnLanguageSelectionListener;
//import com.example.yourlinkapp.utils.Constant;
//import com.example.yourlinkapp.utils.SharedPrefsUtils;
//
//public class LanguageSelectionDialogFragment extends DialogFragment {
//	private android.widget.Spinner spinnerLanguageEntries;
//	private Button btnOkLanguageSelection;
//	private Button btnCancelLanguageSelection;
//	private OnLanguageSelectionListener onLanguageSelectionListener;
//
//	@androidx.annotation.Nullable
//	@Override
//	public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
//		return inflater.inflate(R.layout.fragment_dialog_language_selection, container, false);
//	}
//
//	@Override
//	public void onViewCreated(@androidx.annotation.NonNull android.view.View view, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
//		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//		onLanguageSelectionListener = (OnLanguageSelectionListener) getActivity();
//
//		String appLanguage = SharedPrefsUtils.getStringPreference(getContext(), Constant.APP_LANGUAGE, "en");
//		spinnerLanguageEntries = view.findViewById(R.id.spinnerLanguageEntries);
//		if (appLanguage.equals("en")) spinnerLanguageEntries.setSelection(0);
//		else if (appLanguage.equals("ar")) spinnerLanguageEntries.setSelection(1);
//
//
//		btnOkLanguageSelection = view.findViewById(R.id.btnOkLanguageSelection);
//		btnOkLanguageSelection.setOnClickListener(new android.view.View.OnClickListener() {
//			@Override
//			public void onClick(android.view.View view) {
//				onLanguageSelectionListener.onLanguageSelection(spinnerLanguageEntries.getSelectedItem().toString());
//				dismiss();
//			}
//		});
//
//		btnCancelLanguageSelection = view.findViewById(R.id.btnCancelLanguageSelection);
//		btnCancelLanguageSelection.setOnClickListener(new android.view.View.OnClickListener() {
//			@Override
//			public void onClick(android.view.View view) {
//				dismiss();
//			}
//		});
//
//
//	}
//}
