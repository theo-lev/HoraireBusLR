package fr.istic.mob.horairebuslr.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BusRoute::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun BusRouteDao(): BusRouteDao
}