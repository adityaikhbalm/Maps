package com.adityaikhbalm.maps.ui

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.util.TypedValue
import android.view.*
import android.view.animation.AccelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleCoroutineScope
import com.adityaikhbalm.maps.R
import com.adityaikhbalm.maps.databinding.ActivityMapBinding
import com.adityaikhbalm.maps.databinding.ButtonMapBinding
import com.adityaikhbalm.maps.utils.Utility.clicks
import com.adityaikhbalm.maps.utils.Utility.convertDpToPixel
import com.adityaikhbalm.maps.utils.Utility.getMyColor
import com.adityaikhbalm.maps.utils.Utility.hideIt
import com.adityaikhbalm.maps.utils.Utility.showIt
import kotlinx.coroutines.flow.*
import kotlin.properties.Delegates

private var systemWindowInsets by Delegates.notNull<Int>()

fun LifecycleCoroutineScope.setCustomAppBar(
    binding: ActivityMapBinding,
    button: ButtonMapBinding,
    context: Context,
    window: Window,
) {
    fun searchShow() {
        button.apply {
            btnGps.hideIt()
            btnZoomIn.hideIt()
            btnZoomOut.hideIt()
        }

        binding.searchLayout.apply {
            searchCard.strokeWidth = 3
            searchCard.elevation = 0f
            searchBackground.setBackgroundColor(context.getMyColor(R.color.white))

            val h = 7.convertDpToPixel
            layoutHistory.translationY = -h.toFloat()
            layoutHistory.animate().apply {
                translationY(0f)
                startDelay = 100
                duration = 200
                interpolator = AccelerateInterpolator(1.5f)
                withStartAction {
                    rootHistory.animate().apply {
                        alpha(1f)
                        duration = 200
                    }
                }
            }
        }
    }

    fun searchHide() {
        button.apply {
            btnGps.showIt()
            btnZoomIn.showIt()
            btnZoomOut.showIt()
        }
    }

    binding.apply {
        root.setOnApplyWindowInsetsListener { _, insets ->
            systemWindowInsets = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                insets.getInsets(WindowInsets.Type.systemBars()).top
            } else {
                insets.systemWindowInsetTop
            } + 25

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.topMargin = systemWindowInsets
            searchLayout.searchCard.layoutParams = params

            insets
        }

        window.apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                } else {
                    decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                }
            } else {
                decorView.windowInsetsController?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        }

        searchLayout.searchView
            .clicks()
            .debounce(200)
            .onEach {
                searchLayout.rootHistory.showIt()
                searchLayout.searchView.requestFocus()
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.showSoftInput(searchLayout.searchView, 0)

                searchShow()
            }
            .launchIn(this@setCustomAppBar)

        val closeButtonId = context.resources.getIdentifier("android:id/search_close_btn", null, null)
        val closeButtonImage = searchLayout.searchView.findViewById(closeButtonId) as ImageView

        closeButtonImage.setImageResource(R.drawable.ic_close_icon_circle)
        closeButtonImage.setColorFilter(
            context.getMyColor(R.color.icon_color),
            PorterDuff.Mode.SRC_IN
        )

        val backButtonId = context.resources.getIdentifier("android:id/search_mag_icon", null, null)
        val backButtonImage = searchLayout.searchView.findViewById(backButtonId) as ImageView
        val value = TypedValue()

        context.theme.resolveAttribute(android.R.attr.actionBarItemBackground, value, true)
        backButtonImage.setBackgroundResource(value.resourceId)
        closeButtonImage.setBackgroundResource(value.resourceId)

        searchLayout.searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                searchShow()

                backButtonImage.apply {
                    setImageResource(R.drawable.ic_arrow_back)
                    setColorFilter(
                        context.getMyColor(R.color.icon_color),
                        PorterDuff.Mode.SRC_IN
                    )
                    isClickable = true

                    clicks()
                        .debounce(200)
                        .onEach {
                            setOnClickListener(null)
                            isClickable = false
                            colorFilter = null
                            setImageResource(R.drawable.drop_logo)

                            searchLayout.apply {
                                searchCard.strokeWidth = 0
                                searchCard.elevation = 5.convertDpToPixel.toFloat()
                                searchBackground.setBackgroundColor(context.getMyColor(android.R.color.transparent))
                                rootHistory.hideIt()
                                rootHistory.alpha = 0f
                            }

                            v.clearFocus()
                        }.launchIn(this@setCustomAppBar)
                }
            } else {
                searchHide()
            }
        }
    }
}