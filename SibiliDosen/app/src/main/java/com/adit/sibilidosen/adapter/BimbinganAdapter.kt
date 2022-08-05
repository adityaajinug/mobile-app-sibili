package com.adit.sibilidosen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.adit.sibilidosen.R
import com.adit.sibilidosen.model.bimbingan.DataBimbingan

class BimbinganAdapter(val context: Context, val listBimbingan : List<DataBimbingan>
): RecyclerView.Adapter<BimbinganAdapter.ViewHolder>() {
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val name : TextView = view.findViewById(R.id.tv_name)
        val nim : TextView = view.findViewById(R.id.tv_nim)
        val status : ImageView = view.findViewById(R.id.status_konfirmasi)
        val bab : TextView = view.findViewById(R.id.bab)
        val btnDetail : Button = view.findViewById(R.id.btn_detail)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_bimbingan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listBimbingan[position]
        holder.name.text = item.mhsName
        holder.nim.text = item.nim
        holder.bab.text = item.babName

        if(item.statusKonfirmasi == 1) {
            holder.status.setImageResource(R.drawable.diterima)
        } else {
            holder.status.setImageResource(R.drawable.revisi)
        }
        holder.btnDetail.setOnClickListener {
            Toast.makeText(context, item.fileBabName, Toast.LENGTH_LONG).show()
        }


    }

    override fun getItemCount(): Int = listBimbingan.size
}