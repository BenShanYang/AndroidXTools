package com.benshanyang.androidxtools.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.benshanyang.androidxtools.R;
import com.benshanyang.androidxtools.adapter.SimpleExpandTextListAdapter;
import com.benshanyang.toolslibrary.base.BaseParentFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleExpandTextFragment extends BaseParentFragment {

    @BindView(R.id.list_view)
    ListView listView;

    private SimpleExpandTextListAdapter listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_expand_text, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listView.setAdapter(listAdapter = new SimpleExpandTextListAdapter(context));
    }

    @Override
    public void setListener(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}