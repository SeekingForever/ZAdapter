package com.zone.zadapter.decoration.other;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
  
        private int space;  
  
        public SpaceItemDecoration(int space) {  
            this.space = space;  
        }  
  
        @Override  
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
  
//            if(parent.getChildPosition(view) != 0)
//                outRect.top = space;
            int itemCount = parent.getAdapter().getItemCount();
            int pos = parent.getChildAdapterPosition(view);

            outRect.left = 0;
            outRect.top = 0;
            outRect.bottom = 0;


            if (pos != (itemCount -1)) {
                outRect.right = space;
            } else {
                outRect.right = 0;
            }
        }  
    }