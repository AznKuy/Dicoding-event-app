package com.example.dicodingeventapp.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_event")
@Parcelize
data class FavoriteEvent(
    @PrimaryKey(autoGenerate = false)

    @ColumnInfo(name = "id")
    var id: String = "",
    @ColumnInfo(name = "name")
    var name: String? = "",
    @ColumnInfo(name = "mediaCover")
    var mediaCover: String? = null,
    @ColumnInfo(name = "category")
    var category: String? = "",
    @ColumnInfo(name = "timestamp")
    var timestamp: Long = System.currentTimeMillis()
) : Parcelable