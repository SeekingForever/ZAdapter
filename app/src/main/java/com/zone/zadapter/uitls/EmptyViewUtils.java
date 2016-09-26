package com.zone.zadapter.uitls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.zone.zadapter.R;

public class EmptyViewUtils {
    public static  void setEmptyView(Context context, ListView listview) {
        View view = listview.getEmptyView();
        if(view==null){
            View emptyView = LayoutInflater.from(context).inflate(R.layout.emptyview, null);
            emptyView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, AbsListView.LayoutParams.FILL_PARENT));
            ((ViewGroup)listview.getParent()).addView(emptyView);
            listview.setEmptyView(emptyView);
        }
    }
}