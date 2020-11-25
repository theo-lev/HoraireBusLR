package fr.istic.mob.horairebuslr.worker

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Worker
import androidx.work.WorkerParameters
import fr.istic.mob.horairebuslr.R
import org.json.JSONException
import org.json.JSONObject
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class DownloadWorker(appContext: Context, workerParams: WorkerParameters):
        Worker(appContext, workerParams) {

    private val currentDate = LocalDateTime.now().toLocalDate()

    private val API_URL = "https://data.explore.star.fr/api/records/1.0/search/?dataset=tco-busmetro-horaires-gtfs-versions-td"

    var pref = appContext.getSharedPreferences("AppPref", Context.MODE_PRIVATE)
    val editor = pref.edit()


    override fun doWork(): Result {

        val pair = getIdUrlFromApi()
        val new_id = pair.first
        val new_url = pair.second

        if (new_id != "" && new_id != pref.getString(R.string.current_data_id.toString(), null)) {
            editor.putString(R.string.current_data_id.toString(), new_id)
            editor.putString(R.string.current_data_url.toString(), new_url)
        }

        

        return Result.success()
    }

    fun getIdUrlFromApi(): Pair<String, String> {
        var id: String = ""
        var url: String = ""

        val txtApi = URL(API_URL).readText()
        val json = parseJson(txtApi)
        if (json != null) {
            val obj1 = json.getJSONObject("records").getJSONObject("0")
            val obj2 = json.getJSONObject("records").getJSONObject("1")

            if (isDatesValide(obj1)) {
                id =  obj1.getString("recordid")
                url = obj1.getJSONObject("fields").getString("url")
            } else if (isDatesValide(obj2)) {
                id =  obj2.getString("recordid")
                url = obj2.getJSONObject("fields").getString("url")
            }
        }
        Log.wtf("WTF", txtApi)
        return Pair(id, url)
    }

    private fun isDatesValide(jsonObject: JSONObject): Boolean {
        val fields = jsonObject.getJSONObject("fields")
        val debutValidite = LocalDate.parse(fields.getString("debutvalidite"), DateTimeFormatter.ISO_DATE)
        val finValidite = LocalDate.parse(fields.getString("finvalidite"), DateTimeFormatter.ISO_DATE)
        return currentDate > debutValidite && currentDate < finValidite
    }

    private fun parseJson(string: String): JSONObject? {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(string)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject
    }


}