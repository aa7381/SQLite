package com.example.sqlite;

import static com.example.sqlite.Worker.KEY_ID;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class displaying_tables extends AppCompatActivity implements AdapterView.OnItemSelectedListener ,View.OnCreateContextMenuListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener  {

    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    String[] names = {"worker", "parkfood", "meal", "order"};
    Button back_btn;
    ListView lvrecords;
    Spinner menu, spinner_filter;


    ArrayList<String> tbl;

    ArrayList<Integer> keysList;

    int count = 0;
    int count2 = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaying_tables);

        lvrecords = findViewById(R.id.lvrecords);
        spinner_filter = findViewById(R.id.spinner_filter);
        menu = findViewById(R.id.menu);
        back_btn = findViewById(R.id.rtn_back);

        menu.setOnItemSelectedListener(this);
        spinner_filter.setOnItemSelectedListener(this);
        lvrecords.setOnItemClickListener(this);
        lvrecords.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvrecords.setOnItemLongClickListener(this);


        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, names);
        menu.setAdapter(adp);




    }

    /**
     * Handles item selection events for the spinners.
     *
     * @param parent   The AdapterView where the selection happened.
     * @param view     The view within the AdapterView that was clicked.
     * @param pos      The position of the view in the adapter.
     * @param rowid    The row id of the item that is selected.
     */

    //_____________________________________________________________________________________________________________________
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long rowid) {
        if (parent == menu) {
            if (pos == 0) {
                spinner_filter.setVisibility(View.VISIBLE);

                count = pos;

                String[] filter = {"no_sort", "sort_lastname"};
                ArrayAdapter<String> spinner2adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filter);
                spinner_filter.setAdapter(spinner2adp);

            }
            if (pos == 1) {
                spinner_filter.setVisibility(View.VISIBLE);

                count = pos;

                String[] filter = {"no_sort", "sort_companyname"};
                ArrayAdapter<String> spinner2adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filter);
                spinner_filter.setAdapter(spinner2adp);
            }

            if (pos == 2) {
                tbl = meal_show(lvrecords, tbl, crsr, hlp, db);
                ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                lvrecords.setAdapter(spinneradp);
                lvrecords.setOnCreateContextMenuListener(this);
                spinner_filter.setVisibility(View.GONE);
            }

            if (pos == 3) {
                spinner_filter.setVisibility(View.VISIBLE);
                count = pos;

                String[] filter = {"no_sort", "sort_employee"};
                ArrayAdapter<String> spinner2adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filter);
                spinner_filter.setAdapter(spinner2adp);


            }
        } else if (parent == spinner_filter) {
            {
                if(count == 0 && pos == 0)
                {
                    tbl = worker_show(lvrecords, tbl, crsr, hlp, db);
                    ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                    lvrecords.setAdapter(spinneradp);
                    lvrecords.setOnCreateContextMenuListener(this);


                } else if (count == 0 && pos == 1)
                {
                    {
                        tbl = worker_show_sort(lvrecords, tbl, crsr, hlp, db);
                        ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                        lvrecords.setAdapter(spinneradp);
                        lvrecords.setOnCreateContextMenuListener(this);

                    }
                }
                if(count == 1 && pos == 0)
                {
                    tbl = parkfood_show(lvrecords, tbl, crsr, hlp, db);
                    ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                    lvrecords.setAdapter(spinneradp);
                    lvrecords.setOnCreateContextMenuListener(this);
                } else if (count == 1 && pos == 1)
                {
                    tbl = parkfood_show_sort(lvrecords, tbl, crsr, hlp, db);
                    ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                    lvrecords.setAdapter(spinneradp);
                    lvrecords.setOnCreateContextMenuListener(this);
                }
                if(count == 3 && pos == 0)
                {
                    tbl = order_show(lvrecords, tbl, crsr, hlp, db);
                    ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                    lvrecords.setAdapter(spinneradp);
                    lvrecords.setOnCreateContextMenuListener(this);

                } else if (count == 3 && pos == 1) {
                    tbl = order_show_sort(lvrecords, tbl, crsr, hlp, db);
                    ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
                    lvrecords.setAdapter(spinneradp);
                    lvrecords.setOnCreateContextMenuListener(this);

                }
            }

        }
    }
    /**
     * Creates a context menu for the ListView.
     *
     * @param menu     The context menu to be built.
     * @param v        The view for which the context menu is being built.
     * @param menuInfo Extra information about the item for which the context menu should be shown.
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.add("delete");

    }
    /**
     * Handles item selections in the context menu.
     *
     * @param item The selected menu item.
     * @return True if the item was handled, false otherwise.
     */

    public boolean onContextItemSelected(MenuItem item) {
        String oper = item.getTitle().toString();

        if(oper.equals("delete") && count == 0) {
            hlp = new HelperDB(this);
            db = hlp.getWritableDatabase();

            int keyToDelete = keysList.get(count2);
            db.delete(Worker.TABLE_WORKER, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
            db.close();

            tbl.remove(count2);

        }
        if(oper.equals("delete") && count == 1)
        {
            hlp = new HelperDB(this);
            db = hlp.getWritableDatabase();
            int keyToDelete = keysList.get(count2);
            db.delete(ParkFood.TABLE_PARKFOOD, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});

            db.close();


        }
        if(oper.equals("delete") && count == 3)
        {
            hlp = new HelperDB(this);
            db = hlp.getWritableDatabase();
            int keyToDelete = keysList.get(count2);
            db.delete(Order.TABLE_ORDER, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
            db.close();


        }
        if(oper.equals("delete") && count == 2)
        {
            hlp = new HelperDB(this);
            db = hlp.getWritableDatabase();

            int keyToDelete = keysList.get(count2);
            db.delete(Meal.TABLE_MEAL, KEY_ID + "=?", new String[]{Integer.toString(keyToDelete)});
            db.close();


        }

        if (count2 >= 0 && count2 < tbl.size() && count2 < keysList.size()) {
            tbl.remove(count2);
            keysList.remove(count2);
        }

        ArrayAdapter<String> spinneradp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
        lvrecords.setAdapter(spinneradp);
        return super.onContextItemSelected(item);
    }
    /**
     * Handles the event when nothing is selected in the spinner.
     *
     * @param parent The AdapterView where the nothing-selected event happened.
     */


    //__________________________________________________________________________________________________________________________________________________
    public void onNothingSelected(AdapterView<?> parent) {
        Log.i("SpinDemo", "Nothing selected");
    }

    /**
     * Displays worker data in the ListView.
     *
     * @param lvrecords The ListView to display the data in.
     * @param tbl       The ArrayList to store the data.
     * @param crsr      The Cursor to read the data from.
     * @param hlp       The HelperDB instance.
     * @param db        The SQLiteDatabase instance.
     * @return The ArrayList containing the worker data.
     */

    //_________________________________________________________________________________________________________________________________________________
    public  ArrayList worker_show(ListView lvrecords,ArrayList<String> tbl,Cursor crsr,HelperDB hlp,SQLiteDatabase db)
    {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();


        crsr = db.query(Worker.TABLE_WORKER, null, null, null, null, null, null);

        int col1 = crsr.getColumnIndex(KEY_ID);
        int col2 = crsr.getColumnIndex(Worker.ID);
        int col3 = crsr.getColumnIndex(Worker.NAME);
        int col4 = crsr.getColumnIndex(Worker.CARD_NUMBER);
        int col5 = crsr.getColumnIndex(Worker.LAST_NAME);
        int col6 = crsr.getColumnIndex(Worker.PHONE_NUMBER);
        int col7 = crsr.getColumnIndex(Worker.THE_COMPANY_HE_WORKS_FOR);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String id = crsr.getString(col2);
            String name = crsr.getString(col3);
            String card = crsr.getString(col4);
            String last = crsr.getString(col5);
            String phone = crsr.getString(col6);
            String company = crsr.getString(col7);

            String tmp = "key: "+key + "\n\n id: " + id + "\n\n name: " + name + "\n\nlastname: " + last + "\n\n phone_number:  " + phone + "\n\ncompany_name: " + company;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl ;
    }
    /**
     * Displays worker data in the ListView, sorted by last name.
     *
     * @param lvrecords The ListView to display the data in.
     * @param tbl       The ArrayList to store the data.
     * @param crsr      The Cursor to read the data from.
     * @param hlp       The HelperDB instance.
     * @param db        The SQLiteDatabase instance.
     * @return The ArrayList containing the sorted worker data.
     */
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<String> worker_show_sort(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();

        crsr = db.query(Worker.TABLE_WORKER, null, null, null, null, null, Worker.LAST_NAME + " ASC");

        int col1 = crsr.getColumnIndex(KEY_ID);
        int col2 = crsr.getColumnIndex(Worker.ID);
        int col3 = crsr.getColumnIndex(Worker.NAME);
        int col4 = crsr.getColumnIndex(Worker.CARD_NUMBER);
        int col5 = crsr.getColumnIndex(Worker.LAST_NAME);
        int col6 = crsr.getColumnIndex(Worker.PHONE_NUMBER);
        int col7 = crsr.getColumnIndex(Worker.THE_COMPANY_HE_WORKS_FOR);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String id = crsr.getString(col2);
            String name = crsr.getString(col3);
            String card = crsr.getString(col4);
            String last = crsr.getString(col5);
            String phone = crsr.getString(col6);
            String company = crsr.getString(col7);

            String tmp = "key: " + key + "\n\n id: " + id + "\n\n name: " + name + "\n\nlastname: " + last + "\n\n phone_number:  " + phone + "\n\ncompany_name: " + company;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }
    /**
     * Displays ParkFood data in the ListView.
     *
     * @param lvrecords The ListView to display the data in.
     * @param tbl       The ArrayList to store the data.
     * @param crsr      The Cursor to read the data from.
     * @param hlp       The HelperDB instance.
     * @param db        The SQLiteDatabase instance.
     * @return The ArrayList containing the ParkFood data.
     */


    //_______________________________________________________________________________________________________________________________________________
    public ArrayList<String> parkfood_show(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();

        crsr = db.query(ParkFood.TABLE_PARKFOOD, null, null, null, null, null, null);

        int col1 = crsr.getColumnIndex(ParkFood.KEY_ID);
        int col2 = crsr.getColumnIndex(ParkFood.COMPANY_ID);
        int col3 = crsr.getColumnIndex(ParkFood.NAME_COMPANY);
        int col4 = crsr.getColumnIndex(ParkFood.MAIN_PHONE);
        int col5 = crsr.getColumnIndex(ParkFood.SECONDARY_PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String companyId = crsr.getString(col2);
            String companyName = crsr.getString(col3);
            String mainPhone = crsr.getString(col4);
            String secondaryPhone = crsr.getString(col5);

            String tmp = "key: " + key + "\n\nCompany ID: " + companyId + "\n\nCompany Name: " + companyName + "\n\n Main Phone: " + mainPhone + "\n\n Secondary Phone: " + secondaryPhone;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }
    /**
     * Displays ParkFood data in the ListView, sorted by company name.
     *
     * @param lvrecords The ListView to display the data in.
     * @param tbl       The ArrayList to store the data.
     * @param crsr      The Cursor to read the data from.
     * @param hlp       The HelperDB instance.
     * @param db
     */

    //---------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<String> parkfood_show_sort(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();


        crsr = db.query(ParkFood.TABLE_PARKFOOD, null, null, null, null, null, ParkFood.NAME_COMPANY + " ASC");

        int col1 = crsr.getColumnIndex(ParkFood.KEY_ID);
        int col2 = crsr.getColumnIndex(ParkFood.COMPANY_ID);
        int col3 = crsr.getColumnIndex(ParkFood.NAME_COMPANY);
        int col4 = crsr.getColumnIndex(ParkFood.MAIN_PHONE);
        int col5 = crsr.getColumnIndex(ParkFood.SECONDARY_PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String companyId = crsr.getString(col2);
            String companyName = crsr.getString(col3);
            String mainPhone = crsr.getString(col4);
            String secondaryPhone = crsr.getString(col5);

            String tmp = "key: " + key + "\n\nCompany ID: " + companyId + "\n\nCompany Name: " + companyName + "\n\n Main Phone: " + mainPhone + "\n\n Secondary Phone: " + secondaryPhone;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }

    /**
     * Displays meal data in the ListView.
     *
     * @param lvrecords The ListView to display the data in.
     * @param tbl       The ArrayList to store the data.
     * @param crsr      The Cursor to read the data from.
     * @param hlp       The HelperDB instance.
     * @param db        The SQLiteDatabase instance.
     * @return The ArrayList containing the meal data.
     */

    //________________________________________________________________________________________________________

    public ArrayList<String> meal_show(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();

        crsr = db.query(Meal.TABLE_MEAL, null, null, null, null, null, null);

        int col1 = crsr.getColumnIndex(Meal.KEY_ID);
        int col2 = crsr.getColumnIndex(Meal.STARTER);
        int col3 = crsr.getColumnIndex(Meal.MAIN_MEAL);
        int col4 = crsr.getColumnIndex(Meal.SIDE_MEAL);
        int col5 = crsr.getColumnIndex(Meal.DESSERT);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String starter = crsr.getString(col2);
            String mainMeal = crsr.getString(col3);
            String sideMeal = crsr.getString(col4);
            String dessert = crsr.getString(col5);

            String tmp = "key: " + key + "\n\n Starter: " + starter + "\n\n ,Main Meal: " + mainMeal + "\n\n Side Meal: " + sideMeal + "\n\n Dessert: " + dessert;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }
    /**
     * Displays order data in the ListView.
     *
     * @param lvrecords The ListView to display the data in.
     * @param tbl       The ArrayList to store the data.
     * @param crsr      The Cursor to read the data from.
     * @param hlp       The HelperDB instance.
     * @param db        The SQLiteDatabase instance.
     * @return The ArrayList containing the order data.
     */

    //_____________________________________________________________________________________________________________________________________-

    public ArrayList<String> order_show(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();
        crsr = db.query(Order.TABLE_ORDER, null, null, null, null, null, null);

        int col1 = crsr.getColumnIndex(Order.KEY_ID);
        int col2 = crsr.getColumnIndex(Order.DATE);
        int col3 = crsr.getColumnIndex(Order.TIME);
        int col4 = crsr.getColumnIndex(Order.EMPLOYEE);
        int col5 = crsr.getColumnIndex(Order.MEAL);
        int col6 = crsr.getColumnIndex(Order.PROVIDER_COMPANY);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String date = crsr.getString(col2);
            String time = crsr.getString(col3);
            String employee = crsr.getString(col4);
            String meal = crsr.getString(col5);
            String providerCompany = crsr.getString(col6);

            String tmp = "key: " + key + "\n\n Date: " + date + "\n\n Time: " + time + "\n\n Employee: " + employee + "\n\n Meal: " + meal + "\n\n Provider Company: " + providerCompany;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }

    /**
     * Displays order data in the ListView, sorted by employee name.
     *
     * @param lvrecords The ListView to display the data in.
     * @param tbl       The ArrayList to store the data.
     * @param crsr      The Cursor to read the data from.
     * @param hlp       The HelperDB instance.
     * @param db        The SQLiteDatabase instance.
     * @return The ArrayList containing the sorted order data.
     */

    public ArrayList<String> order_show_sort(ListView lvrecords, ArrayList<String> tbl, Cursor crsr, HelperDB hlp, SQLiteDatabase db) {
        hlp = new HelperDB(this);
        db = hlp.getReadableDatabase();

        tbl = new ArrayList<>();
        keysList = new ArrayList<>();
        crsr = db.query(Order.TABLE_ORDER, null, null, null, null, null, Order.EMPLOYEE + " ASC");

        int col1 = crsr.getColumnIndex(Order.KEY_ID);
        int col2 = crsr.getColumnIndex(Order.DATE);
        int col3 = crsr.getColumnIndex(Order.TIME);
        int col4 = crsr.getColumnIndex(Order.EMPLOYEE);
        int col5 = crsr.getColumnIndex(Order.MEAL);
        int col6 = crsr.getColumnIndex(Order.PROVIDER_COMPANY);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String date = crsr.getString(col2);
            String time = crsr.getString(col3);
            String employee = crsr.getString(col4);
            String meal = crsr.getString(col5);
            String providerCompany = crsr.getString(col6);

            String tmp = "key: " + key + "\n\n Date: " + date + "\n\n Time: " + time + "\n\n Employee: " + employee + "\n\n Meal: " + meal + "\n\n Provider Company: " + providerCompany;
            tbl.add(tmp);
            keysList.add(key);
            crsr.moveToNext();
        }

        crsr.close();
        db.close();
        return tbl;
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Finishes the current activity and returns to the previous one.
     *
     * @param view
     */
    public void back(View view)
{
    finish();
}


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        count2 = i ;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        count2 = i ;
        return false;
    }

    /**
     * create the options menu
     *
     * @param menu The options menu
     * @return return true
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Checks the selection in the options menu
     *
     * @param menu The selected menu item.
     * @return return true
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String num1 = menu.getTitle().toString();
        if (num1.equals("credits"))
        {
            Intent si = new Intent(this,credits_menu.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(menu);
    }

}



