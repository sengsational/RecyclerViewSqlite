package com.company.cpp.lvcaapp;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "ViewHolder";
    private final Cursor cursor;

    public TextView bDbItem;
    public TextView bName;
    public TextView bSecondLine;
    public TextView bHidden;

    public static final int DB_ITEM = 0;
    public static final int NAME = 1;
    public static final int SECOND_LINE = 2;
    public static final int HIDDEN = 3;

    public ViewHolder(View root, Cursor cursor ) {
        super(root);
        this.cursor = cursor;
        Log.v(TAG, "ViewHolder constructor");
        bDbItem = (TextView) itemView.findViewById(R.id.bDbItem);
        bName = (TextView) itemView.findViewById(R.id.bName);
        bSecondLine = (TextView) itemView.findViewById(R.id.bSecondLine);
        bHidden = (TextView) itemView.findViewById(R.id.bHidden);
    }

    public void bindFields(Cursor cursor) {

        bDbItem.setText("" + cursor.getInt(DB_ITEM));
        bName.setText(cursor.getString(NAME));
        bSecondLine.setText(cursor.getString(SECOND_LINE));

        String hidden = cursor.getString(HIDDEN);
        bHidden.setText(hidden);
        if ("F".equals(hidden)) {
            bSecondLine.setVisibility(View.VISIBLE);
        } else {
            bSecondLine.setVisibility(View.INVISIBLE);
        }
    }
}
