package com.zone.zadapter.decoration;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sxl on 2016/6/23.
 */
public class GridSpaceDectoration extends TypeItemDecoration {
    private int space;

    public GridSpaceDectoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets_GridLayoutManager( View view, RecyclerView parent, RecyclerView.State state, GridLayoutManager temp, int spanCount, int itemPosition, int totalItemCount) {
        super.getItemOffsets_GridLayoutManager( view, parent, state, temp, spanCount, itemPosition, totalItemCount);
        bottom=space;
        left=space/2;
        right=space/2;
        if(itemPosition>=totalItemCount-totalItemCount%spanCount){
            //最后一行
            bottom=0;
        }
    }
}
