package me.blackdroid.nab.ui.main

import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import me.blackdroid.nab.R
import me.blackdroid.nab.base.BaseAdapter
import me.blackdroid.nab.ext.toDateDisplay
import me.blackdroid.nab.model.Daily

class DailyAdapter(dailyList: List<Daily>) : BaseAdapter<Daily>(
    layoutId = R.layout.item_daily,
    dataList = dailyList
) {
    override fun onBindViewHolder(holder: ViewHolder<Daily>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.root.findViewById<TextView>(R.id.tvDate).text =
            "Date: ${dataset[position].dt?.toDateDisplay() ?: ""} "
    }
}
