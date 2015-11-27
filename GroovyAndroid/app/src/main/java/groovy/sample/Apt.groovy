package groovy.sample

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import groovy.transform.CompileStatic

/**
 * Created by SidneyXu on 2015/11/27.
 */
@CompileStatic
class Apt extends ArrayAdapter<String> {

    Apt(final Context context, final List<String> data) {
        super(context, android.R.layout.simple_list_item_1, android.R.id.text1, data)
    }

    @Override
    String getItem(final int position) {
        return super.getItem(position)
    }

    @Override
    int getCount() {
        return super.getCount()
    }

    @Override
    View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = super.getView(position, convertView, parent)
        def title = view.findViewById(android.R.id.text1) as TextView
        title.setText(getItem(position))
        view
    }
}