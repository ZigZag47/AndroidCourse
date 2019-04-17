package com.example.gabriel_cst.myapplication.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gabriel_cst.myapplication.helpers.Constants;
import com.example.gabriel_cst.myapplication.adapters.CustomAdapter;
import com.example.gabriel_cst.myapplication.R;

import java.util.ArrayList;

public class FragmentFour extends Fragment {

    private RecyclerView rvList;
    private CustomAdapter customAdapter;
    private ArrayList<String> mArray;
    private EditText edt_insertText;

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
                rvList.scrollToPosition(mArray.size() - 1);

                edt_insertText.setText(getString(R.string.i_am_batman));
            }
        });

        edt_insertText = view.findViewById(R.id.edt_insertText);
        edt_insertText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

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
