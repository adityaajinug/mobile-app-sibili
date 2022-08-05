package com.adit.sibilidosen.model.bimbingan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataBimbingan(
    @Expose
    @SerializedName("bab_id")
    val babDosenId : Int,

    @Expose
    @SerializedName("user_id")
    val userId : Int,

    @Expose
    @SerializedName("id_bimbingan")
    val bimbinganId : Int,

    @Expose
    @SerializedName("nim")
    val nim : String,

    @Expose
    @SerializedName("mhs_name")
    val mhsName : String,

    @Expose
    @SerializedName("bab_name")
    val babName : String,

    @Expose
    @SerializedName("bab_filename")
    val fileBabName : String,

    @Expose
    @SerializedName("file")
    val fileBab : String,

    @Expose
    @SerializedName("group")
    val group : String,

    @Expose
    @SerializedName("year")
    val year : String,

    @Expose
    @SerializedName("status_konfirmasi")
    val statusKonfirmasi : Int,
)