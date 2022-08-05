package com.adit.sibili

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.adit.sibili.databinding.ActivityLoginBinding
import com.adit.sibili.model.ResponseLogin
import com.adit.sibili.network.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private var binding : ActivityLoginBinding? = null
    private var username : String = ""
    private var password : String = ""
    lateinit var profil: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        profil = getSharedPreferences("login_session", MODE_PRIVATE)

        val user = profil.getString("username", null) != null
        val pass = profil.getString("mhs_name", null) != null
        val img = profil.getString("image", null) != null
        val mhsId = profil.getString("id_mhs", null) != null
        val idUser = profil.getString("id_user", null) !=null
        val pembimbingId = profil.getString("pembimbing_id", null) !=null
        val yearId = profil.getString("year_id", null) !=null


        if( user && pass  && img  && mhsId && idUser && pembimbingId && yearId) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        binding!!.buttonLogin.setOnClickListener {
            username = binding!!.textUsername.text.toString()
            password = binding!!.textPass.text.toString()


            when {
                username == "" -> {
                    binding!!.textUsername.error = "Username harus diisi"
                }
                password == "" -> {
                    binding!!.textPass.error = "Password harus diisi"
                }

                else -> {
                    binding!!.loadingLogin.visibility = View.VISIBLE
                    getData()
                }
            }
        }
    }
    private fun getData () {
        val api = Service().getInstance()
        api.login(username, password).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {

                if(response.isSuccessful) {
                    if(response.body()?.status == true) {

                        getSharedPreferences("login_session", MODE_PRIVATE)
                            .edit()
                            .putString("username", response.body()?.data?.username)
                            .putString("mhs_name", response.body()?.data?.mhsName)
                            .putString("image", response.body()?.data?.image)
                            .putString("id_mhs", response.body()?.data?.mhsId)
                            .putString("id_user", response.body()?.data?.idUser)
                            .putString("pembimbing_id", response.body()?.data?.pembimbingId)
                            .putString("year_id", response.body()?.data?.yearId)
                            .apply()
                        Log.d("response-login", "${response.body()?.data}")

                        binding!!.loadingLogin.visibility = View.GONE
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        binding!!.loadingLogin.visibility = View.GONE
                        Toast.makeText(this@LoginActivity, "Login Gagal, Cek Kembali username dan password",
                            Toast.LENGTH_LONG).show()
                    }
                } else {
                    binding!!.loadingLogin.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, "Login Gagal, Terjadi Kesalahan",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
               Log.e("Pesan Error", "${t.message}}")
            }


        })
    }

}