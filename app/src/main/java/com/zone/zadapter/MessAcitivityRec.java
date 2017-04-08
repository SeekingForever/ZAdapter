package com.zone.zadapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zone.adapter.QuickRcvAdapter;
import com.zone.adapter.callback.Helper;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessAcitivityRec extends AppCompatActivity {

    @Bind(R.id.rv)
    RecyclerView rv;

    @Bind(R.id.sendEt)
    EditText sendEt;
    @Bind(R.id.send)
    Button send;
    @Bind(R.id.ll)
    LinearLayout ll;
    private Message message;
    private QuickRcvAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_message_rec);
        ButterKnife.bind(this);
        message = new Message();
        initData();
        setListener();
    }

    public void initData() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter = new QuickRcvAdapter<String>(this, message.getContents()) {
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
