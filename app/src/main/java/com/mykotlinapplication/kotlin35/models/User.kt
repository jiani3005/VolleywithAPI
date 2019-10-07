package com.mykotlinapplication.kotlin35.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val id: String, val firstname: String, val lastname: String, val email: String, val mobile:String, val password: String): Parcelable