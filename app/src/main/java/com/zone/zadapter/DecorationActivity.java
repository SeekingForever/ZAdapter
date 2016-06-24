package com.zone.zadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.zone.adapter.QuickRcvAdapter;
import com.zone.adapter.callback.Helper;
import com.zone.zadapter.animal.ElevationWrapper;
import com.zone.zadapter.decoration.GridSpaceDectoration;
import com.zone.zadapter.uitls.Images;
import com.zone.zadapter.uitls.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(new QuickRcvAdapter<String>(this, Arrays.asList(Images.imageThumbUrls)) {
                    List<Boolean>  list=new ArrayList<>(Images.imageThumbUrls.length);
                    @Override
                    public void fillData(final Helper<String> helper, String item, boolean itemChanged, int layoutId) {
//                        if (list.size()==0) {
//                            for (String imageThumbUrl : Images.imageThumbUrls)
//                                list.add(false);
//                        }
//                        if (!list.get(helper.getPosition())) {
//                            list.set(helper.getPosition(),true);
                            runEnterAnimation(helper.getView(),getContext());
//                        }
                        helper.setImageUrl(R.id.civ,item);
                        helper.setOnClickListener(R.id.civ, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CardView card=helper.getView(R.id.card);
                                System.out.println("postion:"+helper.getPosition()+"\t  width:"+helper.getView().getMeasuredWidth()+"\t  height:"+helper.getView().getMeasuredHeight());
                                runAni(getContext(),card);
                            }
                        });
            }

            @Override
            public int getItemLayoutId(String s, int position) {
                return R.layout.item_rec_card;
            }
        });
    }
    public void runAni(Context context, CardView card){
        float clickElevation =context.getResources().getDimension(R.dimen.Elevation);
        ElevationWrapper elevationWrapper = new ElevationWrapper(card);

        ObjectAnimator ani = ObjectAnimator.ofFloat(elevationWrapper, "elevation", clickElevation, clickElevation / 4,clickElevation);
//        ani.setDuration(600);
//        ani.start();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(card, "scaleX", 1F, 1.2F,1F);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(card, "scaleY", 1F, 1.2F,1F);
//        ObjectAnimator aniReverse = ObjectAnimator.ofFloat(elevationWrapper, "elevation",  clickElevation / 4,clickElevation);
//        aniReverse.setDuration(300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ani,
                scaleX,
                scaleY
        );
        animatorSet.setDuration(600);
        animatorSet.start();
    }
    private void runEnterAnimation(View view,Context context){
//        translationX 、translationY:
//        作为一种增量控制view对象从他布局容器的左上角坐标开始的位置；

//        view.setTranslationY(Utils.getScreenHeight(context));
//        view.animate().translationY(0)
//                .setInterpolator(new DecelerateInterpolator(3.f))
//                .setDuration(1500).start();

        view.setTranslationX(Utils.getScreenHeight(context));
        view.animate().translationX(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(1500)
                .start();
    }

}
