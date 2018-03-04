package com.mobapptuts.kotlin_camera2

import android.arch.lifecycle.ViewModel
import android.net.Uri
import android.util.Rational

/**
 * Created by nigelhenshaw on 2018/03/02.
 */
class CameraViewModel : ViewModel(){
    var videoUri: Uri? = null
    var isVideoPlaying = false
    var aspectRatio : Rational? = null
}