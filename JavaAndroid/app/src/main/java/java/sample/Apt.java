package java.sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SidneyXu on 2016/01/19.
 */
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
        View view = super.getView(position, convertView, parent);
        TextView title = (TextView) view.findViewById(android.R.id.text1);
        title.setText(getItem(position));
        return view;
    }
}
