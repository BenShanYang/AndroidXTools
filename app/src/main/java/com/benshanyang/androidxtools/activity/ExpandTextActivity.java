package com.benshanyang.androidxtools.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.benshanyang.androidxtools.R;
import com.benshanyang.androidxtools.base.BaseActivity;
import com.benshanyang.toolslibrary.callback.TextWatchListener;
import com.benshanyang.toolslibrary.widget.ExpandTextView;
import com.benshanyang.toolslibrary.widget.TitleBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: ExpandTextActivity
 * @Description: 可折叠的文字控件
 * @Author: YangKuan
 * @Date: 2020/11/16 14:57
 */
public class ExpandTextActivity extends BaseActivity {

    @BindView(R.id.expand_text_view)
    ExpandTextView expandTextView;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.tv_iconfont)
    TextView tvIconfont;
    @BindView(R.id.title_bar)
    TitleBarView titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_text);
        ButterKnife.bind(this);

        initView();
        initListener();
    }

    private void initView() {

        expandTextView.setText(getString(R.string.expand_text));

        tvIconfont.setTypeface(Typeface.createFromAsset(getAssets(), "expand_icon_font/ic_expand_iconfont.ttf"));
        tvIconfont.setText(
                "\n\\ue600 \ue600; " + "\\uebc0 \uebc0; " + "\\uebc1 \uebc1; " + "\\uebc2 \uebc2 \n\n" + "\\uebbe \uebbe; " + "\\uebbf \uebbf; " + "\\uebbd \uebbd; " + "\\uebbc \uebbc \n"
        );
    }

    private void initListener() {
        editText.addTextChangedListener(new TextWatchListener() {
            @Override
            public void afterTextChanged(Editable s) {
                expandTextView.setText(s);
            }
        });

        titleBar.setOnFinishListener(new TitleBarView.OnFinishListener() {
            @Override
            public void onFinish(View view) {
                finish();
            }
        });
    }


}