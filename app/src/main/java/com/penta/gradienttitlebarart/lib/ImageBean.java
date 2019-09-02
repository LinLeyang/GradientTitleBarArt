package com.penta.gradienttitlebarart.lib;

import android.view.View;

public class ImageBean {

    private int lightImageResource;
    private int darkImageResource;
    private View.OnClickListener onClickListener;


    public ImageBean(int lightImageResource, int darkImageResource, View.OnClickListener onClickListener) {
        this.lightImageResource = lightImageResource;
        this.darkImageResource = darkImageResource;
        this.onClickListener = onClickListener;
    }

    public int getLightImageResource() {
        return lightImageResource;
    }

    public void setLightImageResource(int lightImageResource) {
        this.lightImageResource = lightImageResource;
    }

    public int getDarkImageResource() {
        return darkImageResource;
    }

    public void setDarkImageResource(int darkImageResource) {
        this.darkImageResource = darkImageResource;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
