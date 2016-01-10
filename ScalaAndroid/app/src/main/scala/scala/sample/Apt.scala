package scala.sample

import android.content.Context
import android.view.{View, ViewGroup}
import android.widget.{ArrayAdapter, TextView}

import scala.collection.mutable.ArrayBuffer

class Apt(val ctx: Context, var data: ArrayBuffer[String]) extends ArrayAdapter[String](ctx,
    android.R.layout.simple_list_item_1,
    android.R.id.text1) {

    override def getItem(position: Int): String = data(position)

    override def getCount: Int = data.length

    override def getView(position: Int, convertView: View, parent: ViewGroup): View = {
        val view: View = super.getView(position, convertView, parent)
        val title = view.findViewById(android.R.id.text1).asInstanceOf[TextView]
        title.setText(getItem(position))
        view
    }
}
