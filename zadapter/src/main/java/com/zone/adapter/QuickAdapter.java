package com.zone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zone.adapter.Helper.BaseAdapterHelper;
import com.zone.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstraction class of a BaseAdapter in which you only need
 * to provide the fillData() implementation.
 * Using the provided BaseAdapterHelper, your code is minimalist.
 *
 * @param <T> The type of the items in the list.
 */
public abstract class QuickAdapter<T> extends BaseQuickAdapter<T> {
    protected static final String TAG = QuickAdapter.class.getSimpleName();
    private int firstLayoutId = -1;

    public QuickAdapter(Context context, List<T> data) {
        super(context, data, 1);
    }

    /**
     *
     * @param context
     * @param data
     * @param viewTypeCount   not Override method:getViewTypeCount();
     */
    public QuickAdapter(Context context, List<T> data, int viewTypeCount) {
        super(context, data, viewTypeCount);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= data.size()) return null;
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        final BaseAdapterHelper<T> helper =new BaseAdapterHelper(context,convertView,parent, getItemViewType(position), this);
        BaseAdapterHelper<T> helper = BaseAdapterHelper.get(context, convertView, parent, layoutIDArrays.get(getItemViewType(position)), this);
        T item = getItem(position);
        boolean itemChanged = (helper.getData() == null || !helper.getData().equals(item));
        //Object position to maintain the accuracy of the data before using
        helper.setData(item, position);
        if(helper.getExtraObject()==null)
            helperInitExtraObject(helper,item,getItemViewType(position));
        //How to do more layout
        fillData(helper, item, itemChanged, getItemViewType(position));
        return helper.getView();
    }

    public void helperInitExtraObject(BaseAdapterHelper<T> helper, T item, int itemViewType) {

    }

    private List<Integer> layoutIDArrays = new ArrayList<>();

    /**
     * @param position
     * @return 注意:getItemViewType(int) can not return int value larger than getViewTypeCount().
     * 例如:getViewTypeCount 是7 那么getItemViewType 不能是7是 小于7 既6是可以的;
     * Otherwise you will get java.lang.ArrayIndexOutOfBoundsException at android.widget.AbsListView$RecycleBin.addScrapView
     */
    @Override
    public int getItemViewType(int position) {
        int resultId = getItemLayoutId(data.get(position), position);
        if (firstLayoutId == -1)
            firstLayoutId = resultId;
        else if (firstLayoutId != resultId && viewTypeCount == 1)
            throw new IllegalStateException("must be use set Params: viewTypeCount use Method:getViewTypeCount or Construtor! ");

        if (layoutIDArrays.size() < viewTypeCount && !layoutIDArrays.contains(resultId))
            layoutIDArrays.add(resultId);
        return layoutIDArrays.indexOf(resultId);
    }

    //the default value is 1
    @Override
    public int getViewTypeCount() {
        return viewTypeCount;
    }

    /**
     * If a list item is a separator (acting as separate items and other item to, also can not the same but not click), returns true,
     * that is, you can click and then receive a response event. If, at the time the position of the item is the separator,
     * return false, also cannot respond to a click or touch events,
     * this project is not clickable, is expressed in the form of point without any reaction can act as a list of partitions,
     * of course, can be separated by this custom layout
     *
     * @param position
     * @return
     */
    @Override
    public boolean isEnabled(int position) {
        return position < data.size();
    }
}
