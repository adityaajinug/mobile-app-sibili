package com.adit.sibilidosen.fragment.bimbingan

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adit.sibilidosen.R
import com.adit.sibilidosen.databinding.FragmentKkiBinding
import com.adit.sibilidosen.ui.LaporanSatuActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KkiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KkiFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentKkiBinding? = null
    private val binding get() = _binding!!

    private lateinit var profil: SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKkiBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDetail2.setOnClickListener {
            startActivity(Intent(this.activity, LaporanSatuActivity::class.java))
        }
    }

}