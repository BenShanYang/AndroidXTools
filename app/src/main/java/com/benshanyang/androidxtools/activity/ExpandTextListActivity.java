package com.benshanyang.androidxtools.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.benshanyang.androidxtools.R;
import com.benshanyang.androidxtools.base.BaseActivity;
import com.benshanyang.androidxtools.fragment.ExpandTextFragment;
import com.benshanyang.androidxtools.fragment.SimpleExpandTextFragment;
import com.benshanyang.toolslibrary.widget.TitleBarView;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandTextListActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBarView titleBar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_text_list);
        ButterKnife.bind(this);

        initView();
        initListener();
    }

    private void initView() {

        tabLayout.setupWithViewPager(viewPager);
        String[] titles = {"ExpandTextView", "SimpleExpandTextView"};
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                if (position == 0) {
                    fragment = new ExpandTextFragment();
                } else if (position == 1) {
                    fragment = new SimpleExpandTextFragment();
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
    }

    private void initListener() {
        titleBar.setOnFinishListener(new TitleBarView.OnFinishListener() {
            @Override
            public void onFinish(View view) {
                finish();
            }
        });
    }
}