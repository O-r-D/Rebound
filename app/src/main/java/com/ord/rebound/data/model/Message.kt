package com.ord.rebound.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Message(
    val uid: String,
    val content: String?,
    val date: Date?,
    val userUID: String
) : Parcelable