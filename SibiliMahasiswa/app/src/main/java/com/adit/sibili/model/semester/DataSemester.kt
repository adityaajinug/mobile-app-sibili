package com.adit.sibili.model.semester

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataSemester(
    @SerializedName("ganjil")
    @Expose
    var semesterGanjil : Int,
    @SerializedName("genap")
    @Expose
    var semesterGenap : Int,

)
