package com.adit.sibili.ui.logHarian

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adit.sibili.MainActivity
import com.adit.sibili.R
import com.adit.sibili.adapter.LogHarianAdapter
import com.adit.sibili.databinding.ActivityLogHarianBinding
import com.adit.sibili.fragment.MagangFragment
import com.adit.sibili.model.logHarian.DataLogHarian
import com.adit.sibili.model.logHarian.ResponseLogHarian
import com.adit.sibili.network.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogHarianActivity : AppCompatActivity() {

    private var binding: ActivityLogHarianBinding? = null
    lateinit var myadapter: LogHarianAdapter

    lateinit var profil: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogHarianBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val recyclerlog = binding!!.recyclerlogharian


        recyclerlog.setHasFixedSize(true)
        recyclerlog.layoutManager = LinearLayoutManager(this)


        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primary_color))

        binding!!.buttonback.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }
        binding!!.buttonTambah.setOnClickListener {
            startActivity(Intent(this, TambahLogHarian::class.java))

        }



        getDataLogHarian()


    }


    private fun getDataLogHarian() {
        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        if(profil.contains("username")) {
            val mhsId = profil.getString("id_mhs", null)

            val api = Service().getInstance()
            api.activities(mhsId!!).enqueue(object : Callback<ResponseLogHarian> {
                override fun onResponse(
                    call: Call<ResponseLogHarian>,
                    response: Response<ResponseLogHarian>
                ) {
                    if(response.isSuccessful) {
                        if (response.body()?.status == true) {
                            val responseData = response.body()?.data

                            myadapter = LogHarianAdapter(baseContext, responseData!!)
                            myadapter.notifyDataSetChanged()
                            binding!!.recyclerlogharian.adapter = myadapter
                            Log.d("response", "${response.body()?.data}")

                        }
                    }
                }

                override fun onFailure(call: Call<ResponseLogHarian>, t: Throwable) {
                    Log.d("pesan error", "Ra kejupuk")
                }

            })
        }




        }

}