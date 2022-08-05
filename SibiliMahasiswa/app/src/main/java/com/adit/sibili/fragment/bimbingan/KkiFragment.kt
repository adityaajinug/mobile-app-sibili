package com.adit.sibili.fragment.bimbingan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adit.sibili.R
import com.adit.sibili.databinding.FragmentHomeBinding
import com.adit.sibili.databinding.FragmentKkiBinding
import com.adit.sibili.model.semester.ResponseSemester
import com.adit.sibili.network.Service
import com.adit.sibili.ui.laporan.LaporanSatuActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KkiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KkiFragment : Fragment(), BimbinganMenuAdapter.ItemAdapterCallback {
    private var adapter: BimbinganMenuAdapter?=null
    private var menuArrayList: ArrayList<BimbinganMenuModel> = ArrayList()

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
        addData()

//        dataSemesterGanjil()
//        dataSemesterGenap()


        adapter = BimbinganMenuAdapter(menuArrayList, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter

//        semester()

    }

    private fun addData() {

        menuArrayList = ArrayList()
        menuArrayList.add(BimbinganMenuModel("Proposal", "KKI I", R.drawable.file_abu, 1))
        menuArrayList.add(BimbinganMenuModel("Laporan", "KKI I", R.drawable.file_garis_abu, 3))
        menuArrayList.add(BimbinganMenuModel("Proposal", "KKI II", R.drawable.file_abu, 2))
        menuArrayList.add(BimbinganMenuModel("Laporan", "KKI II", R.drawable.file_garis_abu, 4))
        menuArrayList.add(BimbinganMenuModel("Guide", "KKI II", R.drawable.file_garis_abu, 5))
    }



    fun dataSemesterGanjil() {
        menuArrayList = ArrayList()
        menuArrayList.add(BimbinganMenuModel("Proposal", "KKI I", R.drawable.file_abu, 1))
        menuArrayList.add(BimbinganMenuModel("Laporan", "KKI I", R.drawable.file_garis_abu, 3))
    }
    fun dataSemesterGenap() {
        menuArrayList = ArrayList()
        menuArrayList.add(BimbinganMenuModel("Proposal", "KKI I", R.drawable.file_abu, 1))
        menuArrayList.add(BimbinganMenuModel("Laporan", "KKI I", R.drawable.file_garis_abu, 3))
        menuArrayList.add(BimbinganMenuModel("Proposal", "KKI II", R.drawable.file_abu, 2))
        menuArrayList.add(BimbinganMenuModel("Laporan", "KKI II", R.drawable.file_garis_abu, 4))
    }

    override fun onClick(v: View, data: BimbinganMenuModel) {
        if(data.id == 1) {
            Toast.makeText(context, "Proposal KKI I", Toast.LENGTH_SHORT).show()
        } else if(data.id == 2) {
            Toast.makeText(context, "Proposal KKI II", Toast.LENGTH_SHORT).show()
        } else if(data.id == 3) {
            startActivity(Intent(this.activity, LaporanSatuActivity::class.java))
        } else if(data.id == 4) {
            Toast.makeText(context, "Laporan KKI II", Toast.LENGTH_SHORT).show()
        }

    }
}