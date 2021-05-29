package com.adityaikhbalm.maps.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object Utility {

    private val metrics = Resources.getSystem().displayMetrics

    val Int.convertDpToPixel
        get() = (this * metrics.density).toInt()

    val Int.convertPixelToDp
        get() = (this / metrics.density).toInt()

    fun Context.getMyColor(color: Int) = ContextCompat.getColor(this, color)

    fun View.showIt() {
        this.isVisible = true
    }

    fun View.hideIt() {
        this.isVisible = false
    }

    @SuppressLint("ShowToast")
    fun Context.toastShow(message: Any) {
        var toast: Toast? = null
        if (message is Int) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        }
        else if (message is String){
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        }
        toast?.setGravity(Gravity.CENTER, 0, 0)
        toast?.show()
    }

    fun View.clicks(): Flow<Unit> = callbackFlow {
        setOnClickListener {
            offer(Unit)
        }
        awaitClose { setOnClickListener(null) }
    }
}