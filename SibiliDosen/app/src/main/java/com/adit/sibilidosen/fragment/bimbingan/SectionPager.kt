package com.adit.sibilidosen.fragment.bimbingan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionPager(fm : FragmentManager?, private val number_tabs: Int) : FragmentPagerAdapter(fm!!) {
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "KKI"
            1 -> "Sertifikasi"
            2 -> "Proyek Akhir"
            else -> null
        }
    }

    override fun getCount(): Int {
        return number_tabs
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> KkiFragment()
            1 -> SertifikasiFragment()
            else -> ProyekAkhirFragment()
        }
    }
}