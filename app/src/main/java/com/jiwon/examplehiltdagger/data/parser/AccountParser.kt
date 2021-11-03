package com.nota.fr_gs25.data.parser

import android.util.Log
import com.google.gson.*
import com.jiwon.examplehiltdagger.data.model.Account
import com.jiwon.examplehiltdagger.data.model.FacialFeature
import org.json.JSONException
import java.lang.reflect.Type
import java.util.ArrayList
import kotlin.jvm.Throws

class AccountParser : JsonDeserializer<Account?> {
    private val TAG = AccountParser::class.java.simpleName
    private val gsonFeature = GsonBuilder().registerTypeAdapter(FacialFeature::class.java, FacialFeatureParser()).create()

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Account? {
        val _json = json.asJsonObject
        val featureArray = ArrayList<FacialFeature>()
        val featureJSONArray = _json.getAsJsonArray(JSON_KEY_FEATURE)

        for(i in featureJSONArray){
            featureArray.add(gsonFeature.fromJson(i, FacialFeature::class.java))
        }

        var id : String
        try{
            id = _json.get(ID_KEY).asString
        }catch(e: Exception){
            Log.e(TAG, "check for missing keys : " + _json.keySet().joinToString(", "))
            Log.e(TAG, e.stackTraceToString())
            return null
        }

        val account = Account(id = id, name = _json.get(NAME_KEY).asString, feature = featureArray)
        return account
    }

    companion object{
        const val ID_KEY = "_id"
        const val NAME_KEY = "name"
        const val TIMESTAMP_KEY = "timestamp"
        const val JSON_KEY_FEATURE = "features"
    }
}
