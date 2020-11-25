package fr.istic.mob.bus.service

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import fr.istic.mob.bus.R
import fr.istic.mob.bus.data.entity.UrlInformation
import fr.istic.mob.bus.data.local.DataBase
import fr.istic.mob.bus.ui.activity.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class DownloadJsonFile(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        downloadJsonFile()
        return Result.success()
    }

    private fun downloadJsonFile() {
        var input: InputStream? = null
        var output: OutputStream? = null
        var connection: HttpURLConnection? = null
        try {
            val url =
                URL("https://data.explore.star.fr/explore/dataset/tco-busmetro-horaires-gtfs-versions-td/download/?format=json&timezone=Europe/Berlin&lang=fr")
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            input = connection.inputStream
            output = FileOutputStream(File(context.filesDir, "jsonfile"))
            val data = ByteArray(10096)
            var count: Int
            while (input.read(data).also { count = it } != -1) {
                output.write(data, 0, count)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        } finally {
            try {
                output?.close()
                input?.close()
                val file = File(context.filesDir, "jsonfile")
                readJsonFile(file)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            connection?.disconnect()
        }
    }

    private fun readJsonFile(file: File) {
        val fileReader = FileReader(file)
        val bufferedReader = BufferedReader(fileReader)
        val stringBuilder = StringBuilder()
        var line = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line).append("\n")
            line = bufferedReader.readLine()
        }
        bufferedReader.close()
        val response = stringBuilder.toString()

        GlobalScope.launch {
            val url =
                ((JSONArray(response)[0] as JSONObject).get("fields") as JSONObject).get("url")

            val date =
                ((JSONArray(response)[0] as JSONObject).get("fields") as JSONObject).get("publication")
                    .toString()
            val currentUrlInformation =
                DataBase.getDataBaseInstance(context = context).UrlInformationDao().getUrlInformation()
            if (currentUrlInformation == null) {
                createNotification()

                DataBase.getDataBaseInstance(context = context).UrlInformationDao()
                    .insertUrlInformation(UrlInformation(1,url = url.toString(),date = date))
            } else {
                var currentUrlDate = Date()
                var urlDate = Date()
                try {
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    currentUrlDate = sdf.parse(currentUrlInformation.date)
                    urlDate = sdf.parse(date)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
                if (currentUrlDate < urlDate) {
                    createNotification()
                    DataBase.getDataBaseInstance(context = context).UrlInformationDao()
                        .insertUrlInformation(UrlInformation(1,url = url.toString(),date = date))
                }

            }


        }

    }

    private fun createNotification() {
        val notifyIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP

        }
        notifyIntent.putExtra("fromNotification", "fromNotification")
        val notifyPendingIntent = PendingIntent.getActivity(
            context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder = NotificationCompat.Builder(context, "primary_notification_channel").apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(context.getString(R.string.app_name))
            setContentText("New file detected")
            setContentIntent(notifyPendingIntent)
            priority = NotificationCompat.PRIORITY_HIGH
        }
        with(NotificationManagerCompat.from(context)) {
            notify(112, builder.build())
        }
    }
}