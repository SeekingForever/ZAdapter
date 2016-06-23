package com.zone.zadapter.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by sxl on 2016/6/23.
 */
public class TypeItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //ItemDecoration 绘制  可以是颜色也可以是图片
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //整体绘制
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        //ItemDecoration 设置宽高
        resloveManager2Offset(view, parent, state);
        outRect.set( left,top,right,bottom);
    }
    public int left,top,right,bottom;
    private void resloveManager2Offset(View view, RecyclerView parent, RecyclerView.State state) {
        left=0;top=0;right=0;bottom=0;
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();//获取位置
        int totalItemCount = parent.getAdapter().getItemCount();//总数
        RecyclerView.LayoutManager manager = parent.getLayoutManager();

        if (manager instanceof GridLayoutManager) {
            GridLayoutManager temp = (GridLayoutManager) manager;
            int spanCount = temp.getSpanCount();//行数量
//            temp.getOrientation();//获取方向
            getItemOffsets_GridLayoutManager(view, parent, state, temp, spanCount,itemPosition,totalItemCount);

        }
        if (manager != null && manager instanceof StaggeredGridLayoutManager) {
            //暂时不管了
            StaggeredGridLayoutManager temp = (StaggeredGridLayoutManager) manager;
            getItemOffsets_StaggeredGridLayoutManager( view, parent, state, temp,itemPosition,totalItemCount);
        }
        if (manager != null && manager instanceof LinearLayoutManager) {
            LinearLayoutManager temp = (LinearLayoutManager) manager;
            ((LinearLayoutManager) manager).getOrientation();//获取方向
            getItemOffsets_LinearLayoutManager( view, parent, state, temp,itemPosition,totalItemCount);
        }
    }



    public void getItemOffsets_LinearLayoutManager(View view, RecyclerView parent,
                                                    RecyclerView.State state, LinearLayoutManager temp,
                                                    int itemPosition, int totalItemCount) {

    }

    public void getItemOffsets_StaggeredGridLayoutManager( View view, RecyclerView parent,
                                                           RecyclerView.State state, StaggeredGridLayoutManager temp,
                                                           int itemPosition, int totalItemCount) {

    }

    public void getItemOffsets_GridLayoutManager( View view, RecyclerView parent,
                                                 RecyclerView.State state, GridLayoutManager temp, int spanCount,
                                                 int itemPosition, int totalItemCount) {

    }


}
