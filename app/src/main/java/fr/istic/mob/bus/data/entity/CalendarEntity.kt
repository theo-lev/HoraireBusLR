package fr.istic.mob.bus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Calendar entity
 * */
@Entity(tableName = "calendar")
data class CalendarEntity(
    @PrimaryKey
    val serviceId: String,
    val monday: String,
    val tuesday: String,
    val wednesday: String,
    val thursday: String,
    val friday: String,
    val saturday: String,
    val sunday: String,
    val startDate: String,
    val endDate: String
) {
    companion object {
        fun parseCalendarObject(csvCalendar: Map<String, String>): CalendarEntity {
            return CalendarEntity(
                serviceId = csvCalendar["service_id"] ?: error(""),
                monday = csvCalendar["monday"] ?: error(""),
                tuesday = csvCalendar["tuesday"] ?: error(""),
                wednesday = csvCalendar["wednesday"] ?: error(""),
                thursday = csvCalendar["thursday"] ?: error(""),
                friday = csvCalendar["friday"] ?: error(""),
                saturday = csvCalendar["saturday"] ?: error(""),
                sunday = csvCalendar["sunday"] ?: error(""),
                startDate = csvCalendar["start_date"] ?: error(""),
                endDate = csvCalendar["end_date"] ?: error("")
            )
        }

    }
}