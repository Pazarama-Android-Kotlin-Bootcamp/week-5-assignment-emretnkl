package com.emretnkl.week5Assignment.ui.loadingprogress

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.emretnkl.week5Assignment.R

/**
 * Created by merttoptas on 16.10.2022.
 */

class LoadingProgressBar(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_loading_progress)
        window?.setBackgroundDrawableResource(R.color.transparent)
        setCancelable(false)
    }
}