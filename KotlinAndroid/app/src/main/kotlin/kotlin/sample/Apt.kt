package kotlin.sample

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * Created by SidneyXu on 2015/11/30.
 */
public class Apt(val ctx: Context, var data: List<String>) : ArrayAdapter<String>(
        ctx,
        android.R.layout.simple_list_item_1,
        android.R.id.text1,
        data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = super.getView(position, convertView, parent)
        val title = view.findViewById(android.R.id.text1) as TextView
        title.text = getItem(position)
        return view
    }
}