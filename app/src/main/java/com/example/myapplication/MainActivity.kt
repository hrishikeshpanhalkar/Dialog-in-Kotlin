package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var username:TextInputEditText;
    private lateinit var password:TextInputEditText;
    private lateinit var btnLogin:Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btnLogin)

        val login:SharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        val isLogin: Boolean = login.getBoolean("isLogin", false)
        if(isLogin){
            startActivity(Intent(this@MainActivity, NewActivity::class.java))
            finish()
        }
        btnLogin.setOnClickListener {
            val editor: SharedPreferences.Editor = login.edit()
            editor.putBoolean("isLogin", true)
            editor.apply()
            startActivity(Intent(this@MainActivity, NewActivity::class.java))
            finish()
        }
    }
}