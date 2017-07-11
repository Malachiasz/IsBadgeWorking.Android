package me.leolin.isbadgeworking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import me.leolin.shortcutbadger.ShortcutBadger
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        find<Button>(R.id.btnSetBadge).setOnClickListener {
            val i = Random().nextInt(98) + 1
            toast("Tried to set badge count to ${i}")
            ShortcutBadger.applyCount(this@MainActivity, i)
        }
    }


    data class DisplayData(
            val label: String,
            val value: String
    )

    inner class ListAdapter(val displayDataList: List<DisplayData>, val layoutInflater: LayoutInflater) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val displayData = displayDataList[position];
            return convertView ?: layoutInflater.inflate(R.layout.listitem, null).apply {
                this.find<TextView>(R.id.textViewLabel)?.apply { text = displayData.label }
                this.find<TextView>(R.id.textViewValue)?.apply { text = displayData.value }
            }
        }

        override fun getItem(position: Int) = displayDataList[position]

        override fun getItemId(position: Int) = position.toLong()

        override fun getCount() = displayDataList.size
    }

}
