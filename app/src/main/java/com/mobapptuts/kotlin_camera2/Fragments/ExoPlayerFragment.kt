package com.mobapptuts.kotlin_camera2.Fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.mobapptuts.kotlin_camera2.CameraViewModel
import com.mobapptuts.kotlin_camera2.R

/**
 * Created by nigelhenshaw on 2018/03/02.
 */
class ExoPlayerFragment : Fragment(){

    private val bandwidthMeter by lazy {
        DefaultBandwidthMeter()
    }

    private val trackSelectionFactory by lazy {
        AdaptiveTrackSelection.Factory(bandwidthMeter)
    }

    private val trackSelection by lazy {
        DefaultTrackSelector(trackSelectionFactory)
    }

    private val simpleExoPlayer by lazy {
        ExoPlayerFactory.newSimpleInstance(context, trackSelection)
    }

    private val applicationName by lazy {
        context?.applicationInfo?.loadLabel(context?.packageManager).toString()
    }

    private val dataSourceFactory by lazy {
        DefaultDataSourceFactory(context, Util.getUserAgent(context, applicationName))
    }

    private val videoMediaSource by lazy {
        ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(cameraViewModel.videoUri)
    }

    private val cameraViewModel by lazy {
        ViewModelProviders.of(activity!!).get(CameraViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exoplayer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "Video uri: ${cameraViewModel.videoUri}")
    }

    companion object {
        val TAG = ExoPlayerFragment::class.qualifiedName
        @JvmStatic fun newInstance() = ExoPlayerFragment()
    }
}