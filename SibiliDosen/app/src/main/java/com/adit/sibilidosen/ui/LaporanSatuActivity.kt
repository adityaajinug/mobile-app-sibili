package com.adit.sibilidosen.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.adit.sibilidosen.MainActivity
import com.adit.sibilidosen.R
import com.adit.sibilidosen.adapter.BabAdapter
import com.adit.sibilidosen.databinding.ActivityLaporanSatuBinding
import com.adit.sibilidosen.model.bimbingan.ResponseBabBimbingan
import com.adit.sibilidosen.network.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaporanSatuActivity : AppCompatActivity() {
    private var binding : ActivityLaporanSatuBinding? = null
    lateinit var myadapter: BabAdapter
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
        getBab()
    }

    private fun getBab() {
        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        if (profil.contains("username")) {
            val mhsId = profil.getString("id_user", null)

            val api = Service().getInstance()
            api.bab(mhsId!!).enqueue(object : Callback<ResponseBabBimbingan> {
                override fun onResponse(
                    call: Call<ResponseBabBimbingan>,
                    response: Response<ResponseBabBimbingan>
                ) {
                    if(response.isSuccessful) {
                        if (response.body()?.status == true) {
                            val responseData = response.body()?.data
                            myadapter = BabAdapter(baseContext, responseData!!)
                            myadapter.notifyDataSetChanged()
                            binding!!.recyclerLaporanSatu.adapter = myadapter
                            Log.d("response-bab", "${response.body()?.data}")

                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBabBimbingan>, t: Throwable) {
                    Log.d("pesan error", "ga keambil")
                }


            })
        }
    }
}