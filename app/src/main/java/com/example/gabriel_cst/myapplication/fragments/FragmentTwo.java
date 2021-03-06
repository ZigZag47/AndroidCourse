package com.example.gabriel_cst.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gabriel_cst.myapplication.helpers.Constants;
import com.example.gabriel_cst.myapplication.interfaces.OnActivityFragmentCommunication;
import com.example.gabriel_cst.myapplication.R;

public class FragmentTwo extends Fragment implements View.OnClickListener {

    private OnActivityFragmentCommunication mOnActivityFragmentCommunication;

    private Button goBackButton;

    public static FragmentTwo newInstance(String dataToSendAsArgument) {
        FragmentTwo fragmentTwo = new FragmentTwo();

        Bundle mBundle = new Bundle();
        mBundle.putString(Constants.ARG_MY_KEY, dataToSendAsArgument);

        fragmentTwo.setArguments(mBundle);

        return fragmentTwo;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnActivityFragmentCommunication) {
            this.mOnActivityFragmentCommunication = (OnActivityFragmentCommunication) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goBackButton = view.findViewById(R.id.btn_go_back);
        goBackButton.setOnClickListener(this);

        Button removeFrOne = view.findViewById(R.id.btn_remove_fragment_one);
        removeFrOne.setOnClickListener(this);

        Bundle bundle = getArguments();
        if(bundle != null) {
            String dataReceived = bundle.getString(Constants.ARG_MY_KEY);

            TextView titleTextView = view.findViewById(R.id.tv_mytext);
            titleTextView.setText(dataReceived);
        }
    }

    private void gotoFragmentOne(){
        if(mOnActivityFragmentCommunication == null){
            return;
        }

        mOnActivityFragmentCommunication.onPopFragment();
    }

    private void gotoFragmentThree(){
        if(mOnActivityFragmentCommunication == null){
            return;
        }

        mOnActivityFragmentCommunication.onReplaceFragment(Constants.FRAGMENT_THREE_TAG);
    }

    private void gotoFragmentFour(){
        if(mOnActivityFragmentCommunication == null){
            return;
        }

        mOnActivityFragmentCommunication.onReplaceFragment(Constants.FRAGMENT_FOUR_TAG);
    }

    private void removeFragmentOne(){
        if(mOnActivityFragmentCommunication == null){
            return;
        }

        mOnActivityFragmentCommunication.onRemoveFragment(Constants.FRAGMENT_ONE_TAG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_back:
                gotoFragmentFour();
                break;

            case R.id.btn_remove_fragment_one:
                removeFragmentOne();
                break;
        }
    }
}
