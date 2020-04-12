package com.im.kotlin_task.sample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager

class Connectivity {
    companion object {

        /**
         * Get the network info
         *
         * @param context
         * @return
         */
        fun getNetworkInfo(context: Context): NetworkInfo? {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }

        /**
         * Check if there is any connectivity
         *
         * @param context
         * @return
         */
        fun isConnected(context: Context?): Boolean {
            if (context == null)
                return false

            val info = getNetworkInfo(context)
            return info != null && info.isConnected
        }
    }
}