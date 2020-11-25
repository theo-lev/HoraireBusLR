package fr.istic.mob.horairebuslr

import androidx.test.core.app.ApplicationProvider
import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import fr.istic.mob.horairebuslr.worker.DownloadWorker
import org.junit.Test
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    private lateinit var applicationContext: Context

    @Before
    fun setup() {
        applicationContext = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun test_dlWorker() {
        val workManager = WorkManager.getInstance(this.applicationContext)
        val downloadRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java)
            .build()
        workManager.enqueue(downloadRequest)
    }
}