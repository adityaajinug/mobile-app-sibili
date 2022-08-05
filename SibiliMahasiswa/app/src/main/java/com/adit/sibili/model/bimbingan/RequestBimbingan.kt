package com.adit.sibili.model.bimbingan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestBimbingan(
    @Expose
    @SerializedName("bab_dosen_id")
    val babDosenId : String,

    @Expose
    @SerializedName("file")
    val file : String,

    @Expose
    @SerializedName("year_id")
    val yearId : String,

    @Expose
    @SerializedName("pembimbing_id")
    val pembimbingId : String,

    @Expose
    @SerializedName("user_id")
    val userId : String,

    @Expose
    @SerializedName("status_konfirmasi")
    val statusKonfirmasi : String,
    @Expose
    @SerializedName("category_bimbingan")
    val categoryBimbingan : String,
)
