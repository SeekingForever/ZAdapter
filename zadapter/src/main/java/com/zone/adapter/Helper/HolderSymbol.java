package com.zone.adapter.Helper;

import android.graphics.drawable.Drawable;

/**
 * Created by sxl on 2016/6/21.
 */
public class HolderSymbol  {
    private Drawable placeholderDrawable;
    private int placeholderRes=-1;
    private Select placeSelect,errorSelect=Select.None;
    private Drawable errorDrawable;
    private int errorRes=-1;
    public Drawable getPlaceholderDrawable() {
        return placeholderDrawable;
    }

    public void setPlaceholderDrawable(Drawable placeholderDrawable) {
        placeSelect=Select.Draw;
        this.placeholderDrawable = placeholderDrawable;
    }

    public int getPlaceholderRes() {
        return placeholderRes;
    }

    public void setPlaceholderRes(int placeholderRes) {
        placeSelect=Select.Res;
        this.placeholderRes = placeholderRes;
    }

    public Drawable getErrorDrawable() {
        return errorDrawable;
    }

    public void setErrorDrawable(Drawable errorDrawable) {
        errorSelect=Select.Draw;
        this.errorDrawable = errorDrawable;
    }

    public int getErrorRes() {
        return errorRes;
    }

    public void setErrorRes(int errorRes) {
        errorSelect=Select.Res;
        this.errorRes = errorRes;
    }

    public Select getPlaceSelect() {
        return placeSelect;
    }

    public Select getErrorSelect() {
        return errorSelect;
    }

    public enum Select{
        None, Res,Draw;
    }
}
