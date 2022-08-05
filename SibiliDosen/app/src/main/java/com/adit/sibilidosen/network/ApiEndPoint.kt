package com.adit.sibilidosen.network

import com.adit.sibilidosen.model.ResponseLogin
import com.adit.sibilidosen.model.bimbingan.ResponseBabBimbingan
import com.adit.sibilidosen.model.bimbingan.ResponseBimbingan
import com.adit.sibilidosen.model.mhsBimbingan.ResponseMhsBimbingan
import retrofit2.Call
import retrofit2.http.*


interface ApiEndPoint {

    @FormUrlEncoded
    @POST("login/dosen")
    fun login(
        @Field("username") username : String,
        @Field("password") password : String
    ): Call<ResponseLogin>



    /*--------Bab ----------*/
    @GET("bimbingan/dosen")
    fun bab (
        @Query("id_user") id_user : String
    ): Call<ResponseBabBimbingan>
    /*--------End bab ----------*/


    @GET("bimbingan/mhsbimbingan/{babid}/{year}/{group}")
    fun bimbingan(
        @Path("babid") babid : String?,
        @Path("year") year: String,
        @Path("group") group: String

    ): Call<ResponseBimbingan>


    @GET("pembimbing")
    fun mhsBimbingan(
        @Query("pembimbing_id")id_user: String
    ): Call<ResponseMhsBimbingan>


}