package com.example.yourlinkapp.interfaces;

public interface OnAppClickListener {
    void onItemClick(String packageName, String appName, boolean blocked);

    /*void onLockCanceled();

    void onLockAppSet(int hours, int minutes);*/
}
