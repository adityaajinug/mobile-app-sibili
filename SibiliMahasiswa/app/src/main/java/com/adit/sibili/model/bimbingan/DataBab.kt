package com.adit.sibili.model.bimbingan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataBab(
    @Expose
    @SerializedName("bab_id")
    val babId : Int,

    @Expose
    @SerializedName("bab_name")
    val babName : String,
)
