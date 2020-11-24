package com.benshanyang.androidxtools.activity;

import android.os.Bundle;
import android.view.View;

import com.benshanyang.androidxtools.R;
import com.benshanyang.androidxtools.base.BaseActivity;
import com.benshanyang.toolslibrary.widget.TitleBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditableTextViewActivity extends BaseActivity {

    @BindView(R.id.title_bar_view)
    TitleBarView titleBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_text_view);
        ButterKnife.bind(this);

        titleBarView.setOnFinishListener(new TitleBarView.OnFinishListener() {
            @Override
            public void onFinish(View view) {
                finish();
            }
        });
    }
}