package com.adit.sibilidosen.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adit.sibilidosen.LoginActivity
import com.adit.sibilidosen.R
import com.adit.sibilidosen.databinding.FragmentMenuBinding
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    private var _binding : FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var profil: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        profil = activity?.getSharedPreferences("login_session", Context.MODE_PRIVATE)!!
        binding.accountName.text = profil.getString("dosen_name", null)
        binding.accountNim.text = profil.getString("username", null)
        Picasso.get().load(profil.getString("image", null)).into(binding.imgProfile)
        binding.logout.setOnClickListener {
            profil.edit().clear().commit()

            startActivity(Intent(this.activity, LoginActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}