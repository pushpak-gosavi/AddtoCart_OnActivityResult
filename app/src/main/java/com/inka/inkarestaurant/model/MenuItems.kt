package com.inka.inkarestaurant.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuItems(
    val menuID:Int?=null,
    val menuName:String?=null,
    val menuDetails:String?=null,
    val price:String?=null,
    val night:Boolean?=null,
    val day:Boolean?=null,
    var quantity:Int?=null):Parcelable