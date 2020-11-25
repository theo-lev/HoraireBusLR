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
    val agencyId: String,
    val routeShortName: String,
    val routeLongName: String,
    val routeDesc: String,
    val routeType: String,
    val routeUrl: String,
    val routeColor: String,
    val routeTextColor: String,
    val routeSortOrder: String
) {
    companion object{
        fun parseBusRouteObject(csvBusRoute: Map<String, String>): BusRouteEntity {
            return BusRouteEntity(
                routeId = csvBusRoute["route_id"] ?: error(""),
                agencyId = csvBusRoute["agency_id"]?: error(""),
                routeShortName = csvBusRoute["route_short_name"]?: error(""),
                routeLongName = csvBusRoute["route_long_name"]?: error(""),
                routeDesc = csvBusRoute["route_desc"]?: error(""),
                routeType = csvBusRoute["route_type"]?: error(""),
                routeUrl = csvBusRoute["route_url"]?: error(""),
                routeColor = csvBusRoute["route_color"]?: error(""),
                routeTextColor = csvBusRoute["route_text_color"]?: error(""),
                routeSortOrder = csvBusRoute["route_sort_order"]?: error("")
            )
        }
    }

}