package cvdevelopers.takehome.utils.preferences

import android.content.Context
import android.content.SharedPreferences

const val ITEM_SELECTED = "listState"
class PreferencesHelper {
    companion object {
        private fun preferences(context: Context): SharedPreferences =
                context.getSharedPreferences("default", 0)

        fun setItem(context: Context, type: Int) {
            preferences(context).edit().putInt(ITEM_SELECTED, type).apply()
        }

        fun getItem(context: Context): Int =
                preferences(context).getInt(ITEM_SELECTED, 0)!!
    }
}