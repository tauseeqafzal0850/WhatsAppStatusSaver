package com.tauseeq.whatsappstatussaver.ui.fragment

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.tauseeq.whatsappstatussaver.adapter.StatusAdapter
import com.tauseeq.whatsappstatussaver.databinding.FragmentRecentStatusBinding
import com.tauseeq.whatsappstatussaver.util.Utils
import java.io.File

class RecentStatusFragment : Fragment() {

    private lateinit var binding:FragmentRecentStatusBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        avedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentStatusBinding.inflate(layoutInflater)

        populateRecyclerView()
        binding.swipeRLayout.setOnRefreshListener {
            // Refresh data on swipe
            populateRecyclerView()
            binding.swipeRLayout.isRefreshing = false
        }
        return  binding.root
    }


    fun populateRecyclerView() {
        val statusDirectory = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            //For API level 29+
        //    File(requireContext().getExternalFilesDir(null), WHATSAPP_STATUSES_LOCATION)
            File(Environment.getExternalStorageDirectory().toString() + File.separator + "Android/media/com.whatsapp/WhatsApp/Media/.Statuses")
        } else {
            // Android 10 and  below 10 (API level 29)
            File(Environment.getExternalStorageDirectory().toString() + File.separator + "WhatsApp/Media/.Statuses")
        }
        binding.recyclerViewRecent.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewRecent.adapter = StatusAdapter(
            requireContext(),
            Utils().getListFiles(statusDirectory), false)
    }

    companion object {
        private const val WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses/"
    }


}