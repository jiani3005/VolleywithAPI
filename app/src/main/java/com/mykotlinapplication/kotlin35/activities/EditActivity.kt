package com.mykotlinapplication.kotlin35.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.mykotlinapplication.kotlin35.R
import com.mykotlinapplication.kotlin35.models.User
import kotlinx.android.synthetic.main.activity_edit.*
import org.json.JSONObject

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        var bundle = intent.getBundleExtra("bundle")
        var user: User? = bundle?.getParcelable("user")

        editText_firstname.setText(user?.firstname)
        editText_lastname.setText(user?.lastname)
        editText_email.setText(user?.email)
        editText_mobile.setText(user?.mobile)
        editText_password.setText(user?.password)

        button_confirmEdit.setOnClickListener {
            val url = "https://apolis-auth.herokuapp.com/api/users/" + user!!.id

            var params = HashMap<String, String>()
            params["firstName"] = user.firstname
            params["lastName"] = user.lastname
            params["email"] = user.email
            params["password"] = editText_password.text.toString()
            var updateJsonObj = JSONObject(params as Map<*, *>)

            var request = JsonObjectRequest(Request.Method.PUT, url, updateJsonObj,
                Response.Listener {
                    try {
                        Toast.makeText(this, "Update successfully!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, DashboardActivity::class.java))
//                        Log.d("success", it.toString())
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
