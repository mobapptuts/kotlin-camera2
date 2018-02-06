package com.mobapptuts.kotlin_camera2.Fragments

import android.graphics.SurfaceTexture
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import com.mobapptuts.kotlin_camera2.R
import kotlinx.android.synthetic.main.fragment_preview.*

/**
 * Created by nigelhenshaw on 2018/01/23.
 */
class PreviewFragment : Fragment() {

    companion object {
        private val TAG = PreviewFragment::class.qualifiedName
        @JvmStatic fun newInstance() = PreviewFragment()
    }

    private val surfaceListener = object: TextureView.SurfaceTextureListener {
        override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
        }

        override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) = Unit

        override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?) = true

        override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
            Log.d(TAG, "textureSurface width: $width height: $height")
            openCamera()
        }
    }

    override fun onResume() {
        super.onResume()

        if (previewTextureView.isAvailable)
            openCamera()
        else
            previewTextureView.surfaceTextureListener = surfaceListener
    }

    private fun openCamera() {

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}