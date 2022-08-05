package com.adit.sibili.fragment.bimbingan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adit.sibili.R

class BimbinganMenuAdapter(
    private val listData: List<BimbinganMenuModel>,
    private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<BimbinganMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BimbinganMenuAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_kki, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BimbinganMenuAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: BimbinganMenuModel, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {

                val title: TextView = itemView.findViewById(R.id.tv_title)
                val desc: TextView = itemView.findViewById(R.id.tv_desc)
                val icon: ImageView = itemView.findViewById(R.id.icon_file)
                val btn : Button = itemView.findViewById(R.id.btn_detail)
                title.text = data.title
                desc.text = data.desc
                icon.setImageResource(data.icon)

                btn.setOnClickListener{
                    itemAdapterCallback.onClick(it, data)
                }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: BimbinganMenuModel)
    }


}

