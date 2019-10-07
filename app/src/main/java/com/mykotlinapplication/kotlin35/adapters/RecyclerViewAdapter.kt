package com.mykotlinapplication.kotlin35.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mykotlinapplication.kotlin35.DataHelper
import com.mykotlinapplication.kotlin35.R
import com.mykotlinapplication.kotlin35.models.User
import kotlinx.android.synthetic.main.list_item.view.*



class RecyclerViewAdapter (var items: ArrayList<User>, val context: Context): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.firstname.text = items[position].firstname
        holder.lastname.text = items[position].lastname

        holder.item.setOnClickListener {
            var dataPasser = context as DataHelper
            var bundle = Bundle()
            bundle.putParcelable("user", items[position])
            dataPasser.passDataToUserDetail(bundle)
        }

    }
}

class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
    var firstname = view.textView_displayFirstname
    var lastname = view.textView_displayLastname
    var item = view.list_row

}