package kotlin.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import java.util.NoSuchElementException

/**
 * Created by mrseasons on 2015/08/04.
 */
public class ListActivity : AppCompatActivity() {

    public val ViewGroup.views: List<View>
        get() = asSequence().toList()

    public val ViewGroup.viewsRecursive: List<View>
        get() = views flatMap { it ->
            when (it) {
                is ViewGroup -> it.viewsRecursive
                else -> listOf(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val container: ViewGroup = findViewById(R.id.container) as ViewGroup
        val views = container.viewsRecursive

        // Set Kotlin TextView to Upper
        val kotlinText = views.first { it ->
            it is TextView && it.getText().toString().contains("Kotlin")
        } as TextView
        kotlinText.setText(kotlinText.getText().toString().toUpperCase())

        // Set even checkboxes as checked, and odd as unchecked
        views filter {
            it is CheckBox
        } forEach {
            with(it as CheckBox) {
                val number = getText().toString().removePrefix("Check ").toInt()
                setChecked(number % 2 == 0)
            }
        }

    }

    fun ViewGroup.asSequence(): Sequence<View> = object : Sequence<View> {

        override fun iterator(): Iterator<View> = object : Iterator<View> {
            private var nextValue: View? = null
            private var done = false
            private var position: Int = 0

            override public fun hasNext(): Boolean {
                if (nextValue == null && !done) {
                    nextValue = getChildAt(position)
                    position++
                    if (nextValue == null) done = true
                }
                return nextValue != null
            }

            override fun next(): View {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }
                val answer = nextValue
                nextValue = null
                return answer!!
            }
        }
    }
}