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
import kotlinx.android.synthetic.main.fragment_first_image.*

/**
 * Created by nigelhenshaw on 2018/01/23.
 */
class FirstImageFragment: Fragment() {

    private val imageUrl = "https://static.pexels.com/photos/596940/pexels-photo-596940.jpeg"

    private fun loadImageUsingGlide() {
        firstFragmentProgressBar.visibility = View.VISIBLE
        GlideApp.with(activity).asBitmap()
                .load(Uri.parse(imageUrl))
                .into(object : BitmapImageViewTarget(firstFragmentImageView){
                    override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(resource, transition)
                        firstFragmentProgressBar.visibility = View.INVISIBLE
                    }
                })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadImageUsingGlide()
    }

    companion object {
        fun newInstance() = FirstImageFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first_image, container, false)
    }
}