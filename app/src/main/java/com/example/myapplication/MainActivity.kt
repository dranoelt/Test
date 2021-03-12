package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text1 : EditText = findViewById(R.id.editTextTextPersonName)
        val texts = text1.text
        val text2 : EditText = findViewById(R.id.editTextTextPersonName2)
        val email = text2.text
        val text3 : EditText = findViewById(R.id.editTextTextPersonName3)
        val umur = text3.text
        val btn : TextView = findViewById(R.id.textView2)
        btn.setOnClickListener {
            var d = Profil (texts.toString(), email.toString(), umur.toString().toInt())
            var intentAct  = Intent(this, MainActivity2::class.java)
            intentAct.putExtra(EXTRA_KEY,d)
        }
    }
}