package com.penta.gradienttitlebarart;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.penta.gradienttitlebarart.lib.DensityUtil;
import com.penta.gradienttitlebarart.lib.GradientManager;
import com.penta.gradienttitlebarart.lib.ImageBean;
import com.penta.gradienttitlebarart.lib.StatusbarUtils;
import com.penta.gradienttitlebarart.lib.TitleBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusbarUtils.enableTranslucentStatusbar(this);

        TitleBarView tbv = findViewById(R.id.tbv);
        RecyclerView recyclerView = findViewById(R.id.rv);

        GradientManager.ins().init(this, DensityUtil.dip2px(this, 200));
        GradientManager.ins().bindViewScolltoGradient(tbv, recyclerView);
        GradientManager.ins().setBack(new ImageBean(R.mipmap.back_light_icon, R.mipmap.back_dark_icon, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        }));
        ArrayList<ImageBean> arrayList = new ArrayList();
        arrayList.add(new ImageBean(R.mipmap.share_light_icon, R.mipmap.share_dark_icon, null));
        arrayList.add(new ImageBean(R.mipmap.im_light_icon, R.mipmap.im_dark_icon, null));
        GradientManager.ins().addRightImageList(arrayList,5);
        ArrayList mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }

        FishAdapter adapter = new FishAdapter(this);
        //必须指定adaoter
        recyclerView.setAdapter(adapter);
        //必须指定layoutmanager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setData(mDatas);
    }


}
