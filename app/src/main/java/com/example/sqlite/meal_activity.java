package com.example.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity for managing Meal data.
 * Allows inserting new meal records into the database.
 */
public class meal_activity extends AppCompatActivity {

    SQLiteDatabase db;
    HelperDB hlp;

    EditText etStartermeal, etmainmeal, etsidemeal, etdessert;

    Button btnInsert, back_btn;


    /**
     * Initializes the activity, sets up UI elements, and prepares the database helper.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        etStartermeal = findViewById(R.id.etStartermeal);
        etmainmeal = findViewById(R.id.etmainmeal);
        etsidemeal = findViewById(R.id.etsidemeal);
        etdessert = findViewById(R.id.etdessert);

        hlp = new HelperDB(this);

        btnInsert = findViewById(R.id.btnInsert);
        back_btn = findViewById(R.id.back_btn);


    }

    /**
     * Inserts a new meal record into the database.
     * Retrieves data from the input fields, validates it, and then inserts it into the database.
     *
     * @param v The View that triggered this method (the insert button).
     */
    public void onClick2(View v) {
        db = hlp.getWritableDatabase();
        if (etStartermeal.getText().toString().isEmpty() || etmainmeal.getText().toString().isEmpty() || etsidemeal.getText().toString().isEmpty() || etdessert.getText().toString().isEmpty()) {
            Toast.makeText(this, "invald input", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(Meal.STARTER, etStartermeal.getText().toString());
            values.put(Meal.MAIN_MEAL, etmainmeal.getText().toString());
            values.put(Meal.SIDE_MEAL, etsidemeal.getText().toString());
            values.put(Meal.DESSERT, etdessert.getText().toString());

            db.insert(Meal.TABLE_MEAL, null, values);
            db.close();

            etStartermeal.setText("");
            etmainmeal.setText("");
            etsidemeal.setText("");
            etdessert.setText("");
            Toast.makeText(this, "The data has been saved", Toast.LENGTH_SHORT).show();


        }
    }

    /**
     * Finishes the current activity and returns to the previous one.
     *
     * @param view The View that triggered this method (the back button).
     */
    public void back(View view) {
        finish();
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
        if (num1.equals("credits")) {
            Intent si = new Intent(this, credits_menu.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(menu);
    }

}