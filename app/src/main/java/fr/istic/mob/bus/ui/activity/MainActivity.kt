package fr.istic.mob.bus.ui.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import fr.istic.mob.bus.R
import fr.istic.mob.bus.data.local.DataBase
import fr.istic.mob.bus.service.DownloadJsonFile
import fr.istic.mob.bus.service.DownloadZipFile
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private lateinit var busSpinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        val periodicWorkRequest = PeriodicWorkRequest.Builder(
            DownloadJsonFile::class.java, 1, TimeUnit.MINUTES
        ).build()
        val mWorkManager = WorkManager.getInstance(application)
        mWorkManager.enqueue(periodicWorkRequest)




        busSpinner = this.findViewById(R.id.spinner_bus)
        val refreshData = this.findViewById<Button>(R.id.refresh_data)
        refreshData.setOnClickListener { v ->
            refreshData()
        }
    }

    private fun refreshData() {
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setTitle("daownload data")
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()
        val arrayList = ArrayList<String>()
        GlobalScope.launch {
            val busList = DataBase.getDataBaseInstance(context = this@MainActivity).busRouteDao()
                .getAllBusRoute()

            busList.forEach {
                arrayList.add(it.routeShortName)
            }

        }
        val busAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList)
        busSpinner.adapter = busAdapter
        progressDialog.dismiss()
    }

    private fun createNotificationChannel() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "primary_notification_channel",
                "Messages",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.enableVibration(false)
            channel.vibrationPattern = longArrayOf(0)
            channel.description = "Messages Notification"
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val extras = intent!!.extras
        val notificationMessage = intent.let {
            extras?.getString("fromNotification", "")

        }
        if (notificationMessage != null) {
            val parsingJsonWorkRequest = OneTimeWorkRequestBuilder<DownloadZipFile>()
                .build()
            val mWorkManager = WorkManager.getInstance(application)
            mWorkManager.enqueue(parsingJsonWorkRequest)
        }

    }
}


