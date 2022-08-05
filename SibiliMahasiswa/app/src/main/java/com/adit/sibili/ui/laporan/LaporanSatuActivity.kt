package com.adit.sibili.ui.laporan

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.adit.sibili.MainActivity
import com.adit.sibili.R
import com.adit.sibili.adapter.BimbinganAdapter
import com.adit.sibili.databinding.ActivityLaporanSatuBinding
import com.adit.sibili.model.bimbingan.ResponseBimbingan
import com.adit.sibili.network.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LaporanSatuActivity : AppCompatActivity() {
    private var binding : ActivityLaporanSatuBinding? = null
    lateinit var myadapter: BimbinganAdapter
    lateinit var profil: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanSatuBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val recyclerLapSatu = binding!!.recyclerLaporanSatu

        recyclerLapSatu.setHasFixedSize(true)
        recyclerLapSatu.layoutManager = LinearLayoutManager(this)

        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primary_color))

        binding!!.buttonback.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))

        }
        binding!!.buttonTambah.setOnClickListener {
            startActivity(Intent(this, TambahLaporanSatu::class.java))

        }

        getLaporanSatu()
    }



    private fun getLaporanSatu() {
        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        if(profil.contains("username")) {
            val mhsId = profil.getString("id_user", null)

            val api = Service().getInstance()
            api.bimbingan(mhsId!!).enqueue(object : Callback<ResponseBimbingan> {
                override fun onResponse(
                    call: Call<ResponseBimbingan>,
                    response: Response<ResponseBimbingan>
                ) {
                    if(response.isSuccessful) {
                        if (response.body()?.status == true) {
                            val responseData = response.body()?.data
                            myadapter = BimbinganAdapter(baseContext, responseData!!)
                            myadapter.notifyDataSetChanged()
                            binding!!.recyclerLaporanSatu.adapter = myadapter
                            Log.d("response-bim", "${response.body()?.data}")

                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBimbingan>, t: Throwable) {
                    Log.d("pesan error", "Ra kejupuk")
                }

            })
        }
    }
}