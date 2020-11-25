package fr.istic.mob.bus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UrlInformation(
    @PrimaryKey(autoGenerate = true)
    var id: Int, var url: String, var date: String
)
