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
import com.adit.sibilidosen.model.mhsBimbingan.DataMhsBimbingan
import com.bumptech.glide.Glide

class MhsBimbinganAdapter(val context:Context, val listMhs : List<DataMhsBimbingan>
): RecyclerView.Adapter<MhsBimbinganAdapter.ViewHolder>() {
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nameMhs : TextView = view.findViewById(R.id.mhsName)
        val nim : TextView = view.findViewById(R.id.nim)
        val imga : ImageView = view.findViewById(R.id.imgMhs)
        val btnMagang : Button = view.findViewById(R.id.btn_magang)
        val btnBimbingan : Button = view.findViewById(R.id.btn_bimbingan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMhs[position]
        holder.nameMhs.text = item.mhsName


        val uri: String = item.img // or whatever you want

        Glide.with(holder.itemView.context).load(uri).into(holder.imga)

        holder.nim.text = item.nim

        holder.btnMagang.setOnClickListener {
            Toast.makeText(context, "Log Harian " + item.mhsName, Toast.LENGTH_LONG).show()
        }
        holder.btnBimbingan.setOnClickListener {
            Toast.makeText(context, "Log bimbingan " + item.mhsName, Toast.LENGTH_LONG).show()
        }





    }

    override fun getItemCount(): Int = listMhs.size
}