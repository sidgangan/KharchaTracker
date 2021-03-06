package com.siddhesh.kharchatracker.Add;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddhesh.kharchatracker.SQLite.Expense;
import com.siddhesh.kharchatracker.SQLite.SQliteDatabaseHandler;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import com.siddhesh.kharchatracker.R;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    private DatePicker datePicker;
    private Calendar calendar;

    private TextView dateView;
    private TextView amount;
    private Spinner category;
    private TextView description;
    private Button add;

    private int year, day;
    private String month;

    private SQliteDatabaseHandler db;

    public AddFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        db = new SQliteDatabaseHandler(getActivity().getApplicationContext());

        dateView = (TextView)view.findViewById(R.id.date);
        amount = (TextView)view.findViewById(R.id.amount);
        category = (Spinner)view.findViewById(R.id.category);
        description = (TextView)view.findViewById(R.id.description);
        add = (Button)view.findViewById(R.id.add);

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(getResources().getColor(R.color.colorPrimary));
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] dates = dateView.getText().toString().split(" ");
                String desc = description.getText().toString();
                int amt = Integer.parseInt(amount.getText().toString());
                String cat = String.valueOf(category.getSelectedItem());

                db.addExpense(new Expense(cat, desc, amt, dates[0], dates[1], dates[2]));


                Toast toast = Toast.makeText(getContext(), "Expense added successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return view;
    }



    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if(view!=null){

            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            dateView.setText(new StringBuilder().append(day).append(" ").append(month).append(" ").append(year));

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog)getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");
        if(dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int month, int day) {
        String monthName = new DateFormatSymbols().getMonths()[month];
        dateView.setText(new StringBuilder().append(day).append(" ").append(monthName).append(" ").append(year));
    }




}
