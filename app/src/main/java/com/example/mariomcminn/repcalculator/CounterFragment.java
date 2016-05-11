package com.example.mariomcminn.repcalculator;

import android.app.AlertDialog;
import android.app.Fragment;
import android.database.Cursor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.text.SimpleDateFormat;


/**
 * Created by mariomcminn on 4/25/16.
 */
public class CounterFragment extends Fragment {

    Button mRepCompleteButton;
    Button mHistoryButton;
    Button mTotalButton;
    Button mDeleteButton;
    RepDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat();
    String date = sdf.format(new java.util.Date());
    private int mRepCount = 0;
    private OrientationEventListener mOrientationListener;
    String exerciseName;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (savedInstanceState != null){
            mRepCount = savedInstanceState.getInt("count");
        }

        mOrientationListener = new OrientationEventListener(getActivity(),
                SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                // Nothing at this time
            }
        };

        if (mOrientationListener.canDetectOrientation()){
            mOrientationListener.enable();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.counter_fragment, parent, false);
        mRepCount++;

        mRepCompleteButton = (Button) v.findViewById(R.id.rep_complete_button);
        mRepCompleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Double mFullRepCount = Double.valueOf(mRepCount / 2);
                db = new RepDatabase(getActivity());
                boolean isInserted = db.insertCartItem(mFullRepCount, date, exerciseName);

                if (isInserted) {
                    Toast.makeText(getActivity(), "ITEMS INSERTED", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "ITEMS NOT INSERTED", Toast.LENGTH_LONG).show();
                }
            }
        });

        mHistoryButton = (Button) v.findViewById(R.id.history_button);
        mHistoryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Cursor result = db.getAllData();
                if (result.getCount() == 0){
                    showMessage("Error", "No Data Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("Id " + result.getString(0) + "\n");
                    buffer.append("Reps " + result.getString(1) + "\n");
                    buffer.append("Date " + result.getString(2) + "\n\n");
                    //buffer.append("Exercise " + result.getString(3) + "\n\n");
                }
                showMessage("Data", buffer.toString());
            }
        });

        mTotalButton = (Button) v.findViewById(R.id.total_button);
        mTotalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = db.getTotalRepCount();
                if (result.getCount() == 0){
                    showMessage("Error", "No Data Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("Total " + result.getString(0) + "\n");

                }
                showMessage("Data", buffer.toString());
            }
        });

        mDeleteButton = (Button) v.findViewById(R.id.delete_button);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData();
                Toast.makeText(getActivity(), "DATA DELETED", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("count", mRepCount);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mOrientationListener.disable();
    }

    public void showMessage (String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}