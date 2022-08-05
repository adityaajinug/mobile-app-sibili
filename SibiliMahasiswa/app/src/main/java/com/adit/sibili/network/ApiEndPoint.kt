package com.adit.sibili.network


import com.adit.sibili.model.ResponseLogin
import com.adit.sibili.model.bimbingan.RequestBimbingan
import com.adit.sibili.model.bimbingan.ResponseBab
import com.adit.sibili.model.bimbingan.ResponseBimbingan
import com.adit.sibili.model.logHarian.RequestLogHarian
import com.adit.sibili.model.logHarian.ResponseLogHarian
import com.adit.sibili.model.magang.ResponseMagang
import com.adit.sibili.model.semester.ResponseSemester
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ApiEndPoint {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username : String,
        @Field("password") password : String
    ): Call<ResponseLogin>

    /*--------Activity ----------*/
    @GET("activity")
    fun activities(
        @Query("id_mhs") id_mhs:String
    ): Call<ResponseLogHarian>


    @FormUrlEncoded
    @POST("activity")
    fun postActivities(
        @Field("date_activity") tanggal : String,
        @Field("activity") activity : String,
        @Field("mhs_id") mhsId : Int,
        @Field("category_activity") categotyActivity : Int
    ): Call<RequestLogHarian>

    /*--------End Activity ----------*/

    /*--------Magang ----------*/
    @GET("activity/magang")
    fun magang(
        @Query("mhs_id") mhs_id:String
    ): Call<ResponseMagang>

    /*--------End Magang ----------*/

    /*--------Bimbingan ----------*/
    @GET("bimbingan")
    fun bimbingan(
        @Query("id_user") id_user: String
    ): Call<ResponseBimbingan>

    @Multipart
    @POST("bimbingan/post/laporansatu")
    fun uploadBimbingan(
        @Part file: MultipartBody.Part?,
        @Part("user_id") userId : RequestBody?,
        @Part("pembimbing_id") pembimbingId : RequestBody?,
        @Part("bab_dosen_id") babDosenId : RequestBody?,
        @Part("year_id") yearId : RequestBody?,
        @Part("status_konfirmasi") statusKonfirmasi : RequestBody?,
        @Part("category_bimbingan") categoryBimbingan : RequestBody?,

    ): Call<RequestBimbingan>



    /*--------End Bimbingan ----------*/

    /*--------Semester ----------*/
    @GET("bimbingan/semester")
    fun semester(
        @Query("id_user") id_user : String
    ): Call<ResponseSemester>

    /*--------End Semester ----------*/

    /*--------Bab ----------*/
    @GET("bimbingan/bab")
    fun bab (
        @Query("id_user") id_user : String
    ): Call<ResponseBab>
    /*--------End bab ----------*/


}