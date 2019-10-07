package com.mykotlinapplication.kotlin35.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mykotlinapplication.kotlin35.R
import com.mykotlinapplication.kotlin35.models.User
import kotlinx.android.synthetic.main.activity_user_detail.*
import org.json.JSONObject
import java.lang.Exception

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        var bundle = intent.getBundleExtra("bundle")
        var user = bundle?.getParcelable<User>("user")

        textView_firstname.text = user?.firstname
        textView_lastname.text = user?.lastname
        textView_email.text = user?.email
        textView_mobile.text = user?.mobile

//        Log.d("user", user.toString())

        button_edit.setOnClickListener {

            var intent = Intent(this, EditActivity::class.java)
//            var bundle = Bundle()
//            bundle.putParcelable("user", user)
            intent.putExtra("bundle", bundle)

            startActivity(intent)
        }

        button_delete.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Delete Entry")
            alertDialog.setMessage("Are you sure you want to delete this entry?")
            alertDialog.setNegativeButton("No") { dialog, which ->  dialog.cancel()}
            alertDialog.setPositiveButton("Yes") { dialog, which ->
                val url = "https://apolis-auth.herokuapp.com/api/users/" + user!!.id

                val request = StringRequest(Request.Method.DELETE, url,
                    Response.Listener {
                        try {
                            Toast.makeText(this, "Delete successfully!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, DashboardActivity::class.java))
//                            Log.d("success", it.toString())
                        } catch (e: Exception) {
                            Log.e("error", e.toString())
                        }
                    },
                    Response.ErrorListener {
                        Log.e("error", it.toString())
                    })
                Volley.newRequestQueue(this).add(request)
            }
            alertDialog.show()
        }

    }
}
