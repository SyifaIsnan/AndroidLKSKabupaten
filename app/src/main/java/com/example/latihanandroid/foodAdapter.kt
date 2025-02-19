package com.example.latihanandroid

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.HttpURLConnection
import java.net.URL

class foodAdapter(val context: Context, val foodList: MutableList<foodModel>): RecyclerView.Adapter<foodAdapter.ViewHolder>(){

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val nama_produk =view.findViewById<TextView>(R.id.nama_produk)
        val satuan = view.findViewById<TextView>(R.id.satuan)
        val harga_member = view.findViewById<TextView>(R.id.harga_member)
        val gambar = view.findViewById<ImageView>(R.id.gambar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foodList[position]
        updateUI(holder, food)
    }

    private fun updateUI(holder: ViewHolder, food: foodModel) = runBlocking {
        launch(Dispatchers.IO){
            val koneksi = URL(food.gambar).openConnection() as HttpURLConnection
            val stream = koneksi.inputStream
            val gambar = BitmapFactory.decodeStream(stream)
            (context as Activity).runOnUiThread {
                val resources = context.resources
                holder.gambar.setImageBitmap(gambar)
                holder.nama_produk.text = food.nama_produk
                holder.satuan.text = food.satuan
                holder.harga_member.text = food.harga_member

            }
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}