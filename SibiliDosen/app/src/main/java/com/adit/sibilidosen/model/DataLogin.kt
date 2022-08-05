package com.adit.sibilidosen.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataLogin(
    @Expose
    @SerializedName("dosen_name")
    val mhsName : String,

    @Expose
    @SerializedName("username")
    val username : String,

    @Expose
    @SerializedName("image")
    val image : String,

    @Expose
    @SerializedName("id_dosen")
    val dosenId : String,

    @Expose
    @SerializedName("id_user")
    val idUser : String,

    @Expose
    @SerializedName("pembimbing_id")
    val pembimbingId : String,

    @Expose
    @SerializedName("year_id")
    val yearId : String,
    @Expose
    @SerializedName("group")
    val group : String,
)
