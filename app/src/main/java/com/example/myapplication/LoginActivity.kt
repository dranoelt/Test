package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityLoginBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        var user = binding.email.text
        var pass = binding.password.text

        binding.btnLogin.setOnClickListener {
            if (user.isNotEmpty() && pass.isNotEmpty()) {
                val sdf = SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z")
                val currentDateandTime = sdf.format(Date())
                var output = openFileOutput("Log.txt", Context.MODE_PRIVATE).apply {
                    write("Login Successful, ID : $user - Password : $pass - Time : $currentDateandTime".toByteArray())
                    close()
                }

                if (isExternalStorageReaedable()) {
                    val root = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                    var myDir = File (root?.toURI())
                    if (!myDir.exists()) {
                        myDir.mkdir()
                    }
                    val file = File(myDir,"Log").apply {
                        writeText("Login Successful, ID : $user - Password : $pass - Time : $currentDateandTime")
                    }
                    file.forEachLine(Charsets.UTF_8) {
                        Log.w("Save Data", it)
                    }
                }
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, NextActivity::class.java)
                startActivity(intent)
                binding.email.text.clear()
                binding.password.text.clear()
            }
            else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun isExternalStorageReaedable() : Boolean {
        var state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true
        }
        return false
    }
}