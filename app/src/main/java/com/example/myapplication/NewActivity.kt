package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log


class NewActivity : AppCompatActivity() {
    private lateinit var logoutBtn: Button;
    @SuppressLint("SimpleDateFormat", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        logoutBtn = findViewById(R.id.logout);
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate = df.format(c)
        Log.d("TAG", "onCreate: $formattedDate")
        val onBoardingScreen: SharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE)
        val selectedDate: String? = onBoardingScreen.getString("Date", "01-01-2023")
        if (!selectedDate.equals(formattedDate)) {
            val editor: SharedPreferences.Editor = onBoardingScreen.edit()
            editor.putString("Date", formattedDate)
            editor.apply()
            CustomDialogFragment.newInstance(
                getString(R.string.label_logout),
                getString(R.string.msg_logout)
            ).show(supportFragmentManager, CustomDialogFragment.TAG)
        }

        logoutBtn.setOnClickListener {
            val login:SharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = onBoardingScreen.edit()
            val editor1: SharedPreferences.Editor = login.edit()
            editor1.clear()
            editor1.apply()
            editor.clear()
            editor.apply()
            startActivity(Intent(this@NewActivity, MainActivity::class.java))
            Toast.makeText(this, "Logout Successful!!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}