package com.mykotlinapplication.kotlin35.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.mykotlinapplication.kotlin35.R
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        button_register.setOnClickListener {

            val url = "https://apolis-auth.herokuapp.com/api/auth/register"

            var firstname = editText_firstname.text.toString()
            var lastname = editText_lastname.text.toString()
            var email = editText_email.text.toString()
            var mobile = editText_phone.text.toString()
            var password = editText_password.text.toString()

            val params = HashMap<String, String>()
            params["firstName"] = firstname
            params["lastName"] = lastname
            params["email"] = email
            params["mobile"] = mobile
            params["password"] = password
            val jsonObject = JSONObject(params as Map<*, *>)

            val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                Response.Listener {
                    try {
                        Log.d("success", it.toString())
                        startActivity(Intent(this, MainActivity::class.java))
                    } catch (e: Exception) {
                        Log.e("error", e.toString())
                    }
                }, Response.ErrorListener {
                    Log.e("error", it.toString())
                })

            Volley.newRequestQueue(this).add(request)
        }
    }
}
