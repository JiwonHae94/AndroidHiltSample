package com.nota.fr_gs25.data.parser

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.jiwon.examplehiltdagger.data.model.FacialFeature
import java.lang.reflect.Type
import java.util.*
import kotlin.jvm.Throws

class FacialFeatureParser : JsonDeserializer<FacialFeature>{
    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): FacialFeature {
        val _json = json.asJsonObject
        return FacialFeature(_json.get("t").asLong, parseFeature(_json.get("d").asString))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseFeature(feature : String) : FloatArray{
        return Base64.getDecoder().decode(feature).toFloatArray().clone()
    }

    private fun ByteArray.toFloatArray() : FloatArray{
        val floatArray = FloatArray(this.size / 4)
        for(i in 0 until this.size - 1 step 4){
            val s1 = this[i+3].toInt() and 0xFF
            val s2 = this[i+2].toInt() and 0xFF
            val s3 = this[i+1].toInt() and 0xFF
            val s4 = this[i].toInt() and 0xFF
            floatArray[i / 4] = ((s1 shl 24) + (s2 shl 16) + (s3 shl 8) + (s4 shl 0)) / 10000000f
        }
        return floatArray
    }

}