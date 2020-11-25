package com.benshanyang.androidxtools.activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.benshanyang.androidxtools.R;
import com.benshanyang.androidxtools.base.BaseActivity;
import com.benshanyang.toolslibrary.utils.KeyBoardUtils;
import com.benshanyang.toolslibrary.utils.TextUtils;
import com.benshanyang.toolslibrary.widget.SearchBarView;
import com.benshanyang.toolslibrary.widget.TitleBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchBarViewActivity extends BaseActivity {

    @BindView(R.id.title_bar_view)
    TitleBarView titleBarView;
    @BindView(R.id.search_bar_view_1)
    SearchBarView searchBarView1;
    @BindView(R.id.search_bar_view_2)
    SearchBarView searchBarView2;
    @BindView(R.id.search_bar_view_3)
    SearchBarView searchBarView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar_view);
        ButterKnife.bind(this);

        titleBarView.setOnFinishListener(new TitleBarView.OnFinishListener() {
            @Override
            public void onFinish(View view) {
                finish();
            }
        });

        View.OnClickListener o = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
        searchBarView1.setOnTextChangedListener(new SearchBarView.OnTextChangedListener() {
            @Override
            public void onChanged(Editable s, final EditText editText, ImageButton imageButton) {
                imageButton.setImageResource(TextUtils.isEmpty(s) ? R.drawable.ic_camera : R.drawable.ic_clear);
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(searchBarView1.getText())) {
                    showToast("拍照");
                } else {
                    searchBarView1.setText("");
                }
            }
        });

        searchBarView2.setOnActionButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("拍照");
            }
        });

        searchBarView3.setOnTextChangedListener(new SearchBarView.OnTextChangedListener() {
            @Override
            public void onChanged(Editable s, EditText editText, ImageButton imageButton) {
                imageButton.setVisibility(TextUtils.isEmpty(s) ? View.GONE : View.VISIBLE);
            }
        });
        searchBarView3.setOnActionButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(searchBarView3.getText())) {
                    searchBarView3.setActionIconVisibility(View.GONE);
                    searchBarView3.setText("");
                    searchBarView3.getEditText().setFocusable(true);
                    searchBarView3.getEditText().setFocusableInTouchMode(true);
                    searchBarView3.getEditText().requestFocus();
                    KeyBoardUtils.showKeyboard(searchBarView3.getEditText());
                }
            }
        });
    }
}