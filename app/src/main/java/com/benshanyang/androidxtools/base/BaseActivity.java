package com.benshanyang.androidxtools.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.benshanyang.toolslibrary.base.BaseParentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * @ClassName: BaseActivity
 * @Description: java类作用描述
 * @Author: mayn
 * @Date: 2020/11/13 17:18
 */
public class BaseActivity extends BaseParentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(activity);
    }
}
