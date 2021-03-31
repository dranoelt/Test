package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val text0 : TextView = findViewById(R.id.textView5)
        text0.text = "Welcome to Application"
        var d : Profil? = intent.getParcelableExtra<Profil>(EXTRA_KEY)
        val text1 : TextView = findViewById(R.id.textView2)
        text1.text = "Nama = ${d?.nama}"
        val text2 : TextView = findViewById(R.id.textView3)
        text2.text = "Email = ${d?.email}"
        val text3 : TextView = findViewById(R.id.textView4)
        text3.text = "Umur = ${d?.umur}"
    }
}