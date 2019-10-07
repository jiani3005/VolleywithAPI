package com.mykotlinapplication.kotlin35.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.mykotlinapplication.kotlin35.R
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences("Session", Context.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences.edit()

        button_login.setOnClickListener {

            val url = "https://apolis-auth.herokuapp.com/api/auth/login"

            var email = editText_email.text.toString()
            var password = editText_password.text.toString()

            val params = HashMap<String, String>()
            params["email"] = email
            params["password"] = password
            val jsonObject = JSONObject(params as MutableMap<*, *>)

            val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                Response.Listener {
                    try {
                        Log.d("success", it.toString())

                        sharedPreferencesEditor.putString("token", it.getString("token"))
                        var userObject = it.getJSONObject("user")
                        sharedPreferencesEditor.putString("userid", userObject.getString("_id"))
                        sharedPreferencesEditor.commit()

                    } catch (e: Exception) {
                        Log.e("error", e.toString())
                    }

                }, Response.ErrorListener {
                    Log.e("error", it.toString())
                })

            Volley.newRequestQueue(this).add(request)

            startActivity(Intent(this, DashboardActivity::class.java))
        }

    }


}
