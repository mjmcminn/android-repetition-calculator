package com.example.mariomcminn.repcalculator;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

public class RepCalculatorActivity extends SingleFragmentActivity {
    RepDatabase db;
    RepCount r = new RepCount();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        db = new RepDatabase(this);
    }

    @Override
    protected Fragment createFragment() {
        return new LandingFragment();
    }

    public String passExerciseName() {
        return r.getExerciseName();
    }

    public void onExerciseNamePass(String exercise) {
        r.setExerciseName(exercise);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}