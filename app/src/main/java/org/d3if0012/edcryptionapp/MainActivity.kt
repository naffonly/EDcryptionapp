package org.d3if0012.edcryptionapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController

    companion object {
        const val CHANNEL_ID = "notif"
        const val PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nameUpdater = getString(R.string.channel_name)
            val nameGetting = getString(R.string.channel_name_getStart)
            val importance = NotificationManager.IMPORTANCE_DEFAULT


            val channelUpdater = NotificationChannel(CHANNEL_ID, nameUpdater, importance)
            channelUpdater.description = getString(R.string.channel_desc)
            val channelGetting = NotificationChannel(CHANNEL_ID, nameGetting, importance)
            channelUpdater.description = getString(R.string.channel_desc_getStart)

            val manager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager?

            manager?.createNotificationChannel(channelGetting)
            manager?.createNotificationChannel(channelUpdater)
        }

        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }





}