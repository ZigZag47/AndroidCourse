package com.example.gabriel_cst.myapplication;

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_back:
                gotoFragmentOne();
                break;
        }
    }
}
