package com.nwar.dsm.deanomoo_dsm.Image

import android.graphics.Bitmap
import com.squareup.picasso.Transformation

class PicassoTransFormation{
    private val targetHeight = 400
    val resizeTransFormation = object : Transformation{
        override fun transform(source : Bitmap):Bitmap{
            val aspectRatio = source.width.toDouble()/source.height.toDouble()
            val targetWidth = (targetHeight*aspectRatio).toInt()
            val result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false)
            if(result!=source){
                source.recycle()
            }
            return result
        }
        override fun key() : String{
            return "resizeTransformation#" + System.currentTimeMillis()
        }
    }
}