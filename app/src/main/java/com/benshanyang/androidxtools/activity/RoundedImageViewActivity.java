package com.benshanyang.androidxtools.activity;

import android.os.Bundle;
import android.view.View;

import com.benshanyang.androidxtools.R;
import com.benshanyang.androidxtools.base.BaseActivity;
import com.benshanyang.toolslibrary.widget.RoundImageView;
import com.benshanyang.toolslibrary.widget.TitleBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoundedImageViewActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBarView titleBar;
    @BindView(R.id.image_1)
    RoundImageView image1;
    @BindView(R.id.image_2)
    RoundImageView image2;
    @BindView(R.id.image_3)
    RoundImageView image3;
    @BindView(R.id.image_4)
    RoundImageView image4;
    @BindView(R.id.image_5)
    RoundImageView image5;
    @BindView(R.id.image_6)
    RoundImageView image6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_image_view);
        ButterKnife.bind(this);

        titleBar.setOnFinishListener(new TitleBarView.OnFinishListener() {
            @Override
            public void onFinish(View view) {
                finish();
            }
        });
        image5.setSelected(true);

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //image6.setSelected(true);
            }
        });
    }
}