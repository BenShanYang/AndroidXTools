package com.benshanyang.androidxtools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.benshanyang.androidxtools.R;
import com.benshanyang.toolslibrary.widget.ExpandTextView;

/**
 * @ClassName: ExpandTextListAdapter
 * @Description: 可折叠文字控件列表适配器
 * @Author: YangKuan
 * @Date: 2020/11/16 15:26
 */
public class ExpandTextListAdapter extends BaseAdapter {

    private Context context;

    public ExpandTextListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExpandViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_expand_text, parent, false);
            viewHolder = new ExpandViewHolder();
            viewHolder.expandTextView = convertView.findViewById(R.id.expand_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ExpandViewHolder) convertView.getTag();
        }

        viewHolder.expandTextView.setText(position + context.getString(R.string.expand_text));
        return convertView;
    }

    class ExpandViewHolder {
        ExpandTextView expandTextView;
    }

}
