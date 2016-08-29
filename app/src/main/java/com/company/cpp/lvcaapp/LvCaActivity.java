package com.company.cpp.lvcaapp;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

public class LvCaActivity extends AppCompatActivity {

    // DRS 20160829 - removed all references to dataAdapter / MySimpleCursorAdapter
    //private SimpleCursorAdapter dataAdapter;
    private DbAdapter dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv_ca);

        dbHelper = new DbAdapter(this);
        dbHelper.open();
        dbHelper.deleteAll();
        dbHelper.insertSome();

        Cursor bCursor = dbHelper.fetchAll(DbAdapter.bColumns);

        /*
        dataAdapter = new MySimpleCursorAdapter(
                this, R.layout.b_item,
                bCursor,
                DbAdapter.bColumns,
                ViewHolder.viewsArray,
                0);
        */

        MyRecyclerCursorAdapter cursorAdapter = new MyRecyclerCursorAdapter(this, bCursor);

        // DRS 20160829 - Changed to recyclerView1 from listView1
        // ListView listView = (ListView) findViewById(R.id.listView1);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);

        recyclerView.setAdapter(cursorAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
