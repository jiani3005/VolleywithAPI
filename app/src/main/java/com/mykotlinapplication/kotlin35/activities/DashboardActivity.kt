package com.mykotlinapplication.kotlin35.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mykotlinapplication.kotlin35.DataHelper
import com.mykotlinapplication.kotlin35.R
import com.mykotlinapplication.kotlin35.adapters.RecyclerViewAdapter
import com.mykotlinapplication.kotlin35.models.User
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONObject

class DashboardActivity : AppCompatActivity(), DataHelper {

    var userList = arrayListOf<User>()

    override fun passDataToUserDetail(bundle: Bundle) {
        var intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra("bundle", bundle)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        addInfo()

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun addInfo() {
        userList.clear()

        val url = "https://apolis-auth.herokuapp.com/api/users"

        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {
                var jsonObject = JSONObject(it)
                var jsonUserArray = jsonObject.getJSONArray("users")

                for (i in 0 until jsonUserArray.length()) {
                    var user = jsonUserArray.getJSONObject(i)

                    var id = user.getString("_id")
                    var firstname = user.getString("firstName")
                    var lastname = user.getString("lastName")
                    var email = user.getString("email")
                    var phone = user.getString("mobile")
                    var password = user.getString("password")

                    userList.add(User(id, firstname, lastname, email, phone, password))

                }

                recyclerView.adapter = RecyclerViewAdapter(userList, this)

            }, Response.ErrorListener {
                Log.e("error", it.toString())
        })

        Volley.newRequestQueue(this).add(request)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_logout -> {
                var sharedPreferencesEditor = getSharedPreferences("Session", Context.MODE_PRIVATE).edit()
                sharedPreferencesEditor.clear().commit()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
