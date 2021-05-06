package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(var nama: String = "-", var lama: String ="-") : Parcelable {

}