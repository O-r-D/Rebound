package com.ord.rebound.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey val uid: String,
    val username: String?,
    var lastMessage: Message
) : Parcelable