package com.example.sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db ;
    HelperDB hlp ;
    TextView tv ;

    Button worker_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        worker_btn = findViewById(R.id.worker_btn);
        tv = findViewById(R.id.tv);

        tv.setText("To insert something into the tables, select one of the tables.");
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
    public void orderclick(View view)
    {
        Intent si = new Intent(this, order_activity.class);
        startActivity(si);

    }
    public void show(View view)
    {
        Intent si = new Intent(this, displaying_tables.class);
        startActivity(si);

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