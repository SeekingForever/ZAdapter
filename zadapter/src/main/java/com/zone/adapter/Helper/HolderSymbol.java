package com.zone.adapter.Helper;

import android.graphics.drawable.Drawable;

/**
 * Created by sxl on 2016/6/21.
 */
public class HolderSymbol {
    private Drawable placeholderDrawable;
    private int placeholderRes = -1;
    private Select placeSelect, errorSelect = Select.None;
    private Drawable errorDrawable;
    private int errorRes = -1;

    public HolderSymbol setPlaceholder(Drawable placeholderDrawable) {
        placeSelect = Select.Draw;
        this.placeholderDrawable = placeholderDrawable;
        return this;
    }

    public HolderSymbol setPlaceholder(int placeholderRes) {
        placeSelect = Select.Res;
        this.placeholderRes = placeholderRes;
        return this;
    }

    public Drawable getPlaceholderDrawable() {
        return placeholderDrawable;
    }

    public int getPlaceholderRes() {
        return placeholderRes;
    }

    public HolderSymbol error(int errorRes) {
        errorSelect = Select.Res;
        this.errorRes = errorRes;
        return this;
    }

    public HolderSymbol error(Drawable errorDrawable) {
        errorSelect = Select.Draw;
        this.errorDrawable = errorDrawable;
        return this;
    }

    public Drawable getErrorDrawable() {
        return errorDrawable;
    }

    public int getErrorRes() {
        return errorRes;
    }

    public Select getPlaceSelect() {
        return placeSelect;
    }

    public Select getErrorSelect() {
        return errorSelect;
    }

    public enum Select {
        None, Res, Draw;
    }
}
