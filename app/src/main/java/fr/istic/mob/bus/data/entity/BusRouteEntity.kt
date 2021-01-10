package fr.istic.mob.bus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Bus route entity.
 **/
@Entity(tableName = "bus_route")
data class BusRouteEntity(
    @PrimaryKey
    val routeId: String,
    val routeShortName: String,
    val routeLongName: String,
    val routeDesc: String,
    val routeType: String,
    val routeColor: String

) {
    companion object{
        fun parseBusRouteObject(csvBusRoute: Map<String, String>): BusRouteEntity {
            return BusRouteEntity(
                routeId = csvBusRoute["route_id"] ?: error(""),
                routeShortName = csvBusRoute["route_short_name"]?: error(""),
                routeLongName = csvBusRoute["route_long_name"]?: error(""),
                routeDesc = csvBusRoute["route_desc"]?: error(""),
                routeType = csvBusRoute["route_type"]?: error(""),
                routeColor = csvBusRoute["route_color"]?: error(""))
        }
    }

}