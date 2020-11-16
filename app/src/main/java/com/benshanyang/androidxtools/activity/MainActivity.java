package com.benshanyang.androidxtools.activity;

import android.os.Bundle;

import com.benshanyang.androidxtools.R;
import com.benshanyang.androidxtools.base.BaseActivity;
import com.benshanyang.toolslibrary.base.BaseParentActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QMUIStatusBarHelper.setStatusBarLightMode(activity);
    }

}