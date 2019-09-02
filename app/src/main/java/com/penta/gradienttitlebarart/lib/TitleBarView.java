package com.penta.gradienttitlebarart.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.penta.gradienttitlebarart.R;

public class TitleBarView extends FrameLayout {
    private Context context;
    private ImageView ivBack;
    private LinearLayout llRight;

    public TitleBarView(@NonNull Context context) {
        super(context);
        init(context);
    }


    public TitleBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this, true);
        ivBack = findViewById(R.id.iv_back);
        llRight = findViewById(R.id.ll_right);
    }

    public ImageView getBackView() {
        return ivBack;
    }

    public LinearLayout getRightContainer() {
        return llRight;
    }

}
