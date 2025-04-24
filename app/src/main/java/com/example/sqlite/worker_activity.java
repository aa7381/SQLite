package com.example.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class worker_activity extends AppCompatActivity {

    EditText etCard, etName, etLastName, etPhone, etId, etCompany;
    Button btnInsert;
    SQLiteDatabase db;
    HelperDB hlp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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

    public void onClick(View v) {
        db = hlp.getWritableDatabase();
        if (etCard.getText().toString().isEmpty() || etName.getText().toString().isEmpty() || etLastName.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty() || etId.getText().toString().isEmpty() || etCompany.getText().toString().isEmpty()) {
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
        }
    }

    public void back (View view)
    {
        finish();
    }
}

