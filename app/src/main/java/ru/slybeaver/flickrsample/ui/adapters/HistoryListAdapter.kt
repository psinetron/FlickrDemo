package ru.slybeaver.flickrsample.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.slybeaver.flickrsample.R

import ru.slybeaver.flickrsample.dto.SearchHistoryDTO

/**
 * Created by psinetron on 30.05.2018.
 * http://slybeaver.ru
 */
class HistoryListAdapter(private var items: ArrayList<SearchHistoryDTO>, val clickListener: (String) -> Unit)
    :RecyclerView.Adapter<HistoryListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.template_list_history, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.txtHistory?.text = item.searchText
        holder.historyLine?.setOnClickListener { item.searchText?.let {clickListener(it)} }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var historyLine:View? = itemView.findViewById(R.id.history_line)
        var txtHistory:TextView? = itemView.findViewById(R.id.txtHistory)
    }
}