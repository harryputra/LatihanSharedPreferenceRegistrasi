package com.putrab13.latihansharedpreferenceregistrasi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var namaTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi TextView untuk menampilkan nama user
        namaTextView = findViewById(R.id.tv_namaMain)

        // Set teks nama dari Preferences
        namaTextView.text = Preferences.getLoggedInUser(this)

        // Tombol Logout: hapus data login dan kembali ke LoginActivity
        findViewById<View>(R.id.button_logoutMain).setOnClickListener {
            Preferences.clearLoggedInUser(this)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
