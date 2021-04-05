package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

const val EXTRA_OPEN = "OPEN"
class MyReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent) {
        if (p1?.action == EXTRA_OPEN)
            Log.w("Broadcast", "Message : YES")
    }
}