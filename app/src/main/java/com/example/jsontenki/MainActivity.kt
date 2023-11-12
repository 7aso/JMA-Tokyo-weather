package com.example.jsontenki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.xml_text_view)
        val client = OkHttpClient()
        val url = "https://www.jma.go.jp/bosai/forecast/data/overview_forecast/130000.json"
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object :Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful){
                    runOnUiThread{
                        textView.text=response.body!!.string()
                    }
                }
            }
        })

    }

}
