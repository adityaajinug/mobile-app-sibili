package com.adit.sibili.ui.logHarian

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.DateTimePatternGenerator.PatternInfo.OK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.adit.sibili.R
import com.adit.sibili.databinding.ActivityLogHarianBinding
import com.adit.sibili.databinding.ActivityTambahLogHarianBinding
import com.adit.sibili.model.logHarian.RequestLogHarian
import com.adit.sibili.model.logHarian.ResponseLogHarian
import com.adit.sibili.network.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class TambahLogHarian : AppCompatActivity() {
    private var binding: ActivityTambahLogHarianBinding? = null
    lateinit var profil: SharedPreferences
    private lateinit var tanggal : String
    private lateinit var desc : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahLogHarianBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


//        binding!!.tanggal

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            myCalendar.set(Calendar.MONTH, month)


            updateLabel(myCalendar)

        }
        binding!!.tanggal.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primary_color))
        binding!!.buttonBack.setOnClickListener {
            startActivity(Intent(this, LogHarianActivity::class.java))
        }

        binding!!.btnPostLogHarian.setOnClickListener {
            tanggal = binding!!.tanggal.text.toString()
            desc = binding!!.activity.text.toString()

            postLogHarian()

        }
    }



    private fun updateLabel(myCalendar: Calendar) {
        val dateFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(dateFormat, Locale.ENGLISH)
        binding!!.tanggal.setText(sdf.format(myCalendar.time))

    }
    private fun postLogHarian() {
        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        if(profil.contains("username")) {
            val mhsId = profil.getString("id_mhs", null).toString().toInt()
            val category = 1;
            val api = Service().getInstance()
            api.postActivities(tanggal, desc, mhsId, category).enqueue(object : Callback<RequestLogHarian> {
                override fun onResponse(
                    call: Call<RequestLogHarian>,
                    response: Response<RequestLogHarian>
                ) {
                    if(response.isSuccessful) {
                        Toast.makeText(this@TambahLogHarian, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@TambahLogHarian, LogHarianActivity::class.java))
                        finish()
                    }
                    Log.d("post-log", "${response.body()}")

                }

                override fun onFailure(call: Call<RequestLogHarian>, t: Throwable) {
                    Log.d("error-tambah-log-harian", "data tidak masuk")
                }

            })
        }


    }
}