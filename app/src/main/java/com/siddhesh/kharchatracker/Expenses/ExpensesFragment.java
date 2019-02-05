package com.siddhesh.kharchatracker.Expenses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siddhesh.kharchatracker.R;
import com.siddhesh.kharchatracker.SQLite.Aggregate;
import com.siddhesh.kharchatracker.SQLite.SQliteDatabaseHandler;
import com.siddhesh.kharchatracker.SummaryActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment {

    private SQliteDatabaseHandler db;
    private TextView text;

    public ExpensesFragment() {
        // Required empty public constructor

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        db = new SQliteDatabaseHandler(getActivity().getApplicationContext());

        text = (TextView)view.findViewById(R.id.text);

        Aggregate agg = db.getTotal("February","2019");
        Aggregate agg2 = db.getAvg("February","2019");
        int num = db.getExpenses("February","2019").size();
        text.setText(Integer.toString(num) + " AVG FOOD : " +Integer.toString(agg2.getFood()) + " TOTAL :"+Integer.toString(agg.getFood()));

        return view;
    }

}
