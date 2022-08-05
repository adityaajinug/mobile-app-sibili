package com.adit.sibilidosen.ui


import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.adit.sibilidosen.R

import com.adit.sibilidosen.adapter.BabAdapter
import com.adit.sibilidosen.adapter.BimbinganAdapter
import com.adit.sibilidosen.databinding.ActivityDetailBabBinding
import com.adit.sibilidosen.model.bimbingan.ResponseBimbingan
import com.adit.sibilidosen.network.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailBabActivity : AppCompatActivity() {
    private var binding: ActivityDetailBabBinding? = null
    lateinit var myadapter: BimbinganAdapter
    lateinit var profil: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBabBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primary_color))
        val recyclerLapSatu = binding!!.recyclerMhsLaporansatu

        recyclerLapSatu.setHasFixedSize(true)
        recyclerLapSatu.layoutManager = LinearLayoutManager(this)

        getBimbingan()

    }

    private fun getBimbingan() {
        var intent = intent
        val babId = intent.getSerializableExtra("bab_id")

        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        if (profil.contains("username")) {
            val yearId = profil.getString("year_id", null)
            val group = profil.getString("group", null)

            val api = Service().getInstance()
            api.bimbingan(babId?.toString(), yearId!!, group!!).enqueue(object :
                Callback<ResponseBimbingan> {
                override fun onResponse(
                    call: Call<ResponseBimbingan>,
                    response: Response<ResponseBimbingan>
                ) {
                    if(response.isSuccessful) {
                        if (response.body()?.status == true) {
                            val responseData = response.body()?.data
                            myadapter = BimbinganAdapter(baseContext, responseData!!)
                            myadapter.notifyDataSetChanged()
                            binding!!.recyclerMhsLaporansatu.adapter = myadapter
                            Log.d("response-bimbingan", "${response.body()?.data}")

                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBimbingan>, t: Throwable) {
                    Log.d("pesan-detail", "list bimbingan tidak ada")
                }

            })

        }


    }
}