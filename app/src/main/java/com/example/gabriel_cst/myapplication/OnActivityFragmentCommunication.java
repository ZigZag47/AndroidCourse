package com.example.gabriel_cst.myapplication;

public interface OnActivityFragmentCommunication {
    void killYourself();
    void onReplaceFragment(String TAG);
    void onAddFragment(String TAG);
    void onPopFragment();
    void onRemoveFragment(String TAG);
}
