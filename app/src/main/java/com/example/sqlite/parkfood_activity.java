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
 * Activity for managing ParkFood data.
 * Allows inserting new ParkFood company records into the database.
 */
public class parkfood_activity extends AppCompatActivity {

    EditText etCompanyId, etCompanyName, etMainPhone, etSecondaryPhone;
    Button btnInsert, btnBack;
    SQLiteDatabase db;
    HelperDB hlp;

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
        setContentView(R.layout.activity_parkfood);

        hlp = new HelperDB(this);

        etCompanyId = findViewById(R.id.etCompanyId);
        etCompanyName = findViewById(R.id.etCompanyName);
        etMainPhone = findViewById(R.id.etMainPhone);
        etSecondaryPhone = findViewById(R.id.etSecondaryPhone);

        btnInsert = findViewById(R.id.btnInsert);
        btnBack = findViewById(R.id.back_btn);
    }

    /**
     * Inserts a new ParkFood company record into the database.
     * Retrieves data from the input fields, validates it, and then inserts it into the database.
     *
     * @param v The View that triggered this method (the insert button).
     */
    public void onClick2(View v) {
        db = hlp.getWritableDatabase();
        if (etCompanyId.getText().toString().isEmpty() || etCompanyId.equals("-.") || etCompanyId.equals("+") || etCompanyId.equals("+.") || etCompanyName.getText().toString().isEmpty() ||  etCompanyName.equals("-.") || etCompanyName.equals("+") || etCompanyName.equals("+.") ||  etMainPhone.getText().toString().isEmpty() || etMainPhone.equals("-.") || etSecondaryPhone.getText().toString().isEmpty() || etSecondaryPhone.equals("-.")) {
            Toast.makeText(this, "invald input", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(ParkFood.COMPANY_ID, etCompanyId.getText().toString());
            values.put(ParkFood.NAME_COMPANY, etCompanyName.getText().toString());
            values.put(ParkFood.MAIN_PHONE, etMainPhone.getText().toString());
            values.put(ParkFood.SECONDARY_PHONE, etSecondaryPhone.getText().toString());

            db.insert(ParkFood.TABLE_PARKFOOD, null, values);
            db.close();

            etCompanyId.setText("");
            etCompanyName.setText("");
            etMainPhone.setText("");
            etSecondaryPhone.setText("");
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
     * Creates the options menu.
     *
     * @param menu The options menu.
     * @return True to display the menu.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Handles item selections in the options menu.
     *
     * @param menu The selected menu item.
     * @return True if the item was handled, false otherwise.
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