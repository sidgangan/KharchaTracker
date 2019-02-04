package com.siddhesh.kharchatracker.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class SQliteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserExpensesDB";

    // Table names in database
    private static final String TABLE_NAME = "Expenses";


    // column names
    private static final String CATEGORY = "category";
    private static final String DESCRIPTION = "description";
    private static final String AMOUNT = "amount";
    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    private Context context;

    private static final String[] COLUMNS = {CATEGORY,DESCRIPTION,AMOUNT,DAY,MONTH,YEAR};

    public SQliteDatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Expenses ( "
                + "category TEXT , " + "description TEXT , " + "amount INTEGER, " + "day TEXT, " + "month TEXT, "
                + "year TEXT )";



        try {
            
            db.execSQL(CREATION_TABLE);


            Toast toast = Toast.makeText(context, "Tables created", Toast.LENGTH_SHORT);
            toast.show();

        }catch(Exception e){
            Toast toast = Toast.makeText(context, "Error in creating table : "+e.toString(), Toast.LENGTH_LONG);
            toast.show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process

        this.onCreate(db);
    }
//-----------------------------------------------------------------------------------------------------

    public void addExpense(Expense expense){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CATEGORY, expense.getCategory());
        values.put(DESCRIPTION, expense.getDescription());
        values.put(AMOUNT, expense.getAmount());
        values.put(DAY, expense.getDay());
        values.put(MONTH, expense.getMonth());
        values.put(YEAR, expense.getYear());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }


    public ArrayList<Expense> getExpenses(String month, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Expense> expenses = new ArrayList<Expense>();

        String query;

        if(month.equalsIgnoreCase("all") && year.equalsIgnoreCase("all")){

            query = "SELECT * FROM " + TABLE_NAME;

        }else if(month.equalsIgnoreCase("all") && !year.equalsIgnoreCase("all")){

            query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ YEAR + " = " + year;

        }else if(!month.equalsIgnoreCase("all") && year.equalsIgnoreCase("all")){

            query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ MONTH + " = " + month;

        }else{

            query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ YEAR + " = " + year + " AND " + MONTH + " = " + month;

        }

        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            String category = cursor.getString(cursor.getColumnIndexOrThrow(CATEGORY));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
            int amount = cursor.getInt(cursor.getColumnIndexOrThrow(AMOUNT));
            String day = cursor.getString(cursor.getColumnIndexOrThrow(DAY));
            String mon = cursor.getString(cursor.getColumnIndexOrThrow(MONTH));
            String yer = cursor.getString(cursor.getColumnIndexOrThrow(YEAR));

            expenses.add(new Expense(category, description, amount, day, mon, yer));
        }

        return expenses;
    }



}
