package com.example.gabriel_cst.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gabriel_cst.myapplication.helpers.Constants;
import com.example.gabriel_cst.myapplication.interfaces.OnActivityFragmentCommunication;
import com.example.gabriel_cst.myapplication.R;

public class FragmentOne extends Fragment {

    OnActivityFragmentCommunication onActivityFragmentCommunication;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnActivityFragmentCommunication) {
            onActivityFragmentCommunication = (OnActivityFragmentCommunication) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        onActivityFragmentCommunication = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button replaceMe = view.findViewById(R.id.btn_replaceFragment);
        replaceMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment();
            }
        });

        Button stopActivity = view.findViewById(R.id.btn_stopActivity);
        stopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stopActivity();
                stopActivitySecondWay();
            }
        });

        TextView mTextView = view.findViewById(R.id.tv_mytext);
        mTextView.setText(getTag());
    }

    void stopActivity() {
        if (getActivity() == null) {
            return;
        }

        getActivity().finish();
    }

    void stopActivitySecondWay() {
        if (onActivityFragmentCommunication == null) {
            return;
        }

        onActivityFragmentCommunication.killYourself();
    }

    void replaceFragment() {
        if (onActivityFragmentCommunication == null) {
            return;
        }

        onActivityFragmentCommunication.onAddFragment(Constants.FRAGMENT_TWO_TAG);
    }
}
