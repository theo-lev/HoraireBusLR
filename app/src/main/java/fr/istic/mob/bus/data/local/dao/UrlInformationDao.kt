package fr.istic.mob.bus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.istic.mob.bus.data.entity.UrlInformation

@Dao
interface UrlInformationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrlInformation(urlInformation: UrlInformation?)

    @Query("select * from UrlInformation")
    suspend fun getUrlInformation(): UrlInformation
}