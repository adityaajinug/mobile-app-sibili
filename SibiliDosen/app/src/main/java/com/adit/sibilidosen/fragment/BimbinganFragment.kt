package com.adit.sibilidosen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adit.sibilidosen.R
import com.adit.sibilidosen.databinding.FragmentBimbinganBinding
import com.adit.sibilidosen.fragment.bimbingan.SectionPager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BimbinganFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BimbinganFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentBimbinganBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBimbinganBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sectionPagerAdapter = SectionPager(childFragmentManager, 3)
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tablayout.setupWithViewPager(binding.viewPager)
    }

}