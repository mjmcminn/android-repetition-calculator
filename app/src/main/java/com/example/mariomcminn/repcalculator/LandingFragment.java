package com.example.mariomcminn.repcalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mariomcminn on 4/25/16.
 */
public class LandingFragment extends Fragment {

    Button mPushUpButton;
    Button mBenchPressButton;
    Button mPullUpButton;
    RepCount r = new RepCount();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.landing_fragment, parent, false);

        mPushUpButton = (Button) v.findViewById(R.id.pushup_button);
        mPushUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CounterFragment cf = new CounterFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, cf)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mPullUpButton = (Button) v.findViewById(R.id.pullup_button);
        mPullUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CounterFragment cf = new CounterFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, cf)
                        .addToBackStack(null)
                        .commit();
            }
        });

        mBenchPressButton = (Button) v.findViewById(R.id.benchpress_button);
        mBenchPressButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CounterFragment cf = new CounterFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, cf)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }
}
