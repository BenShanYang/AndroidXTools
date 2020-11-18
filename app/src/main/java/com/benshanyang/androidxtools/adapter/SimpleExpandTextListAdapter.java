package com.benshanyang.androidxtools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.benshanyang.androidxtools.R;
import com.benshanyang.toolslibrary.widget.SimpleExpandTextView;

/**
 * @ClassName: ExpandTextListAdapter
 * @Description: 可折叠文字控件列表适配器
 * @Author: YangKuan
 * @Date: 2020/11/16 15:26
 */
public class SimpleExpandTextListAdapter extends BaseAdapter {

    private Context context;

    public SimpleExpandTextListAdapter(Context context) {
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
        SimpleExpandViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_simple_expand_text, parent, false);
            viewHolder = new SimpleExpandViewHolder();
            viewHolder.expandTextView = convertView.findViewById(R.id.simple_expand_text_view);
            viewHolder.tvLookMore = convertView.findViewById(R.id.tv_look_more);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SimpleExpandViewHolder) convertView.getTag();
        }

        viewHolder.expandTextView.setText(position +context.getString(R.string.expand_text));
        viewHolder.expandTextView.addTextTooLong(new SimpleExpandTextView.TextTooLong() {
            @Override
            public void tooLong(boolean tooLong) {
                viewHolder.tvLookMore.setVisibility(tooLong ? View.VISIBLE : View.GONE);
            }
        });

        viewHolder.expandTextView.addExpandStateListener(new SimpleExpandTextView.ExpandStateListener() {
            @Override
            public void expandText(boolean expand) {
                viewHolder.tvLookMore.setText(expand ? "收起" : "展开");
            }
        });

        viewHolder.tvLookMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.expandTextView.isExpand()) {
                    viewHolder.expandTextView.closeText();
                } else {
                    viewHolder.expandTextView.expandText();
                }
            }
        });
        return convertView;
    }

    class SimpleExpandViewHolder {

        SimpleExpandTextView expandTextView;
        TextView tvLookMore;//查看更多

    }
}
