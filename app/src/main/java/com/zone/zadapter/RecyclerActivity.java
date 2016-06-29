package com.zone.zadapter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zone.adapter.QuickManager;
import com.zone.adapter.callback.IAdapter;
import com.zone.adapter.loadmore.callback.OnLoadMoreListener;
import com.zone.zadapter.adapter.RecyclerBaseAdapterTest_Muli;

import java.util.ArrayList;
import java.util.List;

//todo animation
public class RecyclerActivity extends Activity implements Handler.Callback, View.OnClickListener {

    private RecyclerView rv;
    private List<String> mDatas = new ArrayList<String>();
    private RecyclerBaseAdapterTest_Muli muliAdapter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler(this);
        setContentView(R.layout.a_recycler);
        rv = (RecyclerView) findViewById(R.id.rv);
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        muliAdapter = new RecyclerBaseAdapterTest_Muli(this, mDatas);
        //base test
        String type = getIntent().getStringExtra("type");
        if ("Linear".equals(type)) {
            rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//            rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }
        if ("Grid".equals(type)) {
            rv.setLayoutManager(new GridLayoutManager(this, 3));
        }
        if ("StaggeredGrid".equals(type)) {
            rv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
            muliAdapter.openHeightRanmdom(true);
        }
        rv.setItemAnimator(new DefaultItemAnimator());
        QuickManager.with(muliAdapter, rv)
                .addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_simple, null))
                .addFooterView(LayoutInflater.from(this).inflate(R.layout.footer_simple, null))
                .setOnLoadMoreListener(new OnLoadMoreListener() {
                    boolean refesh = true;

                    @Override
                    public void onLoadMore() {
                        final List<String> mDatasa = new ArrayList<String>();
                        for (int i = 0; i < 5; i++) {
                            mDatasa.add("insert " + i);
                        }
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (refesh) {
                                    muliAdapter.onLoadMoreComplete();
                                    muliAdapter.addAll(mDatasa);
                                } else {
                                    muliAdapter.onLoadMoreFail();
                                }
                                refesh = !refesh;
                            }
                        }, 1000);
                    }
                })
                .setOnItemClickListener(new IAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ViewGroup parent, View view, int position, long id) {
                        System.out.println("onItemClick position:" + position);
                    }
                }).setOnItemLongClickListener(new IAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, int position, long id) {
                System.out.println("onItemLongClick position:" + position);
                return true;
            }
        }).perform();
        //this is ok,too;
        muliAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_simple, null));
        muliAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.footer_simple, null));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                muliAdapter.add(1, "insert one no ani");
                break;
            case R.id.bt_aniAdd:
                muliAdapter.ani().add(1, "insert one with ani");
                break;
            case R.id.bt_remove:
                muliAdapter.remove(1);
                break;
            case R.id.bt_removeAni:
                muliAdapter.ani().remove(1);
                break;
            case R.id.bt_clear:
                muliAdapter.clear();
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
