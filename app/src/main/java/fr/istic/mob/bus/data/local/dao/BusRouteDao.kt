package fr.istic.mob.bus.data.local.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.istic.mob.bus.data.entity.BusRouteEntity

@Dao
interface BusRouteDao {
    @Query("delete from bus_route")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBusRoute(busRout: BusRouteEntity)

    @Query("select * from bus_route")
    suspend fun getAllBusRoute(): MutableList<BusRouteEntity>

    @Query("select * from bus_route order by routeId")
    fun getRouteListCursor(): Cursor?


}