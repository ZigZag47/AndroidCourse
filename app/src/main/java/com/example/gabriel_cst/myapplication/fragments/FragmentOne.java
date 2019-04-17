package com.example.gabriel_cst.myapplication.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gabriel_cst.myapplication.helpers.Constants;
import com.example.gabriel_cst.myapplication.helpers.roomDatabase.UserRepository;
import com.example.gabriel_cst.myapplication.helpers.volley.MySingleton;
import com.example.gabriel_cst.myapplication.interfaces.OnActivityFragmentCommunication;
import com.example.gabriel_cst.myapplication.R;
import com.example.gabriel_cst.myapplication.models.ToDoItem;
import com.example.gabriel_cst.myapplication.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

        Button btn_getToDoItems = view.findViewById(R.id.btn_getToDoItems);
        btn_getToDoItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getToDoItems();
            }
        });

        TextView mTextView = view.findViewById(R.id.tv_mytext);
//        mTextView.setText(getTag());

//        User mUser = new UserRepository(getContext())
//                .getUserByName("Gabriel", "Alexandru");

    }


    ProgressDialog mProgressDialog;
    private void getToDoItems() {
        String url = "https://jsonplaceholderx.typicode.com/todos?userId=1";

        ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setCancelable(false);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<ToDoItem> toDoItemsList = new ArrayList<>();
                //load this list into RecyclerView

                for(int index = 0; index < response.length(); index++) {
                    try {

                        ToDoItem item = new ToDoItem().fromJSON(response.getJSONObject(index));
                        toDoItemsList.add(item);

                    } catch (JSONException ex) {

                    }
                }

                mProgressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Volley error: " +  error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });

        mProgressDialog.show();
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
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
