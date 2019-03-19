package com.example.gabriel_cst.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentFour extends Fragment {

    private RecyclerView rvList;
    private CustomAdapter customAdapter;
    private ArrayList<String> mArray;

    public static FragmentFour newInstance(String title) {
        FragmentFour mFragment = new FragmentFour();

        Bundle mBundle = new Bundle();
        mBundle.putString(Constants.ARG_MY_KEY, title);

        mFragment.setArguments(mBundle);

        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvList = view.findViewById(R.id.rv_items);

        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArray.add(getString(R.string.i_am_batman));

                customAdapter.notifyDataSetChanged();
            }
        });

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
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

        customAdapter = new CustomAdapter(getContext(), mArray);

//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);

        rvList.setLayoutManager(mLayoutManager);
        rvList.setAdapter(customAdapter);
    }
}
