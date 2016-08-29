package com.company.cpp.lvcaapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

//DRS 20160829 - Added class.  Replaces MySimpleCursorAdapter
public class MyRecyclerCursorAdapter extends RecyclerView.Adapter{

    private Cursor cursor;
    private Context context;
    private static final String TAG = MyRecyclerCursorAdapter.class.getSimpleName();

    public MyRecyclerCursorAdapter(Context context, Cursor cursor) {
        this.cursor = cursor;
        this.context = context;
    }

    //DRS 20160829 - Critical method within new class
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v(TAG, "onCreateViewHolder ");
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.b_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView, cursor);
        return viewHolder;
    }

    //DRS 20160829 - Critical method within new class
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        ((ViewHolder)holder).bindFields(cursor);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
