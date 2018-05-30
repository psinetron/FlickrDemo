package ru.slybeaver.flickrsample.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import ru.slybeaver.flickrsample.R
import ru.slybeaver.flickrsample.dto.SearchListDTO

/**
 * Created by psinetron on 29.05.2018.
 * http://slybeaver.ru
 */
class ImagesGridAdapter(private var items: ArrayList<SearchListDTO.PhotoDetail>, val clickListener: (String) -> Unit)
    : RecyclerView.Adapter<ImagesGridAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.template_grid_image, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clearList() {
        items.clear()
    }

    fun updateList(newItems: ArrayList<SearchListDTO.PhotoDetail>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Picasso.with(holder.smallImage?.context)
                .load("https://farm${item.farm}.staticflickr.com/${item.server}/${item.id}_${item.secret}_q.jpg")
                .into(holder.smallImage)
        holder.smallImage?.setOnClickListener {
            clickListener(
                    "https://farm${item.farm}.staticflickr.com/${item.server}/${item.id}_${item.secret}_b.jpg")
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var smallImage:ImageView? = itemView.findViewById(R.id.smallImage)
    }
}