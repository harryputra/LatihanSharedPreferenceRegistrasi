package com.putrab13.latihansharedpreferenceregistrasi

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
 * Activity untuk pendaftaran user baru.
 * Validasi username dan password, lalu simpan ke SharedPreferences.
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var viewUser: EditText
    private lateinit var viewPassword: EditText
    private lateinit var viewRepassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi form input
        viewUser = findViewById(R.id.et_emailSignup)
        viewPassword = findViewById(R.id.et_passwordSignup)
        viewRepassword = findViewById(R.id.et_passwordSignup2)

        // Tangani aksi "Done" pada keyboard untuk field repassword
        viewRepassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                validateAndRegister()
                true
            } else {
                false
            }
        }

        // Tangani klik tombol Sign Up
        findViewById<View>(R.id.button_signupSignup).setOnClickListener {
            validateAndRegister()
        }
    }

    /**
     * Validasi input dan simpan data saat valid.
     */
    private fun validateAndRegister() {
        // Reset error
        viewUser.error = null
        viewPassword.error = null
        viewRepassword.error = null

        var focusView: View? = null
        var cancel = false

        val user = viewUser.text.toString()
        val password = viewPassword.text.toString()
        val repassword = viewRepassword.text.toString()

        // Validasi username
        if (TextUtils.isEmpty(user)) {
            viewUser.error = "This field is required"
            focusView = viewUser
            cancel = true
        } else if (isUserExisting(user)) {
            viewUser.error = "This Username is already exist"
            focusView = viewUser
            cancel = true
        }

        // Validasi password
        if (TextUtils.isEmpty(password)) {
            viewPassword.error = "This field is required"
            focusView = viewPassword
            cancel = true
        } else if (!isPasswordMatching(password, repassword)) {
            viewRepassword.error = "This password is incorrect"
            focusView = viewRepassword
            cancel = true
        }

        if (cancel) {
            focusView?.requestFocus()
        } else {
            // Simpan ke Preferences
            Preferences.setRegisteredUser(this, user)
            Preferences.setRegisteredPass(this, password)
            finish()
        }
    }

    /**
     * Periksa apakah password dan repassword sama.
     */
    private fun isPasswordMatching(password: String, repassword: String): Boolean =
        password == repassword

    /**
     * Periksa apakah username sudah terdaftar.
     */
    private fun isUserExisting(user: String): Boolean =
        user == Preferences.getRegisteredUser(this)
}
