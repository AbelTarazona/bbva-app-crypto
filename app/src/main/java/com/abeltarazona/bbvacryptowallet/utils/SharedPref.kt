package com.abeltarazona.bbvacryptowallet.utils

import android.content.Context
import android.content.SharedPreferences
import com.abeltarazona.bbvacryptowallet.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(
    @ApplicationContext context: Context
) {

    companion object {
        private const val PREF_USER = "user"
    }


    private val context: Context = context.applicationContext

    @Volatile
    private var sharedPref: SharedPreferences? = null

    private fun getSharedPerf(): SharedPreferences {
        return sharedPref ?: synchronized(this) {
            context.getSharedPreferences(
                "${BuildConfig.APPLICATION_ID}.main",
                Context.MODE_PRIVATE
            )
        }
    }

    fun reset() {
        getSharedPerf().edit().clear().apply()
    }

    // ----------------------------------------------------------------

    fun setUserID(userID: String) {
        getSharedPerf()
            .edit()
            .apply {
                putString(PREF_USER, userID)
                apply()
            }
    }

    fun getUserID(): String? {
        return getSharedPerf().getString(PREF_USER, null)
    }


}