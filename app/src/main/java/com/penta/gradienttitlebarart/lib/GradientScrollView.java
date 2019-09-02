package com.penta.gradienttitlebarart.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class GradientScrollView extends ScrollView {

    private ScrollViewListener scrollViewListener = null;

    public GradientScrollView(Context context) {
        super(context);
    }

    public GradientScrollView(Context context, AttributeSet attrs,
                              int defStyle) {
        super(context, attrs, defStyle);
    }

    public GradientScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnScrollListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScroll(y);
        }
    }

    public interface ScrollViewListener {//dy Y轴滑动距离，isUp 是否返回顶部
        void onScroll(int dy);
    }
}