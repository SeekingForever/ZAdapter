/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Piasy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.zone.zadapter.uitls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class QuickDecoration extends RecyclerView.ItemDecoration {

    private LinearGradient mLinearGradient = new LinearGradient(0, 0, 0, 100, new int[] { Color.BLACK, 0 }, null,
                    Shader.TileMode.CLAMP);
    private Paint mPaint = new Paint();

    private Drawable mDivider;

    public QuickDecoration() {
        mDivider = new ColorDrawable(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //ItemDecoration 绘制  可以是颜色也可以是图片

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - 10;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =(RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + 25;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //整体绘制
        mPaint.setShader(mLinearGradient);
        c.drawRect(0, 0, parent.getRight(), 100, mPaint);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        //ItemDecoration 设置宽高
        resloveManager(parent);
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();//获取位置
        int totalItemCount = parent.getAdapter().getItemCount();//总数
        if (itemPosition == totalItemCount - 1) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(0,0,0,25);
        }
    }
    RecyclerView.LayoutManager manager;
    private void resloveManager(RecyclerView parent) {
        if(manager!=null)
            return ;
        manager= parent.getLayoutManager();
        if(manager instanceof GridLayoutManager){
            GridLayoutManager temp  = (GridLayoutManager) manager;
            int spanCount=temp.getSpanCount();//行数量
            temp.getOrientation();//获取方向
        }
        if(manager!=null&& manager instanceof StaggeredGridLayoutManager){
            //暂时不管了
            StaggeredGridLayoutManager temp  = (StaggeredGridLayoutManager) manager;
        }
        if(manager!=null&& manager instanceof LinearLayoutManager){
            LinearLayoutManager temp  = (LinearLayoutManager) manager;
            ((LinearLayoutManager) manager).getOrientation();//获取方向
        }
    }


}

