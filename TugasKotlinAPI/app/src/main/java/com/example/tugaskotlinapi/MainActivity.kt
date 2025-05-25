package com.example.tugaskotlinapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var txtOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtOutput = findViewById(R.id.txtOutput)

        val url = "https://seasil.free.beeceptor.com/mydata"

        val queue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val nama = response.getString("nama")
                val nim = response.getString("nim")
                txtOutput.text = "Nama: $nama\nNIM: $nim"
            },
            { error ->
                txtOutput.text = "Gagal ambil data: ${error.message}"
            })

        queue.add(request)
    }
}
