package com.zone.zadapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ScrollView;

import com.zone.adapter.QuickAdapter;
import com.zone.adapter.callback.Helper;
import com.zone.zadapter.fully.FullyGridView;
import com.zone.zadapter.fully.FullyListView;
import com.zone.zadapter.uitls.Images;

import java.util.Arrays;

/**
 * Created by sxl on 2016/6/23.
 */
public class ScrollerGridActivity extends Activity {
    private FullyGridView rv;
    private ScrollView sl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_scorllergrid);

        sl=(ScrollView) findViewById(R.id.sl);
//		gridView1.setFocusable(false);//这个也 可以解决ScrollView起始位置不是最顶部的解决办法
        sl.smoothScrollTo(0,0);//这个也 可以解决ScrollView起始位置不是最顶部的解决办法

        rv = (FullyGridView) findViewById(R.id.gridView1);
        rv.setAdapter(new QuickAdapter<String>(this, Arrays.asList(Images.imageThumbUrls)) {
            @Override
            public void fillData(final Helper<String> helper, String item, boolean itemChanged, int layoutId) {
                helper.setImageUrl(R.id.civ, item);
                helper.setOnClickListener(R.id.civ, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CardView card = helper.getView(R.id.card);
                        card.setElevation(getContext().getResources().getDimension(R.dimen.clickElevation));
                    }
                });
            }

            @Override
            public int getItemLayoutId(String s, int position) {
                return R.layout.item_rec_card;
            }
        });
    }
}