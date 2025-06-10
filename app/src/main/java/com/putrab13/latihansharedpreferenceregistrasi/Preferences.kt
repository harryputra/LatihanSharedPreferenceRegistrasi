package com.putrab13.latihansharedpreferenceregistrasi

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Object untuk mengelola SharedPreferences aplikasi.
 * Menyimpan data registrasi dan status login user.
 */
object Preferences {

    private const val KEY_USER_REGISTERED = "user"
    private const val KEY_PASS_REGISTERED = "pass"
    private const val KEY_CURRENT_USER = "Username_logged_in"
    private const val KEY_LOGIN_STATUS = "Status_logged_in"

    /**
     * Mendapatkan SharedPreferences default aplikasi.
     */
    private fun prefs(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * Simpan username terdaftar.
     */
    fun setRegisteredUser(context: Context, username: String) {
        prefs(context).edit()
            .putString(KEY_USER_REGISTERED, username)
            .apply()
    }

    /**
     * Ambil username yang telah terdaftar.
     */
    fun getRegisteredUser(context: Context): String =
        prefs(context).getString(KEY_USER_REGISTERED, "") ?: ""

    /**
     * Simpan password terdaftar.
     */
    fun setRegisteredPass(context: Context, password: String) {
        prefs(context).edit()
            .putString(KEY_PASS_REGISTERED, password)
            .apply()
    }

    /**
     * Ambil password yang telah terdaftar.
     */
    fun getRegisteredPass(context: Context): String =
        prefs(context).getString(KEY_PASS_REGISTERED, "") ?: ""

    /**
     * Simpan username yang sedang login.
     */
    fun setLoggedInUser(context: Context, username: String) {
        prefs(context).edit()
            .putString(KEY_CURRENT_USER, username)
            .apply()
    }

    /**
     * Ambil username yang sedang login.
     */
    fun getLoggedInUser(context: Context): String =
        prefs(context).getString(KEY_CURRENT_USER, "") ?: ""

    /**
     * Simpan status login (true/false).
     */
    fun setLoggedInStatus(context: Context, status: Boolean) {
        prefs(context).edit()
            .putBoolean(KEY_LOGIN_STATUS, status)
            .apply()
    }

    /**
     * Ambil status login saat ini.
     */
    fun getLoggedInStatus(context: Context): Boolean =
        prefs(context).getBoolean(KEY_LOGIN_STATUS, false)

    /**
     * Hapus data login (username dan status) sehingga kembali ke default.
     */
    fun clearLoggedInUser(context: Context) {
        prefs(context).edit()
            .remove(KEY_CURRENT_USER)
            .remove(KEY_LOGIN_STATUS)
            .apply()
    }
}
