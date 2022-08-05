package com.adit.sibili.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.adit.sibili.LoginActivity
import com.adit.sibili.R
import com.adit.sibili.databinding.FragmentHomeBinding
import com.adit.sibili.databinding.FragmentMagangBinding
import com.adit.sibili.model.magang.DataMagang
import com.adit.sibili.model.magang.ResponseMagang
import com.adit.sibili.network.Service
import com.adit.sibili.ui.logHarian.LogHarianActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MagangFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MagangFragment : Fragment() {
    private var _binding : FragmentMagangBinding? = null
    private val binding get() = _binding!!

    private lateinit var profil: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMagangBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.logharian.setOnClickListener {
            startActivity(Intent(this.activity, LogHarianActivity::class.java))
        }

        getMagang()
    }

    private fun getMagang() {
        profil = activity?.getSharedPreferences("login_session", Context.MODE_PRIVATE)!!

        if(profil.contains("username")) {
            val mhsId = profil.getString("id_mhs", null)
            val api = Service().getInstance()
            api.magang(mhsId!!).enqueue(object : Callback<ResponseMagang> {
                override fun onResponse(
                    call: Call<ResponseMagang>,
                    response: Response<ResponseMagang>
                ) {
                    if(response.isSuccessful) {
                        if(response.body()?.status == true) {
                            val industri = response.body()?.data?.industriName
                            val address = response.body()?.data?.address
                            val supervisor = response.body()?.data?.supervisor

                            binding.tvInstansi.text = industri
                            binding.tvAlamat.text = address
                            binding.tvPenyelia.text = supervisor

                        }
                    }

                }

                override fun onFailure(call: Call<ResponseMagang>, t: Throwable) {
                    Log.d("magang-error", "data ga keambil")
                }

            })
        }

    }
}