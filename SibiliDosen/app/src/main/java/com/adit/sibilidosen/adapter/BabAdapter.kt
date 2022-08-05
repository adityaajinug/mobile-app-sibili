package com.adit.sibilidosen.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adit.sibilidosen.R
import com.adit.sibilidosen.model.bimbingan.DataBabBimbingan
import com.adit.sibilidosen.ui.DetailBabActivity

class BabAdapter(
    val context: Context, val listBab : List<DataBabBimbingan>
): RecyclerView.Adapter<BabAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val bab : TextView = view.findViewById(R.id.tv_bab)
        val desc : TextView = view.findViewById(R.id.tv_desc)
        val btnDetail : Button = view.findViewById(R.id.btn_detail)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_bab, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listBab[position]
        holder.bab.text = item.babName
        holder.desc.text = item.description



        holder.btnDetail.setOnClickListener {
            val intent = Intent(context, DetailBabActivity::class.java)
            intent.putExtra("bab_id", item.babId)
            intent.putExtra("bab_name", item.babName)
            intent.putExtra("desc", item.description)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listBab.size
}