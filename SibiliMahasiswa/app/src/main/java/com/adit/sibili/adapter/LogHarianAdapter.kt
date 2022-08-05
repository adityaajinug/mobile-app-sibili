package com.adit.sibili.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.adit.sibili.R
import com.adit.sibili.model.logHarian.DataLogHarian
import com.adit.sibili.model.logHarian.ResponseLogHarian
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LogHarianAdapter(
    val context: Context, val listLogharian : List<DataLogHarian>
    ): RecyclerView.Adapter<LogHarianAdapter.ViewHolder>()   {
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvTanggal: TextView = view.findViewById(R.id.tanggal)
        val tvActivity: TextView = view.findViewById(R.id.desc)
        val btnDetail: Button = view.findViewById(R.id.btn_detail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_log_harian, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listLogharian[position]

        holder.tvTanggal.text = item.dateActivities
        holder.tvActivity.text = item.shortActivity
        holder.btnDetail.setOnClickListener {
            Toast.makeText(context, "Aktivtias : " + item.activities, Toast.LENGTH_LONG).show()
        }


    }

    override fun getItemCount() = listLogharian.size

}