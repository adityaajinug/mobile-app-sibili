package com.adit.sibilidosen.model.mhsBimbingan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataMhsBimbingan(
    @Expose
    @SerializedName("id_mhs")
    var idMhs : Int,
    @Expose
    @SerializedName("mhs_name")
    var mhsName : String,
    @Expose
    @SerializedName("nim")
    var nim : String,
    @Expose
    @SerializedName("image")
    var img : String,


)
