package fr.istic.mob.bus.service

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.opencsv.CSVReaderHeaderAware
import fr.istic.mob.bus.R
import fr.istic.mob.bus.data.entity.*
import fr.istic.mob.bus.data.local.DataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


class DownloadZipFile(private val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    private lateinit var builder: NotificationCompat.Builder
    override suspend fun doWork(): Result {
        builder = NotificationCompat.Builder(context, "primary_notification_channel").apply {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle(context.getString(R.string.app_name))
            setContentText("DownloadZipFile")
            priority = NotificationCompat.PRIORITY_DEFAULT
        }
        with(NotificationManagerCompat.from(context)) {
            builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, true)
            notify(112, builder.build())
        }
        Log.d("DownloadZipFileWorker", "i am in DownloadZipFile")
        GlobalScope.launch(Dispatchers.IO) {
            downloadZipFile(
                DataBase.getDataBaseInstance(context = context).UrlInformationDao()
                    .getUrlInformation().url
            )
        }
        return Result.success()
    }

    private fun downloadZipFile(urlStr: String?) {
        var input: InputStream? = null
        var output: OutputStream? = null
        var connection: HttpURLConnection? = null
        try {
            val url = URL(urlStr)
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            input = connection.inputStream
            output = FileOutputStream(File(context.filesDir, "bus"))
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
                Result.success()
                output?.close()
                input?.close()
                val f = File(context.filesDir, "bus")
                extractZip(f.path)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            connection?.disconnect()
        }
    }

    private fun extractZip(filePath: String): Boolean {
        val zis: ZipInputStream?
        try {
            val zipFile = File(filePath)
            val parentFolder = zipFile.parentFile?.path
            var filename: String?
            val fileInputStream = FileInputStream(filePath)
            zis = ZipInputStream(BufferedInputStream(fileInputStream))
            var ze: ZipEntry?
            while (zis.nextEntry.also { ze = it } != null) {
                filename = ze?.name
                FileOutputStream(File(parentFolder, filename.toString())).use { file ->
                    val buffer = ByteArray(8192)
                    var len = zis.read(buffer)
                    while (len != -1) {
                        file.write(buffer, 0, len)
                        len = zis.read(buffer)
                    }
                    zis.closeEntry()
                }
                when (filename) {
                    "routes.txt", "trips.txt", "stops.txt", "stop_times.txt", "calendar.txt" -> {
                        Log.d("filname :---", filename)
                        readCsvFile("$parentFolder/$filename", filename)
                    }
                }
            }
            zis.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } finally {
            with(NotificationManagerCompat.from(context)) {
                builder.setContentText("Download complete")
                    .setProgress(0, 0, false)
                notify(112, builder.build())
            }

            Log.d("loading file", "finished")
        }
        return true
    }

    private fun changeNotificationText(notificationText: String) {
        with(NotificationManagerCompat.from(context)) {
            builder.setContentText("Download $notificationText file")
                .setProgress(PROGRESS_MAX, PROGRESS_CURRENT, true)
            notify(112, builder.build())
        }
    }

    private fun readCsvFile(filePath: String, fileName: String?) {
        val reader = CSVReaderHeaderAware(FileReader(filePath))
        val resultList = mutableListOf<Map<String, String>>()
        var line = reader.readMap()
        while (line != null) {
            resultList.add(line)
            line = reader.readMap()
        }
        when (fileName) {
            "routes.txt" -> {
                changeNotificationText("routes.txt")
                resultList.forEach { map: Map<String, String> ->
                    GlobalScope.launch {
                        val busRouteEntity = BusRouteEntity.parseBusRouteObject(map)
                        DataBase.getDataBaseInstance(context = context).busRouteDao()
                            .insertBusRoute(busRouteEntity)
                        Log.d("----------", "i am in routes")
                        Log.d("size", resultList.size.toString())
                    }
                }
            }
            "trips.txt" -> {
                changeNotificationText("trips.txt")
                resultList.forEach { map: Map<String, String> ->
                    GlobalScope.launch {
                        val tripEntity = TripEntity.parseTripObject(map)
                        DataBase.getDataBaseInstance(context = context).tripDao()
                            .insertTrip(tripEntity)
                        Log.d("size", resultList.size.toString())
                    }
                }
            }
            "stoaps.txt" -> {
                changeNotificationText("stops.txt")
                resultList.forEach { map: Map<String, String> ->
                    GlobalScope.launch {
                        val stopEntity = StopEntity.parseStopObject(map)
                        DataBase.getDataBaseInstance(context = context).stopDao()
                            .insertStop(stopEntity)
                        Log.d("----------", "stops.txt")
                        Log.d("size", resultList.size.toString())
                    }
                }
            }
            "stoap_times.txt" -> {
                Log.d("stop_times----", "i am in csv stop_times with size ${resultList.size}")

                changeNotificationText("stop_times.txt")
                resultList.forEachIndexed { index, map: Map<String, String> ->
                    GlobalScope.launch {
                        val stopTimesEntity = StopTimeEntity.parseStopTimeObject(map)
                        DataBase.getDataBaseInstance(context = context).stopTimeDao()
                            .insertStopTime(stopTimesEntity)
                        Log.d("----------", "i am in $index")
                    }
                }
            }
            "calendar.txt" -> {
                changeNotificationText("calendar.txt")
                resultList.forEach { map: Map<String, String> ->
                    GlobalScope.launch {
                        val calendarEntity = CalendarEntity.parseCalendarObject(map)
                        DataBase.getDataBaseInstance(context = context).calendarDao()
                            .insertCalendar(calendarEntity)
                        Log.d("----------", "calendar.txt")
                        Log.d("size", resultList.size.toString())
                    }
                }
            }
        }
    }

    companion object {
        const val PROGRESS_MAX = 100
        const val PROGRESS_CURRENT = 0
    }

}