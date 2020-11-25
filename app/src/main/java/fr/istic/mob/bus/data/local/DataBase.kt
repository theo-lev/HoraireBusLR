package fr.istic.mob.bus.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.istic.mob.bus.data.entity.*
import fr.istic.mob.bus.data.local.dao.*

@Database(entities = [BusRouteEntity::class, CalendarEntity::class, StopEntity::class, StopTimeEntity::class, TripEntity::class, UrlInformation::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun busRouteDao(): BusRouteDao
    abstract fun tripDao(): TripDao
    abstract fun calendarDao(): CalendarDao
    abstract fun stopDao(): StopDao
    abstract fun stopTimeDao(): StopTimeDao
    abstract fun UrlInformationDao(): UrlInformationDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getDataBaseInstance(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }

        }

        const val DATABASE_NAME = "BUS_TIME"
    }
}