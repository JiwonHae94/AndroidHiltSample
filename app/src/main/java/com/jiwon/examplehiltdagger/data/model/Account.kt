package com.jiwon.examplehiltdagger.data.model

import android.util.Log
import java.lang.StringBuilder

data class Account(val id : String, var name : String, var feature : ArrayList<FacialFeature> = ArrayList()){
    private val TAG = Account::class.java.simpleName

    init{
        Log.d(TAG, "account info : ${id} name : $name, feature cnt : ${feature.size}")
    }

    fun ArrayList<FacialFeature>.toLog() : String{
        val builder = StringBuilder()
        this.forEachIndexed { index, facialFeature ->
            builder.append("\nindex : $index")
            builder.append("\ntimestamp : ${facialFeature.timestamp}")
        }
        return builder.toString()
    }

    internal fun getLastUpdate() : Long{
        return feature.lastUpdatedTime()
    }


    private fun ArrayList<FacialFeature>.lastUpdatedTime() : Long {
        this.maxByOrNull { it.timestamp }?.let{
            return it.timestamp
        } ?: return -1
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder("Account\n")
        stringBuilder.append("id : ", "$id\n")
        stringBuilder.append("name: ", "$name\n")
        stringBuilder.append("feature cnt : ${feature.size}\n")
        stringBuilder.append("features\n", "${feature.toLog()}")
        return stringBuilder.toString()
    }
}