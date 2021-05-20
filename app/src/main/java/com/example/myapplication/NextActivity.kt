package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityNextBinding
import java.io.FileNotFoundException
import java.io.IOException

class NextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_next)
        show()

        binding.btnLogout.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun show() {
        try {
            var input = openFileInput("Log.txt").bufferedReader().useLines {
                for (text in it.toList()) {
                    binding.showText.setText("$text")
                }
            }
        }catch (e: FileNotFoundException) {
            binding.showText.setText("File Not Found")
        }catch (e: IOException) {
            binding.showText.setText("File Cant be Read")
        }
    }
}