package com.example.gabriel_cst.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity implements OnActivityFragmentCommunication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        onReplaceFragment(Constants.FRAGMENT_ONE_TAG);
    }

    private void replaceFragment(Fragment fragment, String TAG) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
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
            case Constants.FRAGMENT_ONE_TAG:
                replaceFragment(new FragmentOne(),
                        TAG);
            break;

            case Constants.FRAGMENT_TWO_TAG:
                replaceFragment(FragmentTwo.newInstance(getString(R.string.i_am_batman)),
                        TAG);
            break;
        }
    }

    @Override
    public void onPopFragment() {
        getSupportFragmentManager().popBackStack();
    }

}
