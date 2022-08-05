package com.adit.sibili.fragment.bimbingan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adit.sibili.R
import com.adit.sibili.databinding.FragmentProyekAkhirBinding
import com.adit.sibili.databinding.FragmentSertifikasiBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProyekAkhirFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProyekAkhirFragment : Fragment(), BimbinganMenuAdapter.ItemAdapterCallback {
    private var adapter: BimbinganMenuAdapter? = null
    private var menuArrayList: ArrayList<BimbinganMenuModel> = ArrayList()

    private var _binding : FragmentProyekAkhirBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProyekAkhirBinding.inflate(inflater, container, false)
        val view = binding.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addData()
        adapter = BimbinganMenuAdapter(menuArrayList, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rcList.layoutManager = layoutManager
        binding.rcList.adapter = adapter

    }
    private fun addData() {
        menuArrayList = ArrayList()
        menuArrayList.add(BimbinganMenuModel("Proyek Akhir", "Tugas Akhir", R.drawable.proyekahir_abu, 1))

    }
    override fun onClick(v: View, data: BimbinganMenuModel) {
        if (data.title == "Proyek Akhir") {
            Toast.makeText(context, "pa", Toast.LENGTH_SHORT).show()
        }
    }
}