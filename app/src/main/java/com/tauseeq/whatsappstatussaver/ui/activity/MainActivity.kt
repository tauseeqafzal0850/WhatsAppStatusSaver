package com.tauseeq.whatsappstatussaver.ui.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.tauseeq.whatsappstatussaver.R
import com.tauseeq.whatsappstatussaver.adapter.SectionPagerAdapter
import com.tauseeq.whatsappstatussaver.databinding.ActivityMainBinding
import com.tauseeq.whatsappstatussaver.ui.fragment.RecentStatusFragment
import com.tauseeq.whatsappstatussaver.ui.fragment.SavedStatusFragment
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import umairayub.madialog.MaDialog

class MainActivity : AppCompatActivity() {

    private lateinit var mSectionsPageAdapter: SectionPagerAdapter
    private  val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mSectionsPageAdapter =
            SectionPagerAdapter(
                supportFragmentManager
            )

        binding.tabs.setupWithViewPager(binding.container)
        if (!isReadStorageAllowed()) {
            checkPermission()
        } else {
            setupViewPager(binding.container)
        }

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = SectionPagerAdapter(
            supportFragmentManager
        )
        adapter.addFragment(RecentStatusFragment(), "Recent Status")
        adapter.addFragment(SavedStatusFragment(), "Saved Status")
        viewPager.adapter = adapter
    }

    private fun isReadStorageAllowed(): Boolean {
        //Getting the permission status
        val result = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        return result == PackageManager.PERMISSION_GRANTED


    }

    private fun checkPermission() {
        val rationale = "Please provide Storage permission to save Status."
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val options = Permissions.Options()
            .setRationaleDialogTitle("Info")
            .setSettingsDialogTitle("Warning")
        Permissions.check(
            this,
            permissions,
            rationale,
            options,
            object : PermissionHandler() {
                override fun onGranted() {
                    // do your task.
                    // Set up the ViewPager with the sections adapter.
                    setupViewPager(binding.container)
                }

                override fun onDenied(
                    context: Context,
                    deniedPermissions: ArrayList<String>
                ) {
                    // permission denied, block the feature.
                    Toast.makeText(
                        this@MainActivity,
                        "We need Storage Permission to Save/Load Status ",
                        Toast.LENGTH_LONG
                    ).show()

                }
            })
    }

}

















