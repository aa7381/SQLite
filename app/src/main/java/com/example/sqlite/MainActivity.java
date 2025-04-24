package com.example.sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db ;
    HelperDB hlp ;

    Button worker_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        worker_btn = findViewById(R.id.worker_btn);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }

public void workerclick (View view)
{
    Intent si = new Intent(this, worker_activity.class);
    startActivity(si);

}
    public void Parkfoodclick(View view)
    {
        Intent si = new Intent(this, parkfood_activity.class);
        startActivity(si);

    }
    public void mealclick(View view)
    {
        Intent si = new Intent(this, meal_activity.class);
        startActivity(si);

    }

}