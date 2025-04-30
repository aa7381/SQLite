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

public class order_activity extends AppCompatActivity {

    EditText etdate, ettime, etemployee, etprovcomp;
    Button btnInsert , back_btn;
    SQLiteDatabase db;
    HelperDB hlp;

    String count = Meal.KEY_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        hlp = new HelperDB(this);

        etdate = findViewById(R.id.etdate);
        ettime = findViewById(R.id.ettime);
        etemployee = findViewById(R.id.etemployee);
        etprovcomp = findViewById(R.id.etprovcomp);

        btnInsert = findViewById(R.id.btnInsert);
        back_btn = findViewById(R.id.back_btn);


    }
    public void onClick(View v) {


        db = hlp.getWritableDatabase();
        if (etdate.getText().toString().isEmpty() || ettime.getText().toString().isEmpty() || etemployee.getText().toString().isEmpty() ||  etprovcomp.getText().toString().isEmpty()) {
            Toast.makeText(this, "invald input", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(Order.DATE, etdate.getText().toString());
            values.put(Order.TIME, ettime.getText().toString());
            values.put(Order.EMPLOYEE, etemployee.getText().toString());
            values.put(Order.MEAL,Meal.KEY_ID);
            values.put(Order.PROVIDER_COMPANY, etprovcomp.getText().toString());

            db.insert(Order.TABLE_ORDER, null, values);
            db.close();

            etdate.setText("");
            ettime.setText("");
            etemployee.setText("");
            etprovcomp.setText("");
        }
    }

    public void back (View view)
    {
        finish();
    }
}
