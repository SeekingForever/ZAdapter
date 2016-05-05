package com.zone.adapter.callback;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zone.adapter.loadmore.callback.OnLoadMoreListener;

import java.util.List;

/**
 * Generic adapter must implement the interface, as a method of unified standard
 */
public interface IAdapter<T> {

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     * @param itemChanged Whether or not the helper was bound to another object before.
     * @param layoutId Layout of the ID can be switch 0 is the default value of the single layout can not be considered
     */
     void fillData(Helper<T>  helper, T item, boolean itemChanged, int layoutId);

    /**
     * @param t A item of data in list
     * @return Mustbe returns layout ID
     */
    int getItemLayoutId(T t, int position);

    void relatedList(Object listView);

    IAdapter ani();

    void add(T elem);

    void add(int index,T elem);

    void addAll(List<T> elem);

    void reverse(int fromPosition,int toPosition);

    void set(int index, T elem);

    void set(T oldElem, T newElem);

    void remove(T elem);

    void remove(int index);


    void replaceAll(List<T> elem);

    boolean contains(T elem);

    void clear();

    void notifyDataSetChanged();

    void notifyItemChanged(int position);


    void setOnItemClickListener(OnItemClickListener listener);

    void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener);

    OnItemClickListener getOnItemClickListener();

    OnItemLongClickListener getOnItemLongClickListener();

    interface OnItemClickListener {
        void onItemClick(ViewGroup parent, View view, int position, long id);
    }
    interface OnItemLongClickListener {
        boolean onItemLongClick(ViewGroup parent, View view, int position, long id);
    }


    void addHeaderView(View header);
    void addFooterView(View footer);

    void removeHeaderView(View header);
    void removeFooterView(View footer);

    int getHeaderViewsCount();
    int getFooterViewsCount();

    void setOnLoadMoreListener(OnLoadMoreListener listener);
    void onLoadMoreComplete();
    void removeOnLoadMoreListener();
    void onLoadMoreFail();
    Context getContext();
}