package com.example.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class parkfood_activity extends AppCompatActivity {

    EditText etCompanyId,etCompanyName , etMainPhone, etSecondaryPhone;
    Button btnInsert ,btnBack;
    SQLiteDatabase db;
    HelperDB hlp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkfood);

        hlp = new HelperDB(this);

        etCompanyId = findViewById(R.id.etStartermeal);
        etCompanyName = findViewById(R.id.etmainmeal);
        etMainPhone = findViewById(R.id.etsidemeal);
        etSecondaryPhone = findViewById(R.id.etdessert);

        btnInsert = findViewById(R.id.btnInsert);
        btnBack = findViewById(R.id.back_btn);
    }
    public void onClick2(View v) {
        db = hlp.getWritableDatabase();
        if (etCompanyId.getText().toString().isEmpty() || etCompanyName.getText().toString().isEmpty() || etMainPhone.getText().toString().isEmpty() || etSecondaryPhone.getText().toString().isEmpty() ) {
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
        }
    }
    public void back (View view)
    {
        finish();
    }
}