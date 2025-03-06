package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityHalamanUtamaBinding
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class HalamanUtama : AppCompatActivity() {

    private lateinit var binding: ActivityHalamanUtamaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_halaman_utama)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val foodList = mutableListOf<foodModel>()

        val binding = ActivityHalamanUtamaBinding.inflate(layoutInflater)
        setContentView(binding.root)



        lifecycleScope.launch(Dispatchers.IO){
            val koneksi = URL("https://wahanasuksesbersama.id/lks/produkview.php").openConnection() as HttpURLConnection
            val inputString = koneksi.inputStream.bufferedReader().readText()
            val jsonObject = JSONObject(inputString)
            val jsonArray = jsonObject.getJSONArray("result")
            for(i in 0 until jsonArray.length()){
//                val datajson = jsonArray.getJSONObject(i)
//                foodList(
//                    foodModel(
//                        kd_produk = datajson.getString("kd_produk"),
//                        nama_produk = datajson.getString("nama_produk"),
//                        harga_member = datajson.getString("harga_member"),
//                        gambar = datajson.getString("gambar"),
//                    )
//                )

                val TAG = "home"

//                Log.d(TAG, "onCreate : $inputString")
//                    binding.RecyclerView.layoutManager = GridLayoutManager(this@HalamanUtama, 2)
//                    binding.RecyclerView.adapter = foodAdapter

            }



        }
    }
}