package com.mykotlinapplication.kotlin35.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mykotlinapplication.kotlin35.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_goToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        button_goToLogin.setOnClickListener {
            var token = getSharedPreferences("Session", Context.MODE_PRIVATE).getString("token", "")

            if (token != "") {
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}
