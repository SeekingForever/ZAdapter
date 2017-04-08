package com.zone.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.zone.adapter.Helper.BaseAdapterHelperRcv;
import com.zone.adapter.Helper.ViewHolderWithRecHelper;
import com.zone.adapter.base.Header2FooterRcvAdapter;
import com.zone.adapter.callback.Helper;

import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public abstract class QuickRcvAdapter<T> extends Header2FooterRcvAdapter<T> {

    public QuickRcvAdapter(Context context, List<T> data) {
        super(context, data, 1);

    }

    //The third number is useless but in order to be consistent with the quickadapter foreign method you know
    public QuickRcvAdapter(Context context, List<T> data, int viewTypeCount) {
        super(context, data, viewTypeCount);
    }

    @Override
    public void onBindContentViewHolder(ViewHolderWithRecHelper holder, int position) {
            T item = data.get(position);
            boolean itemChanged = (holder.baseAdapterHelperRcv.getData() == null || !holder.baseAdapterHelperRcv.getData().equals(item));
            //Object position to maintain the accuracy of the data before using
            holder.baseAdapterHelperRcv.setData(item, position);
            if(holder.baseAdapterHelperRcv.getExtraObject()==null)
                bindHelperExtra(holder.baseAdapterHelperRcv,item,getItemViewType(position));
            fillData(holder.baseAdapterHelperRcv, item, itemChanged, getItemViewType(position));
    }

    public  void bindHelperExtra(Helper<T> helper, T item, int itemViewType){

    }

    @Override
    public ViewHolderWithRecHelper onCreateContentView(ViewGroup parent, int viewType) {
        return ViewHolderWithRecHelper.newInstance(context, parent, viewType, this);
    }
}
