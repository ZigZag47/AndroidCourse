package com.example.gabriel_cst.myapplication.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gabriel_cst.myapplication.helpers.Constants;
import com.example.gabriel_cst.myapplication.interfaces.OnActivityFragmentCommunication;
import com.example.gabriel_cst.myapplication.R;
import com.example.gabriel_cst.myapplication.fragments.FragmentFour;
import com.example.gabriel_cst.myapplication.fragments.FragmentOne;
import com.example.gabriel_cst.myapplication.fragments.FragmentThree;
import com.example.gabriel_cst.myapplication.fragments.FragmentTwo;

public class Main2Activity extends AppCompatActivity implements OnActivityFragmentCommunication {

    FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        onAddFragment(Constants.FRAGMENT_ONE_TAG);
    }

    private void addFragment(Fragment fragment, String TAG, boolean addToBackstack) {

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.add(R.id.fragments_container,
                fragment,
                TAG);

        if(addToBackstack) {
            mFragmentTransaction.addToBackStack(TAG);
        }

        mFragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment, String TAG) {

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.replace(R.id.fragments_container,
                fragment,
                TAG);
        mFragmentTransaction.addToBackStack(TAG);
        mFragmentTransaction.commit();
    }

    //OnActivityFragmentCommunication methods
    @Override
    public void killYourself() {
        Intent intent = new Intent();
        intent.putExtra(Constants.ARG_MY_KEY,
                getString(R.string.i_am_batman));
        setResult(Constants.RESULT_CODE, intent);

        finish();
    }

    @Override
    public void onReplaceFragment(String TAG) {
        switch (TAG) {
            case Constants.FRAGMENT_THREE_TAG:
                replaceFragment(FragmentThree.newInstance(getString(R.string.i_am_batman)),
                        TAG);
                break;

            case Constants.FRAGMENT_FOUR_TAG:
                replaceFragment(FragmentFour.newInstance(getString(R.string.i_am_batman)),
                        TAG);

                break;
        }
    }

    @Override
    public void onAddFragment(String TAG) {
        switch (TAG) {
            case Constants.FRAGMENT_ONE_TAG:
                addFragment(new FragmentOne(),
                        TAG,
                        false);
                break;

            case Constants.FRAGMENT_TWO_TAG:
                addFragment(FragmentTwo.newInstance(getString(R.string.i_am_batman)),
                        TAG,
                        true);
                break;
        }
    }

    @Override
    public void onPopFragment() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onRemoveFragment(String TAG) {
        Fragment fragment = mFragmentManager.findFragmentByTag(TAG);
        if (fragment != null) {
            mFragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }

}
