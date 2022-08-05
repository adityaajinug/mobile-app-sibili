package com.adit.sibilidosen.model.bimbingan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataBabBimbingan (
    @Expose
    @SerializedName("bab_id")
    val babId : Int,

    @Expose
    @SerializedName("bab_name")
    val babName : String,

    @Expose
    @SerializedName("id_pembimbing")
    val pembimbingId : String,


    @Expose
    @SerializedName("description")
    val description : String,
)