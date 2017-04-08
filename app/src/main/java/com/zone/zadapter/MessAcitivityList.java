package com.zone.zadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zone.adapter.QuickAdapter;
import com.zone.adapter.callback.Helper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessAcitivityList extends AppCompatActivity {

    @Bind(R.id.rv)
    ListView rv;

    @Bind(R.id.sendEt)
    EditText sendEt;
    @Bind(R.id.send)
    Button send;
    @Bind(R.id.ll)
    LinearLayout ll;
    private Message message;
    private QuickAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_message);
        ButterKnife.bind(this);
        message = new Message();
        initData();
        setListener();
    }

    public void initData() {
        rv.setAdapter(adapter = new QuickAdapter<String>(this, message.getContents(), 2) {
            @Override
            public void fillData(Helper<String> helper, String s, boolean b, int i) {
                helper.setText(R.id.tv, s);
            }

            @Override
            public int getItemLayoutId(String s, int i) {
                if (i % 2 == 0)
                    return R.layout.item_left;
                return R.layout.item_right;
            }


        });
        adapter.relatedList(rv);
        adapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_simple, null));
        adapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.header_simple, null));
    }

    public void setListener() {
    }

    @OnClick(R.id.send)
    public void onClick() {
    }

}
