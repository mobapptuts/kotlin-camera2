package com.mobapptuts.kotlinfragments

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_second_image.*

/**
 * Created by nigelhenshaw on 2018/01/23.
 */
class SecondImageFragment : Fragment() {

    private val imageUrl = "https://images.pexels.com/photos/163065/mobile-phone-android-apps-phone-163065.jpeg"

    private fun loadImageUsingGlide() {
        secondFragmentProgressBar.visibility = View.VISIBLE
        GlideApp.with(activity).asBitmap()
                .load(Uri.parse(imageUrl))
                .into(object : BitmapImageViewTarget(secondFragmentImageView){
                    override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(resource, transition)
                        secondFragmentProgressBar.visibility = View.INVISIBLE
                    }
                })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadImageUsingGlide()
    }

    companion object {
        fun newInstance() = SecondImageFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second_image, container, false)
    }
}