package fr.istic.mob.horairebuslr.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BusRoute::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun BusRouteDao(): BusRouteDao
    abstract fun CalendarDao(): CalendarDao
    abstract fun StopDao(): StopDao
    abstract fun StopTimeDao(): StopTimeDao
    abstract fun TripDao(): TripDao
}