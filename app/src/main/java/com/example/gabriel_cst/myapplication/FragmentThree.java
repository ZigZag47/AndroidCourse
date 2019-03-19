package com.example.gabriel_cst.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentThree extends Fragment {

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private ArrayList<String> mArray;

    public static FragmentThree newInstance(String value) {
        FragmentThree mFragment = new FragmentThree();

        Bundle mBundle = new Bundle();
        mBundle.putString(Constants.ARG_MY_KEY, value);

        mFragment.setArguments(mBundle);

        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.rv_items);
        setupRecyclerView();

        if(getArguments() != null) {
            String argumentsKey = getArguments().getString(Constants.ARG_MY_KEY);
            TextView tv_title = view.findViewById(R.id.tv_title);
            tv_title.setText(argumentsKey);
            tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mArray.add("New Value");
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public void setupRecyclerView() {

        mArray = new ArrayList<>();
        mArray.add("one");
        mArray.add("two");
        mArray.add("three");
        mArray.add("four");
        mArray.add("five");
        mArray.add("six");
        mArray.add("seven");
        mArray.add("eight");
        mArray.add("nine");
        mArray.add("ten");

        mAdapter = new CustomAdapter(getContext(), mArray);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mAdapter);
    }

}
