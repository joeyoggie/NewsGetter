package com.joey.seamlabstask.view.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.joey.seamlabstask.R;

public class MainActivity extends AppCompatActivity{

    private static MainActivity mInstance;

    private static Toolbar toolbar;
    private static TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInstance = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getString(R.string.app_name));
        mTitle.setTextColor(getResources().getColor(R.color.whiteColor));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction = Utils.setAnimations(fragmentTransaction, Utils.ANIMATION_TYPE_TRANSLATION);
        fragmentTransaction.replace(R.id.fragment_view, NewsListFragment.newInstance(), "newsListFragment");
        fragmentTransaction.commit();

    }

    public static void setActionBarTitle(String title, int colorID){
        mTitle.setText(title);
        mTitle.setTextColor(colorID);
    }

    public static Toolbar getToolbar(){
        return toolbar;
    }

    public static MainActivity getInstance(){
        return mInstance;
    }
}
