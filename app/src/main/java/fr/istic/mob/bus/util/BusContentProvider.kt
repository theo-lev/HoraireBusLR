package fr.istic.mob.bus.util

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import fr.istic.mob.bus.data.local.DataBase


class BusContentProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        val content: String = StarContract.AUTHORITY
        URI_MATCHER.addURI(content, StarContract.Trips.CONTENT_PATH, PROVIDER_TRIPS)
        URI_MATCHER.addURI(content, StarContract.Calendar.CONTENT_PATH, PROVIDER_CALENDAR)
        URI_MATCHER.addURI(content, StarContract.Stops.CONTENT_PATH, PROVIDER_STOPS)
        URI_MATCHER.addURI(content, StarContract.StopTimes.CONTENT_PATH, PROVIDER_STOP_TIMES)
        URI_MATCHER.addURI(content, StarContract.BusRoutes.CONTENT_PATH, PROVIDER_ROUTES)
        URI_MATCHER.addURI(content, StarContract.RouteDetails.CONTENT_PATH, PROVIDER_ROUTES_DETAILS)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor: Cursor? = null
        when (URI_MATCHER.match(uri)) {
            PROVIDER_ROUTES -> {
                cursor =
                    DataBase.getDataBaseInstance(context = context!!).busRouteDao()
                        .getRouteListCursor()
            }
            PROVIDER_STOPS -> {
                cursor =
                    DataBase.getDataBaseInstance(context = context!!).stopDao()
                        .getStops(selectionArgs!![0], selectionArgs[1])
            }
            PROVIDER_ROUTES_DETAILS -> {
                cursor =
                    DataBase.getDataBaseInstance(context = context!!).stopTimeDao()
                        .getRouteDetail(selectionArgs!![0], selectionArgs[1])
            }
            PROVIDER_STOP_TIMES -> {
                when (selectionArgs!![3].toLowerCase()) {
                    StarContract.Calendar.CalendarColumns.MONDAY -> {
                        cursor = DataBase.getDataBaseInstance(context = context!!).stopTimeDao()
                            .getStopTimeCursorMonday(
                                selectionArgs[0],
                                selectionArgs[1],
                                selectionArgs[2],
                                selectionArgs[4]
                            )
                    }
                    StarContract.Calendar.CalendarColumns.TUESDAY -> {
                        cursor = DataBase.getDataBaseInstance(context = context!!).stopTimeDao()
                            .getStopTimeCursorTuesday(
                                selectionArgs[0],
                                selectionArgs[1],
                                selectionArgs[2],
                                selectionArgs[4]
                            )
                    }
                    StarContract.Calendar.CalendarColumns.WEDNESDAY -> {
                        cursor = DataBase.getDataBaseInstance(context = context!!).stopTimeDao()
                            .getStopTimeCursorWednesday(
                                selectionArgs[0],
                                selectionArgs[1],
                                selectionArgs[2],
                                selectionArgs[4]
                            )
                    }
                    StarContract.Calendar.CalendarColumns.THURSDAY -> {
                        cursor = DataBase.getDataBaseInstance(context = context!!).stopTimeDao()
                            .getStopTimeCursorThursday(
                                selectionArgs[0],
                                selectionArgs[1],
                                selectionArgs[2],
                                selectionArgs[4]
                            )
                    }
                    StarContract.Calendar.CalendarColumns.FRIDAY -> {
                        cursor = DataBase.getDataBaseInstance(context = context!!).stopTimeDao()
                            .getStopTimeCursorFriday(
                                selectionArgs[0],
                                selectionArgs[1],
                                selectionArgs[2],
                                selectionArgs[4]
                            )
                    }
                    StarContract.Calendar.CalendarColumns.SATURDAY -> {
                        cursor = DataBase.getDataBaseInstance(context = context!!).stopTimeDao()
                            .getStopTimeCursorSaturday(
                                selectionArgs[0],
                                selectionArgs[1],
                                selectionArgs[2],
                                selectionArgs[4]
                            )
                    }
                    StarContract.Calendar.CalendarColumns.SUNDAY -> {
                        cursor = DataBase.getDataBaseInstance(context = context!!).stopTimeDao()
                            .getStopTimeCursorSunday(
                                selectionArgs[0],
                                selectionArgs[1],
                                selectionArgs[2],
                                selectionArgs[4]
                            )
                    }
                }
            }


        }
        return cursor
    }


    override fun getType(uri: Uri): String? {
        var type = ""
        when (URI_MATCHER.match(uri)) {
            PROVIDER_ROUTES -> type = StarContract.BusRoutes.CONTENT_ITEM_TYPE
            PROVIDER_TRIPS -> type = StarContract.Trips.CONTENT_ITEM_TYPE
            PROVIDER_STOPS -> type = StarContract.Stops.CONTENT_ITEM_TYPE
            PROVIDER_STOP_TIMES -> type = StarContract.StopTimes.CONTENT_ITEM_TYPE
            PROVIDER_CALENDAR -> type = StarContract.Calendar.CONTENT_ITEM_TYPE
            PROVIDER_ROUTES_DETAILS -> type = StarContract.RouteDetails.CONTENT_ITEM_TYPE
        }
        return type
    }

    companion object {
        private const val PROVIDER_ROUTES = 100
        private const val PROVIDER_STOPS = 200
        private const val PROVIDER_TRIPS = 300
        private const val PROVIDER_STOP_TIMES = 400
        private const val PROVIDER_CALENDAR = 500
        private const val PROVIDER_ROUTES_DETAILS = 600
        private const val PROVIDER_SEARCHED_STOPS = 700
        private const val PROVIDER_ROUTES_FOR_STOP = 800
        private val URI_MATCHER = UriMatcher(UriMatcher.NO_MATCH)
    }


    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }
}