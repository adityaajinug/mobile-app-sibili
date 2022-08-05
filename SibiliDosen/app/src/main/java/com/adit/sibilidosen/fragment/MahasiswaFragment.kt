package com.adit.sibilidosen.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adit.sibilidosen.R
import com.adit.sibilidosen.adapter.BabAdapter
import com.adit.sibilidosen.adapter.MhsBimbinganAdapter
import com.adit.sibilidosen.databinding.FragmentBimbinganBinding
import com.adit.sibilidosen.databinding.FragmentMahasiswaBinding
import com.adit.sibilidosen.model.bimbingan.ResponseBabBimbingan
import com.adit.sibilidosen.model.mhsBimbingan.ResponseMhsBimbingan
import com.adit.sibilidosen.network.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MahasiswaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MahasiswaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentMahasiswaBinding? = null
    private val binding get() = _binding!!
    lateinit var myadapter: MhsBimbinganAdapter
    lateinit var profil: SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMahasiswaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = binding!!.recyclerMhs

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this.activity)
        getMhs()
    }

    private fun getMhs() {
        profil = activity?.getSharedPreferences("login_session", Context.MODE_PRIVATE)!!

        if (profil.contains("username")) {
            val mhsId = profil.getString("pembimbing_id", null)

            val api = Service().getInstance()
            api.mhsBimbingan(mhsId!!).enqueue(object : Callback<ResponseMhsBimbingan> {
                override fun onResponse(
                    call: Call<ResponseMhsBimbingan>,
                    response: Response<ResponseMhsBimbingan>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.status == true) {
                            val responseData = response.body()?.data
                            myadapter = MhsBimbinganAdapter(requireContext(), responseData!!)
                            myadapter.notifyDataSetChanged()
                            binding!!.recyclerMhs.adapter = myadapter
                            Log.d("response-bab", "${response.body()?.data}")
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMhsBimbingan>, t: Throwable) {
                    Log.d("mhs_err", "ga keambil")
                }
            })
        }
    }
}