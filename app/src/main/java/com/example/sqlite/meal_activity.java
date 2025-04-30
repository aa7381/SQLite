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

public class meal_activity extends AppCompatActivity {

    SQLiteDatabase db;
    HelperDB hlp;

    EditText etStartermeal , etmainmeal , etsidemeal , etdessert ;

    Button btnInsert , back_btn ;


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
        back_btn= findViewById(R.id.back_btn);


    }

    public void onClick2(View v) {
        db = hlp.getWritableDatabase();
        if (etStartermeal.getText().toString().isEmpty() || etmainmeal.getText().toString().isEmpty() || etsidemeal.getText().toString().isEmpty() || etdessert.getText().toString().isEmpty() ) {
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
        }
    }
    public void back (View view)
    {
        finish();
    }
}




