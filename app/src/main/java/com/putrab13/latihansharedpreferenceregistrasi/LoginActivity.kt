package com.putrab13.latihansharedpreferenceregistrasi

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Activity untuk login user.
 * Memvalidasi input username dan password, lalu navigasi ke MainActivity jika valid.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var viewUser: EditText
    private lateinit var viewPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi EditText
        viewUser = findViewById(R.id.et_emailSignin)
        viewPassword = findViewById(R.id.et_passwordSignin)

        // Tangani tombol "Done" pada keyboard password
        viewPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                performLoginCheck()
                true
            } else {
                false
            }
        }

        // Tombol Sign In
        findViewById<View>(R.id.button_signinSignin).setOnClickListener {
            performLoginCheck()
        }

        // Tombol Sign Up → RegisterActivity
        findViewById<View>(R.id.button_signupSignin).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        // Jika sudah login, langsung ke MainActivity
        if (Preferences.getLoggedInStatus(this)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    /**
     * Validasi input, tampilkan error jika tidak valid.
     */
    private fun performLoginCheck() {
        // Reset error
        viewUser.error = null
        viewPassword.error = null

        var focusView: View? = null
        var cancel = false

        val user = viewUser.text.toString()
        val password = viewPassword.text.toString()

        // Validasi username
        if (TextUtils.isEmpty(user)) {
            viewUser.error = "This field is required"
            focusView = viewUser
            cancel = true
        } else if (!isUserValid(user)) {
            viewUser.error = "This Username is not found"
            focusView = viewUser
            cancel = true
        }

        // Validasi password
        if (TextUtils.isEmpty(password)) {
            viewPassword.error = "This field is required"
            focusView = viewPassword
            cancel = true
        } else if (!isPasswordValid(password)) {
            viewPassword.error = "This password is incorrect"
            focusView = viewPassword
            cancel = true
        }

        if (cancel) {
            focusView?.requestFocus()
        } else {
            signIn()
        }
    }

    /**
     * Simpan status login dan navigasi ke MainActivity.
     */
    private fun signIn() {
        val registeredUser = Preferences.getRegisteredUser(this)
        Preferences.setLoggedInUser(this, registeredUser)
        Preferences.setLoggedInStatus(this, true)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /**
     * Cek validitas password.
     */
    private fun isPasswordValid(password: String): Boolean =
        password == Preferences.getRegisteredPass(this)

    /**
     * Cek validitas username.
     */
    private fun isUserValid(user: String): Boolean =
        user == Preferences.getRegisteredUser(this)
}
