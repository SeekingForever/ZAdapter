package com.zone.zadapter.animal;

import android.support.v7.widget.CardView;

/**
 * Created by sxl on 2016/6/24.
 */
public class ElevationWrapper {
    private CardView card;
//    private float elevation;//这样也可以 主要是 set get
    public ElevationWrapper(CardView card) {
        this.card = card;
    }

    public float getElevation() {
//        return card.getCardElevation();
        return card.getElevation();
    }

    public void setElevation(float elevation) {
//        this.elevation = elevation;
//        card.setCardElevation(elevation);
        card.setElevation(elevation);
    }

}
