package com.adit.sibili.ui.laporan

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import com.adit.sibili.R
import com.adit.sibili.databinding.ActivityTambahLaporanSatuBinding
import com.adit.sibili.model.bimbingan.RequestBimbingan
import com.adit.sibili.model.bimbingan.ResponseBab
import com.adit.sibili.network.Service
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class TambahLaporanSatu : AppCompatActivity() {
    private var binding : ActivityTambahLaporanSatuBinding? = null
    lateinit var profil: SharedPreferences

    lateinit var idBabLap : String
    lateinit var babNameLap : String
    lateinit var  fileName : String
    lateinit var fileUri: Uri
    lateinit var cursor : Cursor
    lateinit var filePart : File
//    lateinit var requstFile : RequestBody

    private val REQUEST_WRITE_PERMISSION = 180
    private val REQUEST_PICK_FILE = 220
    private val PERMISSION_REQUEST_STORAGE = 5




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahLaporanSatuBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primary_color))

        binding!!.buttonback.setOnClickListener {
            startActivity(Intent(this, LaporanSatuActivity::class.java))

        }
        binding!!.btnPilihFile.setOnClickListener {
            chooseFile()
        }
        binding!!.btnUpload.setOnClickListener {
            postLaporanSatu()
        }
        getBab()

    }

    private fun getBab() {
        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        if(profil.contains("username")) {
            val idUser = profil.getString("id_user", null)

            val api = Service().getInstance()
            api.bab(idUser!!).enqueue(object : Callback<ResponseBab> {
                override fun onResponse(call: Call<ResponseBab>, response: Response<ResponseBab>) {
                    if(response.isSuccessful) {
                        if(response.body()?.status == true) {
                            val response = response.body()!!.data

                            Log.d("response-bab", "${response}")

                            val listBab = arrayOfNulls<String>(response.size)
                            val listBabId = arrayOfNulls<String>(response.size)

                            for (i in response.indices) {
                                listBab[i] = response[i].babName
                                listBabId[i] = response[i].babId.toString()

                                binding!!.textField2.onItemSelectedListener = object :
                                    AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        adapterView: AdapterView<*>,
                                        view: View?,
                                        i: Int,
                                        l: Long
                                    ) {
                                        babNameLap = adapterView.getItemAtPosition(i).toString()
                                        val jumlah = i
                                        idBabLap = listBabId[jumlah]!!
                                        Log.d("bab", babNameLap)
                                        Log.d("bab", idBabLap)
                                    }

                                    override fun onNothingSelected(adapterView: AdapterView<*>?) {
                                        Toast.makeText(
                                            this@TambahLaporanSatu,
                                            "Bab Belum ada",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Log.d("err-bab", "gaada")
                                    }
                                }
                                val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this@TambahLaporanSatu,
                                    android.R.layout.simple_spinner_item, listBab )
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                                binding!!.textField2.setAdapter(spinnerArrayAdapter)
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<ResponseBab>, t: Throwable) {
                   Log.e("pesan", "${t.message}")
                }
            })
        }
    }
    private fun chooseFile() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                PERMISSION_REQUEST_STORAGE
            )
        } else {
            openFilePicker()
        }
    }

    private fun openFilePicker() {
        val fileIntent = Intent()
        fileIntent.action = Intent.ACTION_GET_CONTENT
        fileIntent.type = "*/*"
        startActivityForResult(Intent.createChooser(fileIntent, "Select File"), REQUEST_PICK_FILE)
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_WRITE_PERMISSION
            )
        } else {
            postLaporanSatu()
        }
    }



    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PICK_FILE) {
                if (data != null) {
                    fileUri = data.data!!
                    cursor = contentResolver.query(fileUri!!, null, null, null, null)!!
                    assert(cursor != null)
                    cursor.moveToFirst()
                    fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    cursor.close()

                    binding!!.textView7.text = fileName

                                        Log.d("coba", java.lang.String.valueOf(fileUri))


                }
            }
        }
    }

    fun postLaporanSatu() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), REQUEST_WRITE_PERMISSION
            )
        }

            profil = getSharedPreferences("login_session", MODE_PRIVATE)
            if(profil.contains("username")) {
                val idUser = profil.getString("id_user", null).toString()
                val pembimbingId = profil.getString("pembimbing_id", null).toString()

                val yearId = profil.getString("year_id", null).toString()

                filePart = FileUtils.getFile(this, fileUri)!!
                Log.d("unggah", "${idUser}")
                Log.d("unggah", "${pembimbingId}")
                Log.d("unggah", "${filePart}")
                val requestFile = RequestBody.create(MediaType.parse("multipart/form-file"), filePart)
                val partFile = MultipartBody.Part.createFormData("file", filePart.name, requestFile)

                val statusKonfirmasi = 0
                val categoryBimbingan = 1


                val requestUserId = RequestBody.create(MediaType.parse("text/plain"), idUser)
                val requestPembimbingId = RequestBody.create(MediaType.parse("text/plain"), pembimbingId)
                val requestYearId = RequestBody.create(MediaType.parse("text/plain"), yearId)
                val requestStatusKonfirmasi = RequestBody.create(MediaType.parse("text/plain"), statusKonfirmasi.toString())
                val requestCategoryBimbingan = RequestBody.create(MediaType.parse("text/plain"), categoryBimbingan.toString())
                val requestBabId = RequestBody.create(MediaType.parse("text/plain"), idBabLap)


                val api = Service().getInstance()
                api.uploadBimbingan(partFile, requestUserId, requestPembimbingId, requestBabId, requestYearId, requestStatusKonfirmasi, requestCategoryBimbingan)
                    .enqueue(object : Callback<RequestBimbingan> {
                        override fun onResponse(
                            call: Call<RequestBimbingan>,
                            response: Response<RequestBimbingan>
                        ) {
                            if(response.isSuccessful) {
                                Toast.makeText(this@TambahLaporanSatu, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@TambahLaporanSatu, LaporanSatuActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this@TambahLaporanSatu, "Data Gagal ditambahkan", Toast.LENGTH_SHORT).show()
                            }
                            Log.d("unggah", "${response.body()}")
                        }

                        override fun onFailure(call: Call<RequestBimbingan>, t: Throwable) {
                            Log.e("err-upload-bimbingan", "${t.message}")
                        }

                    })




        }
    }
}