package com.benshanyang.androidxtools.activity;

import android.os.Bundle;
import android.view.View;

import com.benshanyang.androidxtools.R;
import com.benshanyang.androidxtools.base.BaseActivity;
import com.benshanyang.toolslibrary.widget.SimpleClearEditText;
import com.benshanyang.toolslibrary.widget.TitleBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleClearEditTextActivity extends BaseActivity {

    @BindView(R.id.title_bar_view)
    TitleBarView titleBarView;
    @BindView(R.id.simple_clear_edittext)
    SimpleClearEditText simpleClearEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_clear_edit_text);
        ButterKnife.bind(this);

        titleBarView.setOnFinishListener(new TitleBarView.OnFinishListener() {
            @Override
            public void onFinish(View view) {
                finish();
            }
        });

        simpleClearEdittext.setBorderColor(0xFF0000FF);
    }
}