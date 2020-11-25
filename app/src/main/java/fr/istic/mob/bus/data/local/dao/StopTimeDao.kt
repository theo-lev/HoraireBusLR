package fr.istic.mob.bus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.istic.mob.bus.data.entity.StopEntity
import fr.istic.mob.bus.data.entity.StopTimeEntity

@Dao
interface StopTimeDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertStopTime(StopTimeEntity: StopTimeEntity)
    @Query("select * from  stop_time")
    suspend fun getAllStopTime():MutableList<StopTimeEntity>
}