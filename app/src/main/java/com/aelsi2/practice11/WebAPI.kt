package com.aelsi2.practice11

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.JsonReader
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import java.io.InputStreamReader
import java.net.URL

fun randomFace(): BitmapDrawable? {
    val connection = URL("https://100k-faces.glitch.me/random-image").openConnection() ?: return null
    val stream = connection.getInputStream() ?: return null
    val bmp = BitmapFactory.decodeStream(stream)
    return BitmapDrawable(Resources.getSystem(), bmp)
}
fun randomNames(count : Int = 1) : List<String>? {
    val connection = URL("http://names.drycodes.com/$count?nameOptions=girl_names&separator=space").openConnection() ?: return null
    val stream = connection.getInputStream() ?: return null
    val reader = JsonReader(InputStreamReader(stream, Charsets.UTF_8))
    var list = ArrayList<String>()
    reader.beginArray()
    while (reader.hasNext()) {
        val str = reader.nextString()
        list.add(str)
    }
    reader.endArray()
    return list
}
fun  randomName() : String {
    val connection = URL("http://names.drycodes.com/1?nameOptions=girl_names&separator=space").openConnection() ?: return ""
    val stream = connection.getInputStream() ?: return ""
    val reader = JsonReader(InputStreamReader(stream, Charsets.UTF_8))
    reader.beginArray()
    var string = ""
    if (reader.hasNext()) {
        string = reader.nextString()
    }
    reader.endArray()
    return string
}