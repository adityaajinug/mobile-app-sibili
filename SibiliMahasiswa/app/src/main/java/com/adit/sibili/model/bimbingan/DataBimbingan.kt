package com.adit.sibili.model.bimbingan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataBimbingan(
    @Expose
    @SerializedName("bab_dosen_id")
    val babDosenId : Int,

    @Expose
    @SerializedName("bab_name")
    val babName : String,

    @Expose
    @SerializedName("description")
    val description : String,

    @Expose
    @SerializedName("bab_filename")
    val fileBabName : String,

    @Expose
    @SerializedName("file_bab")
    val fileBab : String,

    @Expose
    @SerializedName("file_koreksi")
    val fileKoreksi : String,

    @Expose
    @SerializedName("koreksi_filename")
    val fileKoreksiName : String,

    @Expose
    @SerializedName("status_konfirmasi")
    val statusKonfirmasi : Int,

    @Expose
    @SerializedName("category_bimbingan")
    val categoryBimbingan : Int,





)
