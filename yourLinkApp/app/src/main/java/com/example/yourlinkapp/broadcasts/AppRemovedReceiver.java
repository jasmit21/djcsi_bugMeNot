package com.example.yourlinkapp.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AppRemovedReceiver extends BroadcastReceiver {
	public static final String TAG = "AppRemovedReceiver";
	private FirebaseUser user;
	private FirebaseDatabase firebaseDatabase;
	private DatabaseReference databaseReference;
	private String uid;
	private String childEmail;
	
	public AppRemovedReceiver(FirebaseUser user) {
		this.user = user;
		firebaseDatabase = FirebaseDatabase.getInstance("https://yourlink-19e96-default-rtdb.firebaseio.com/");
		databaseReference = firebaseDatabase.getReference("users");
		uid = user.getUid();
		childEmail = user.getEmail();
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		
		String componentPackage = intent.getData().getSchemeSpecificPart();
		
		String packageName = null;
		String appName = null;
		
		PackageManager packageManager = context.getPackageManager();
		try {
			ApplicationInfo applicationInfo = packageManager.getApplicationInfo(componentPackage, 0);
			packageName = applicationInfo.packageName;
			appName = (String) applicationInfo.loadLabel(packageManager);
			
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		
		android.util.Log.i(TAG, "onReceive: action: " + action);
		android.util.Log.i(TAG, "onReceive: packageName: " + packageName);
		android.util.Log.i(TAG, "onReceive: appName: " + appName);
		android.util.Log.i(TAG, "onReceive: componentPackage: " + componentPackage);         //package name
		android.util.Log.i(TAG, "onReceive: " + intent.getData().getScheme());
		android.util.Log.i(TAG, "onReceive: " + intent.getData());
		
		removeApp(componentPackage);
		
	}
	
	private void removeApp(final String packageName) {
		
		Query query = databaseReference.child("childs").child(uid).child("apps").orderByChild("packageName").equalTo(packageName);
		query.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()) {
					DataSnapshot snapshot = dataSnapshot.getChildren().iterator().next();
					databaseReference.child("childs").child(uid).child("apps").child(snapshot.getKey()).removeValue();
				}
			}
			
			@Override
			public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {
			
			}
		});
		
	}
	
}
