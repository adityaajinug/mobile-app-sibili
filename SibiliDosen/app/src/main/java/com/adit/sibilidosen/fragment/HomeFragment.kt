package com.adit.sibilidosen.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adit.sibilidosen.R
import com.adit.sibilidosen.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var profil: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profil = activity?.getSharedPreferences("login_session", Context.MODE_PRIVATE)!!
        binding.nameAccount.text = profil.getString("dosen_name", null)
        Picasso.get().load(profil.getString("image", null)).into(binding.imgAccount)

        getGreetingMessage()

        binding.greeting.text = getGreetingMessage()


    }
    fun getGreetingMessage():String{
        val c = Calendar.getInstance()
        val timeOfDay = c.get(Calendar.HOUR_OF_DAY)

        return when (timeOfDay) {
            in 0..9 -> "Selamat Pagi"
            in 10..15 -> "Selamat Siang"
            in 16..18 -> "Selamat Sore"
            in 18..23,59 -> "Selamat Malam"
            else -> "Hello"
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}