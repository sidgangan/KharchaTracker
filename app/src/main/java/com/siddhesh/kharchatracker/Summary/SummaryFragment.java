package com.siddhesh.kharchatracker.Summary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siddhesh.kharchatracker.R;
import com.siddhesh.kharchatracker.SQLite.SQliteDatabaseHandler;
import com.siddhesh.kharchatracker.SummaryActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    private SQliteDatabaseHandler db;
    public SummaryFragment() {
        // Required empty public constructor




    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        db = new SQliteDatabaseHandler(getActivity().getApplicationContext());

        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

}
