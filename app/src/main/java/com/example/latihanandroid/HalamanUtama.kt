package com.example.latihanandroid

import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Grid
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.latihanandroid.databinding.ActivityHalamanUtamaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        binding = ActivityHalamanUtamaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodList = mutableListOf<foodModel>()

        binding.progressBar.isVisible = true

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val koneksi = URL("https://wahanasuksesbersama.id/lks/produkview.php").openConnection() as HttpURLConnection
                val inputString = koneksi.inputStream.bufferedReader().readText()
                val jsonObject = JSONObject(inputString)
                val jsonArray = jsonObject.getJSONArray("result")
                for(i in 0 until jsonArray.length()){
                    val jsonfoodlist = jsonArray.getJSONObject(i)
                    foodList.add(
                        foodModel(
                            kd_produk = jsonfoodlist.getString("kd_produk"),
                            nama_produk = jsonfoodlist.getString("nama_produk"),
                            satuan = jsonfoodlist.getString("satuan"),
                            harga_member = jsonfoodlist.getString("harga_member"),
                            gambar = jsonfoodlist.getString("gambar")


                        )
                    )

                    val TAG = "home"
                    Log.d(TAG, "onCreate: $inputString")
                    withContext(Dispatchers.Main){
                        binding.RecyclerView.layoutManager = GridLayoutManager(this@HalamanUtama, 2)
                        binding.RecyclerView.adapter = foodAdapter(this@HalamanUtama, foodList)
                        binding.progressBar.isVisible = false
                    }
                }
            }
            catch(e: Exception){
                Toast.makeText(this@HalamanUtama, "Gagal menghubungkan!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}