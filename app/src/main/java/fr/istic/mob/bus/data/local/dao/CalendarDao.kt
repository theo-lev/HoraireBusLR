package fr.istic.mob.bus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.istic.mob.bus.data.entity.CalendarEntity

@Dao
interface CalendarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalendar(calendarEntity: CalendarEntity)

    @Query("select *  from calendar")
    suspend fun getAllCalendar(): MutableList<CalendarEntity>

}