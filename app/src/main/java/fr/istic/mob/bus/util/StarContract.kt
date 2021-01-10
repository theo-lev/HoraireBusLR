package fr.istic.mob.bus.util

import android.net.Uri
import android.provider.BaseColumns

interface StarContract {
    interface BusRoutes {
        interface BusRouteColumns : BaseColumns {
            companion object {
                const val ID = "routeId"
                const val SHORT_NAME = "routeShortName"
                const val LONG_NAME = "routeLongName"
                const val DESCRIPTION = "routeDesc"
                const val TYPE = "routeType"
                const val COLOR = "routeColor"
                const val TEXT_COLOR = "route_text_color"
            }
        }

        companion object {
            const val CONTENT_PATH = "bus_route"
            val CONTENT_URI = Uri.withAppendedPath(
                AUTHORITY_URI,
                CONTENT_PATH
            )
            const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.fr.istic.mob.bus.bus_route"
            const val CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.fr.istic.mob.bus.bus_route"
        }
    }

    interface Trips {
        interface TripColumns : BaseColumns {
            companion object {
                const val ID = "tripEntityId"
                const val ROUTE_ID = "routeId"
                const val SERVICE_ID = "serviceId"
                const val HEADSIGN = "tripHeadSign"
                const val DIRECTION_ID = "directionId"
                const val BLOCK_ID = "BlockId"
                const val WHEELCHAIR_ACCESSIBLE = "wheelchairAccessible"
            }
        }

        companion object {
            const val CONTENT_PATH = "trip"
            val CONTENT_URI = Uri.withAppendedPath(
                AUTHORITY_URI,
                CONTENT_PATH
            )
            const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.fr.istic.mob.bus.trip"
            const val CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.fr.istic.mob.bus.trip"
        }
    }

    interface Stops {
        interface StopColumns : BaseColumns {
            companion object {
                const val ID = "stopId"
                const val NAME = "stopName"
                const val DESCRIPTION = "stopDesc"
                const val LATITUDE = "stopLat"
                const val LONGITUDE = "stopLon"
                const val WHEELCHAIR_BOARDING = "wheelchairBoarding"
            }
        }

        companion object {
            const val CONTENT_PATH = "stop"
            val CONTENT_URI = Uri.withAppendedPath(
                AUTHORITY_URI,
                CONTENT_PATH
            )
            const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.fr.istic.mob.bus.stop"
            const val CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.fr.istic.mob.bus.stop"
        }
    }

    interface StopTimes {
        interface StopTimeColumns : BaseColumns {
            companion object {
                const val TRIP_ID = "tripId"
                const val ID = "id"
                const val ARRIVAL_TIME = "arrivalTime"
                const val DEPARTURE_TIME = "departureTime"
                const val STOP_ID = "stopId"
                const val STOP_SEQUENCE = "stopSequence"
            }
        }

        companion object {
            const val CONTENT_PATH = "stop_time"
            val CONTENT_URI = Uri.withAppendedPath(
                AUTHORITY_URI,
                CONTENT_PATH
            )

            // select stop_time.*, trip.*, calendar.*
            const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.fr.istic.mob.bus.stop_time"
            const val CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.fr.istic.mob.bus.stop_time"
        }
    }

    interface Calendar {
        interface CalendarColumns : BaseColumns {
            companion object {
                const val ID = "serviceId"
                const val MONDAY = "monday"
                const val TUESDAY = "tuesday"
                const val WEDNESDAY = "wednesday"
                const val THURSDAY = "thursday"
                const val FRIDAY = "friday"
                const val SATURDAY = "saturday"
                const val SUNDAY = "sunday"
                const val START_DATE = "startDate"
                const val END_DATE = "endDate"
            }
        }

        companion object {
            const val CONTENT_PATH = "calendar"
            val CONTENT_URI = Uri.withAppendedPath(
                AUTHORITY_URI,
                CONTENT_PATH
            )
            const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.fr.istic.mob.bus.calendar"
            const val CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.fr.istic.mob.bus.calendar"
        }
    }

    interface RouteDetails {
        companion object {
            const val CONTENT_PATH = "routedetail"
            val CONTENT_URI = Uri.withAppendedPath(
                AUTHORITY_URI,
                CONTENT_PATH
            )

            const val CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.fr.istic.mob.bus.routedetail"
            const val CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.fr.istic.mob.bus.routedetail"
        }
    }

    companion object {
        const val AUTHORITY = "fr.istic.mob.bus"
        val AUTHORITY_URI =
            Uri.parse("content://$AUTHORITY")
    }
}