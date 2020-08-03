package cvdevelopers.takehome.utils.preferences

import android.content.Context
import android.content.SharedPreferences

const val ITEM_TYPE_KEY = "listState"
class PreferencesHelper {
    companion object {
        private fun preferences(context: Context): SharedPreferences =
                context.getSharedPreferences("default", 0)

        fun setItem(context: Context, type: Int) {
            preferences(context).edit().putInt(ITEM_TYPE_KEY, type).apply()
        }

        fun getItem(context: Context): Int =
                preferences(context).getInt(ITEM_TYPE_KEY, 0)!!
    }
}