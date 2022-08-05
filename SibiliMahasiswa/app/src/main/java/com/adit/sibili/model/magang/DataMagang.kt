package com.adit.sibili.model.magang

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataMagang(
    @SerializedName("internship_industry_name")
    @Expose
    var industriName : String,

    @SerializedName("address")
    @Expose
    var address : String,

    @SerializedName("supervisor")
    @Expose
    var supervisor : String,
)
