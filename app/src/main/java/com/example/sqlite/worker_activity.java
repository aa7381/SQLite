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
 * Activity for managing Worker data.
 * Allows inserting new worker records into the database.
 */
public class worker_activity extends AppCompatActivity {

    EditText etCard, etName, etLastName, etPhone, etId, etCompany;
    Button btnInsert;
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
        setContentView(R.layout.activity_worker);

        hlp = new HelperDB(this);

        etCard = findViewById(R.id.etCard);
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etPhone = findViewById(R.id.etPhone);
        etId = findViewById(R.id.etId);
        etCompany = findViewById(R.id.etCompany);
        btnInsert = findViewById(R.id.btnInsert);

    }

    /**
     * Inserts a new worker record into the database.
     * Retrieves data from the input fields, validates it, and then inserts it into the database.
     *
     * @param v The View that triggered this method (the insert button).
     */
    public void onClick(View v) {
        db = hlp.getWritableDatabase();
        if (etCard.getText().toString().isEmpty() || etCard.equals("-") || etCard.equals("-.") || etCard.equals("+") || etCard.equals("+.") || etName.getText().toString().isEmpty() ||  etName.equals("-.") || etName.equals("+") || etName.equals("+.") || etLastName.getText().toString().isEmpty() || etLastName.equals("-") || etLastName.equals("-.") || etPhone.getText().toString().isEmpty() || etPhone.equals("-.") || etId.getText().toString().isEmpty() || etId.equals("-") || etCompany.getText().toString().isEmpty() || etCompany.equals("-.")) {
            Toast.makeText(this, "invald input", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(Worker.CARD_NUMBER, etCard.getText().toString());
            values.put(Worker.NAME, etName.getText().toString());
            values.put(Worker.LAST_NAME, etLastName.getText().toString());
            values.put(Worker.PHONE_NUMBER, etPhone.getText().toString());
            values.put(Worker.ID, etId.getText().toString());
            values.put(Worker.THE_COMPANY_HE_WORKS_FOR, etCompany.getText().toString());

            db.insert(Worker.TABLE_WORKER, null, values);
            db.close();

            etCard.setText("");
            etName.setText("");
            etLastName.setText("");
            etPhone.setText("");
            etId.setText("");
            etCompany.setText("");
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