package co.uk.happyapper.lushspacex.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.uk.happyapper.lushspacex.R

import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rocket_line.view.*

class RocketItemAdapter(
    private val items: List<MainViewModel.RocketUIModel>,
    private val context: Context
) :
    RecyclerView.Adapter<RocketItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.title
        val success: TextView = view.launch_success
        val date: TextView = view.launch_date
        val image: ImageView = view.rocket_image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rocket_line, parent, false)
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = items[position].name
        Glide.with(context).load(items[position].badgeUrl).into(holder.image)
        holder.success.text = items[position].success
        holder.date.text = items[position].launchDate
    }

    override fun getItemCount(): Int = items.size
}