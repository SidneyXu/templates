package com.bookislife.android.sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Apt extends ArrayAdapter<String> {
    public Apt(final Context context, List<String> data) {
        super(context, android.R.layout.simple_list_item_1, android.R.id.text1, data);
    }

    @Override
    public String getItem(final int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        Holder holder;
        View view;
        if (null == convertView) {
            holder = new Holder();
            view = super.getView(position, convertView, parent);
            holder.title = (TextView) view.findViewById(android.R.id.text1);
            view.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
            view = convertView;
        }
        holder.title.setText(getItem(position));
        return view;
    }

    private class Holder {
        public TextView title;
    }
}
