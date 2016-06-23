package com.zone.zadapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zone.adapter.QuickRcvAdapter;
import com.zone.adapter.callback.Helper;
import com.zone.zadapter.decoration.GridSpaceDectoration;
import com.zone.zadapter.uitls.Images;

import java.util.Arrays;

/**
 * Created by sxl on 2016/6/23.
 */
public class DecorationActivity extends Activity {
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_animal);
        rv = (RecyclerView) findViewById(R.id.rv);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        rv.addItemDecoration(new GridSpaceDectoration(spacingInPixels));
        rv.setLayoutManager(new GridLayoutManager(this,3));
        rv.setAdapter(new QuickRcvAdapter<String>(this, Arrays.asList(Images.imageThumbUrls)) {
                    @Override
                    public void fillData(final Helper<String> helper, String item, boolean itemChanged, int layoutId) {
                        helper.setImageUrl(R.id.civ,item);
                        helper.setOnClickListener(R.id.civ, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CardView card=helper.getView(R.id.card);
                                System.out.println("postion:"+helper.getPosition()+"\t  width:"+helper.getView().getMeasuredWidth()+"\t  height:"+helper.getView().getMeasuredHeight());
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
