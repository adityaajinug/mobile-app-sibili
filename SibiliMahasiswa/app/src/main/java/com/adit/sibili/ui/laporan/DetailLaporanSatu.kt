package com.adit.sibili.ui.laporan

import android.Manifest
import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adit.sibili.R
import com.adit.sibili.databinding.ActivityDetailLaporanSatuBinding
import java.io.File


class DetailLaporanSatu : AppCompatActivity() {
    private var binding: ActivityDetailLaporanSatuBinding? = null
    private val REQUEST_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailLaporanSatuBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var intent = intent
        val babName = intent.getSerializableExtra("bab_name")

        val fileBabName = intent.getSerializableExtra("file_bab_name")
        val koreksiName = intent.getSerializableExtra("file_koreksi_name")
        val fileBab = intent.getSerializableExtra("file_bab")
        val fileKoreksi = intent.getSerializableExtra("file_koreksi")


        binding!!.bab.text = babName.toString()
        binding!!.fileBab.text = fileBabName.toString()

        binding!!.titleFilebab.text = "File " + babName.toString() + " :"

        binding!!.fileKoreksi.text = koreksiName.toString()
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primary_color))

        binding!!.buttonback.setOnClickListener {
            startActivity(Intent(this,  LaporanSatuActivity::class.java))

        }
        // storage runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
        }
        binding!!.downloadFilebab.setOnClickListener {
            val urlBab = fileBab.toString()

            val requestBab = DownloadManager.Request(Uri.parse(urlBab))
            val titleBab = URLUtil.guessFileName(urlBab, null, null)
            requestBab.setTitle(titleBab)
            requestBab.setDescription("Download File ${babName.toString()}....")
            requestBab.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            requestBab.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, titleBab)
            requestBab.setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)
            requestBab.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_MOBILE)

            val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(requestBab)

            Toast.makeText(this@DetailLaporanSatu, "Download ${babName} Dimulai", Toast.LENGTH_LONG).show()

        }

        if(fileKoreksi == null) {
            binding!!.downloadFilekoreksi.visibility = View.GONE
        } else {
            binding!!.downloadFilekoreksi.visibility = View.VISIBLE
            binding!!.downloadFilekoreksi.setOnClickListener {

                val urlKoreksi = fileKoreksi.toString()
                val requestBab = DownloadManager.Request(Uri.parse(urlKoreksi))
                val titleBab = URLUtil.guessFileName(urlKoreksi, null, null)
                requestBab.setTitle(titleBab)
                requestBab.setDescription("Download File ${koreksiName.toString()}....")
                requestBab.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                requestBab.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, titleBab)
                requestBab.setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)
                requestBab.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_MOBILE)

                val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                dm.enqueue(requestBab)

                Toast.makeText(this@DetailLaporanSatu, "Download ${koreksiName} Dimulai", Toast.LENGTH_LONG).show()


            }
        }
    }
}