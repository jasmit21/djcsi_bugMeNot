package com.example.yourlinkapp.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import com.example.yourlinkapp.services.MainForegroundService;

public class BootCompleteReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent serviceIntent = new Intent(context, MainForegroundService.class);
		ContextCompat.startForegroundService(context, serviceIntent);
	}
}
