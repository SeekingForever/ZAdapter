package com.zone.zadapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import com.zone.adapter.QuickAdapter;
import com.zone.adapter.QuickManager;
import com.zone.adapter.callback.Helper;
import com.zone.adapter.callback.IAdapter;
import com.zone.adapter.loadmore.callback.OnLoadMoreListener;
import com.zone.zadapter.uitls.ToastUtils;

public class ListViewActivity extends Activity implements Handler.Callback {
    private ListView listView1;
    private int positonId = -1;
    private AlertDialog alert;
    private IAdapter<String> adapter2;
    private List<String> mDatas=new ArrayList<String>();
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handler = new Handler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_menu);

        createDialog();
        listView1 = (ListView) findViewById(R.id.listView1);
        for (int i = 'A'; i <='K'; i++) {
            mDatas.add("" + (char) i);
        }
        final int[] colorArry = {Color.WHITE, Color.GREEN, Color.YELLOW, Color.CYAN};
        adapter2 = new QuickAdapter<String>(this, mDatas) {
            @Override
            public void fillData(final Helper<String> helper, final String item, boolean itemChanged, int layoutId) {
                helper.setText(R.id.tv, item).setBackgroundColor(R.id.tv, colorArry[helper.getPosition() % colorArry.length])
                        .setOnClickListener(R.id.tv, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.out.println("position:" + helper.getPosition());
                                ToastUtils.showShort(ListViewActivity.this, "position:"+ helper.getPosition());
                            }
                        })
                        .setOnLongClickListener(R.id.tv, new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                positonId = helper.getPosition();
                                alert.show();
                                return false;
                            }
                        });
            }

            @Override
            public int getItemLayoutId(String data, int position) {
                return R.layout.item_menu;
            }

        };
        QuickManager.with(adapter2, listView1)
                .addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_simple, null))
                .addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_simple, null))
                .addFooterView(LayoutInflater.from(this).inflate(R.layout.footer_simple, null))
                .setOnLoadMoreListener(new OnLoadMoreListener() {
                    boolean refesh = true;

                    @Override
                    public void onLoadMore() {
                        final List<String> mDatasa = new ArrayList<>();
                        for (int i = 0; i < 5; i++) {
                            mDatasa.add("insert " + i);
                        }
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (refesh) {
                                    adapter2.onLoadMoreComplete();
                                    adapter2.addAll(mDatasa);
                                } else {
                                    adapter2.onLoadMoreFail();
                                }
                                refesh = !refesh;
                            }
                        }, 1000);
                    }
                }).perform();
    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to remove?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (positonId != -1) {
                            mDatas.remove(positonId);
                            positonId = -1;
                            adapter2.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
    }


    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
