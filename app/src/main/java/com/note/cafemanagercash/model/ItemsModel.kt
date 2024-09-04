package com.note.cafemanagercash.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemsModel(
    var title: String = "",
    var description: String = "",
    var picUrl: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 0,
    var extra: String = "",
) : Parcelable
