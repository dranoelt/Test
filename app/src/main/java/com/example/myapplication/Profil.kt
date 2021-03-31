package com.example.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profil(var nama: String = "No-Name",
                  var email: String = "example@gmail.com",
                  var umur: String = "0") : Parcelable {
                  }
