package com.example.yourlinkapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yourlinkapp.R;
import com.example.yourlinkapp.interfaces.OnAppClickListener;
import com.example.yourlinkapp.models.App;
import com.example.yourlinkapp.utils.BackgroundGenerator;

import java.util.ArrayList;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppAdapterViewHolder> {
	private static final String TAG = "AppAdapterTAG";
	private Context context;
	private ArrayList<App> apps;
	private OnAppClickListener onAppClickListener;
	
	public AppAdapter(Context context, ArrayList<App> apps) {
		this.context = context;
		this.apps = apps;
	}
	
	public void setOnAppClickListener(OnAppClickListener onAppClickListener) {
		this.onAppClickListener = onAppClickListener;
	}
	
	@androidx.annotation.NonNull
	@Override
	public AppAdapterViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup viewGroup, int i) {
		android.view.View view = LayoutInflater.from(context).inflate(R.layout.card_app, viewGroup, false);
		return new AppAdapterViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@androidx.annotation.NonNull AppAdapterViewHolder appAdapterViewHolder, int i) {
		App app = apps.get(i);
		if (app != null) {
			appAdapterViewHolder.txtAppName.setText(app.getAppName());
			appAdapterViewHolder.switchAppState.setChecked(app.isBlocked());
			appAdapterViewHolder.txtAppBackground.setText(BackgroundGenerator.getFirstCharacters(app.getAppName()));
			appAdapterViewHolder.txtAppBackground.setBackground(BackgroundGenerator.getBackground(context));
			
		}
	}
	
	@Override
	public int getItemCount() {
		return apps.size();
	}
	
	public class AppAdapterViewHolder extends RecyclerView.ViewHolder {
		private android.widget.TextView txtAppBackground;
		private android.widget.TextView txtAppName;
		private android.widget.Switch switchAppState;
		
		public AppAdapterViewHolder(@androidx.annotation.NonNull android.view.View itemView) {
			super(itemView);
			txtAppBackground = itemView.findViewById(R.id.txtAppBackground);
			txtAppName = itemView.findViewById(R.id.txtAppName);
			switchAppState = itemView.findViewById(R.id.switchAppState);
			switchAppState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (buttonView.isPressed()) {
						onAppClickListener.onItemClick(apps.get(getAdapterPosition()).getPackageName(), apps.get(getAdapterPosition()).getAppName(), isChecked); //changed from txtAppName.getText()
						android.util.Log.i(TAG, "onCheckedChanged: packageName: " + apps.get(getAdapterPosition()).getPackageName());
						android.util.Log.i(TAG, "onCheckedChanged: appName: " + apps.get(getAdapterPosition()).getAppName());
					}
					
				}
			});
		}
	}
	
}
