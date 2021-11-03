package com.jiwon.examplehiltdagger.data.model

data class FacialFeature(val timestamp : Long, val feature : FloatArray){
    internal fun compareFeature(var1 : FloatArray) : Double{
        feature?.let{
            var sumProduct = 0f
            var sumASq = 0f
            var sumBSq = 0f

            for (i in 0 until var1.size) {
                sumProduct += var1.get(i) * feature.get(i)
                sumASq += Math.pow(var1.get(i).toDouble(), 2.toDouble()).toFloat()
                sumBSq += Math.pow(feature.get(i).toDouble(), 2.toDouble()).toFloat()
            }

            return (sumProduct / (Math.sqrt(sumASq.toDouble()) * Math.sqrt(sumBSq.toDouble())))
        } ?: return -1.toDouble()
    }
}