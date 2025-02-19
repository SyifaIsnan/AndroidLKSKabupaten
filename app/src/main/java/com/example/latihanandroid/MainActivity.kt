package com.example.latihanandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.latihanandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(
            binding.toolbarLogin
        )

        binding.button.setOnClickListener{

            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if(username == "admin" && password == "admin"){
                Toast.makeText(this@MainActivity, "Berhasil login!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, HalamanUtama::class.java)
                startActivity(intent)
            }
            else if (username != "admin" || password != "admin")
            {
                Toast.makeText(this@MainActivity, "Username atau password salah!", Toast.LENGTH_SHORT).show()

            }
            else if (username == "" && password == "")
            {
                Toast.makeText(this@MainActivity, "Harap isi username dan password sesuai ketentuan!", Toast.LENGTH_SHORT).show()
            }



        }

    }
}