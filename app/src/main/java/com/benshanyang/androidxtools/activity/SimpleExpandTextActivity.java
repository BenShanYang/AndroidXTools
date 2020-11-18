package com.benshanyang.androidxtools.activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;

import com.benshanyang.androidxtools.R;
import com.benshanyang.androidxtools.base.BaseActivity;
import com.benshanyang.toolslibrary.callback.TextWatchListener;
import com.benshanyang.toolslibrary.widget.SimpleExpandTextView;
import com.benshanyang.toolslibrary.widget.TitleBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: SimpleExpandTextActivity
 * @Description: 可折叠的文字控件
 * @Author: YangKuan
 * @Date: 2020/11/16 14:57
 */
public class SimpleExpandTextActivity extends BaseActivity {

    @BindView(R.id.simple_expand_text_view)
    SimpleExpandTextView simpleExpandTextView;
    @BindView(R.id.expand_button)
    AppCompatButton expandButton;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.title_bar)
    TitleBarView titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_expand_text);
        ButterKnife.bind(this);

        initView();
        initListener();
    }

    private void initView() {
        simpleExpandTextView.setText(getString(R.string.expand_text));
    }

    private void initListener() {
        simpleExpandTextView.addTextTooLong(new SimpleExpandTextView.TextTooLong() {
            @Override
            public void tooLong(boolean tooLong) {
                expandButton.setVisibility(tooLong ? View.VISIBLE : View.GONE);
            }
        });

        simpleExpandTextView.addExpandStateListener(new SimpleExpandTextView.ExpandStateListener() {
            @Override
            public void expandText(boolean expand) {
                expandButton.setText(expand ? "收起" : "展开");
            }
        });

        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleExpandTextView.isExpand()) {
                    simpleExpandTextView.closeText();
                } else {
                    simpleExpandTextView.expandText();
                }
            }
        });

        editText.addTextChangedListener(new TextWatchListener() {
            @Override
            public void afterTextChanged(Editable s) {
                simpleExpandTextView.setText(s.toString());
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