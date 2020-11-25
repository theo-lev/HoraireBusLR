package fr.istic.mob.bus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.istic.mob.bus.data.entity.StopEntity

@Dao
interface StopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStop(stopEntity: StopEntity)

    @Query("select * from stop")
    suspend fun getAllStop(): MutableList<StopEntity>
}