package com.penta.gradienttitlebarart.lib;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.penta.gradienttitlebarart.R;

import java.util.List;

/**
 * 渐变控制类
 */
public class GradientManager {

    private int gradientHeight;//渐变需要滑动的高度值
    private Activity activity;
    private TitleBarView titleBarContainer;
    private int totalRvDy = 0;//recyclerView专用记录滚动距离

    private GradientManager() {
    }

    public static GradientManager ins() {
        return GradientManagerEnum.INSTANCE.getSingleTon();
    }

    public void init(Activity activity, int gradientHeight) {
        this.activity = activity;
        this.gradientHeight = gradientHeight;
    }

    private enum GradientManagerEnum {
        INSTANCE;

        private GradientManager singleTon;

        GradientManagerEnum() {
            singleTon = new GradientManager();
        }

        public GradientManager getSingleTon() {
            return singleTon;
        }
    }

    public void bindViewScolltoGradient(TitleBarView titleBarContainer, GradientScrollView scrollView) {
        this.titleBarContainer = titleBarContainer;
        scrollView.setOnScrollListener(new GradientScrollView.ScrollViewListener() {

            @Override
            public void onScroll(int dy) {
                gradientScroll(dy);
            }
        });
    }

//    //TODO:ListView适配
//    public void bindViewScolltoGradient(TitleBarView titleBarContainer, ListView listView) {
//        this.titleBarContainer = titleBarContainer;
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int i) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//                gradientScroll(dy);
//            }
//        });
//
//    }

    public void bindViewScolltoGradient(TitleBarView titleBarContainer, RecyclerView recyclerView) {
        this.titleBarContainer = titleBarContainer;

        int height = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = activity.getResources().getDimensionPixelSize(resourceId);
        }
        titleBarContainer.setPadding(0, height, 0, 0);
        titleBarContainer.requestLayout();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalRvDy += dy;
                //int horizontalOffset = recyclerView.computeVerticalScrollOffset();
                gradientScroll(totalRvDy);
            }
        });
        titleBarContainer.setBackgroundColor(activity.getResources().getColor(R.color.white));
    }

    public void setBack(ImageBean imageBean) {
        titleBarContainer.getBackView().setTag(imageBean);
        titleBarContainer.getBackView().setOnClickListener(imageBean.getOnClickListener());
    }


    public void addRightImageList(List<ImageBean> imageBeanList, int imagePaddingDp) {
        for (ImageBean imageBean : imageBeanList) {
            ImageView imageView = new ImageView(activity);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(DensityUtil.dip2px(activity, 28 + imagePaddingDp), DensityUtil.dip2px(activity, 28)));
            imageView.setOnClickListener(imageBean.getOnClickListener());
            imageView.setTag(imageBean);
            imageView.setPadding(0, 0, DensityUtil.dip2px(activity, imagePaddingDp), 0);
            imageView.setOnClickListener(imageBean.getOnClickListener());
            titleBarContainer.getRightContainer().addView(imageView);
        }
    }

    private void gradientScroll(int dy) {

        Log.e("ioo", "dy:" + dy + "|gradientHeight:" + gradientHeight);

        if (dy <= gradientHeight / 2) {
            titleAlphaChange(dy, gradientHeight, false);//标题栏渐变
            titleImageChange(true);

        } else if (dy > gradientHeight / 2 && dy <= gradientHeight) {//手指往上滑,距离超过200dp
            titleAlphaChange(dy, gradientHeight, true);//标题栏渐变
            titleImageChange(false);

        } else if (dy > gradientHeight) {
            titleAlphaChange(1, 1, true);//设置不透明百分比为100%，防止因滑动速度过快，导致距离超过200dp,而标题栏透明度却还没变成完全不透的情况。
            titleImageChange(false);
        }
    }

    /**
     * 标题栏透明度变化
     *
     * @param dy
     * @param mHeaderHeight_px
     * @param overHalf         是否超过一半
     */
    private void titleAlphaChange(int dy, float mHeaderHeight_px, boolean overHalf) {
        float percent = (float) Math.abs(dy) / Math.abs(mHeaderHeight_px);
        int alpha = (int) (percent * 255);
        titleBarContainer.getBackground().setAlpha(alpha);

        //标题栏图标渐变 根据是否滑动到一半 设置渐变值
        int viewAlpha = 255 - alpha;
        if (overHalf) {
            viewAlpha = alpha;
        }
        titleBarContainer.getBackView().setAlpha(viewAlpha);
        for (int i = 0; i < titleBarContainer.getRightContainer().getChildCount(); i++) {
            ImageView imageView = (ImageView) titleBarContainer.getRightContainer().getChildAt(i);
            imageView.setAlpha(viewAlpha);
        }
    }

    /**
     * 设置标题栏 图片和状态栏主题
     *
     * @param isDark
     */
    private void titleImageChange(boolean isDark) {

        for (int i = 0; i < titleBarContainer.getRightContainer().getChildCount(); i++) {
            ImageView imageView = (ImageView) titleBarContainer.getRightContainer().getChildAt(i);
            int res;
            if (isDark) {
                res = ((ImageBean) imageView.getTag()).getDarkImageResource();
            } else {
                res = ((ImageBean) imageView.getTag()).getLightImageResource();
            }
            imageView.setImageResource(res);
        }
        if (isDark) {
            titleBarContainer.getBackView().setImageResource(((ImageBean) titleBarContainer.getBackView().getTag()).getDarkImageResource());
            StatusbarUtils.setStatusBarIconLight(activity);
        } else {
            titleBarContainer.getBackView().setImageResource(((ImageBean) titleBarContainer.getBackView().getTag()).getLightImageResource());
            StatusbarUtils.setStatusBarIconDark(activity);
        }
    }


}
