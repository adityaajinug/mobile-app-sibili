package com.adit.sibili.model.logHarian

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataLogHarian(
    @Expose
    @SerializedName("date_activity")
    val dateActivities : String,

    @Expose
    @SerializedName("activity")
    val activities : String,
    @Expose
    @SerializedName("short_activity")
    val shortActivity : String,

    @Expose
    @SerializedName("mhs_id")
    val mhsId : Int,
    @Expose

    @SerializedName("category_activity")
    val categoryActivity : Int,
)
