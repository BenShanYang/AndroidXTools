package com.benshanyang.androidxtools.activity;

import android.os.Bundle;
import android.view.View;

import com.benshanyang.androidxtools.R;
import com.benshanyang.androidxtools.base.BaseActivity;
import com.benshanyang.toolslibrary.widget.ExpandTextView;
import com.benshanyang.toolslibrary.widget.TitleBarView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBarView titleBar;//标题栏

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        QMUIStatusBarHelper.setStatusBarLightMode(activity);

        initView();
        initListener();
    }

    private void initView() {

    }

    private void initListener() {
        titleBar.setOnActionButtonClickListener(new TitleBarView.OnActionButtonClickListener() {
            @Override
            public void onClick(View view) {
                showToast("菜单");
            }
        });
    }

    @OnClick({R.id.expand_text_view, R.id.simple_expand_text_view, R.id.expand_text_list, R.id.round_imageview, R.id.title_bar_view, R.id.password_edit_text, R.id.clear_edit_text, R.id.simple_clear_edit_text, R.id.border_text_view, R.id.clickable_text_view, R.id.editable_text_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.expand_text_view:
                toActivity(ExpandTextActivity.class);
                break;
            case R.id.simple_expand_text_view:
                toActivity(SimpleExpandTextActivity.class);
                break;
            case R.id.expand_text_list:
                toActivity(ExpandTextListActivity.class);
                break;
            case R.id.round_imageview:
                toActivity(RoundedImageViewActivity.class);
                break;
            case R.id.title_bar_view:
                toActivity(TitleBarViewActivity.class);
                break;
            case R.id.password_edit_text:
                toActivity(PasswordEditTextActivity.class);
                break;
            case R.id.clear_edit_text:
                toActivity(ClearEditTextActivity.class);
                break;
            case R.id.simple_clear_edit_text:
                toActivity(SimpleClearEditTextActivity.class);
                break;
            case R.id.border_text_view:
                toActivity(BorderTextViewActivity.class);
                break;
            case R.id.clickable_text_view:
                toActivity(ClickableTextViewActivity.class);
                break;
            case R.id.editable_text_view:
                toActivity(EditableTextViewActivity.class);
                break;
        }
    }
}