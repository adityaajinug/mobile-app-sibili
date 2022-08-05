package com.adit.sibili.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adit.sibili.R
import com.adit.sibili.model.bimbingan.DataBimbingan
import com.adit.sibili.ui.laporan.DetailLaporanSatu

class BimbinganAdapter(
    val context: Context, val listBimbingan : List<DataBimbingan>
    ): RecyclerView.Adapter<BimbinganAdapter.ViewHolder>() {
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val bab : TextView = view.findViewById(R.id.tv_bab)
        val desc : TextView = view.findViewById(R.id.tv_desc)
        val statusKonfirmasi : ImageView = view.findViewById(R.id.status_konfirmasi)
        val btnDetail : Button = view.findViewById(R.id.btn_detail)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_bimbingan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listBimbingan[position]
        holder.bab.text = item.babName
        holder.desc.text = item.description



        if(item.statusKonfirmasi == 1) {
            holder.statusKonfirmasi.setImageResource(R.drawable.diterima)
        } else {
            holder.statusKonfirmasi.setImageResource(R.drawable.revisi)
        }
        holder.btnDetail.setOnClickListener {
            val intent = Intent(context, DetailLaporanSatu::class.java)

            intent.putExtra("bab_name", item.babName)
            intent.putExtra("desc", item.description)
            intent.putExtra("file_bab_name", item.fileBabName)
            intent.putExtra("file_bab", item.fileBab)
            intent.putExtra("file_koreksi", item.fileKoreksi)
            intent.putExtra("file_koreksi_name", item.fileKoreksiName)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = listBimbingan.size
}