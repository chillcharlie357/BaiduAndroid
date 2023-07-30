package com.example.myapplication.ui.video

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.VideoFullScreenBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class FullScreenVideo : Fragment() {

    private var _binding: VideoFullScreenBinding? = null
    private val binding get() = _binding!!

    private var player: ExoPlayer? = null

    private val args: FullScreenVideoArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = VideoFullScreenBinding.inflate(inflater, container, false)

        player = ExoPlayer.Builder(requireContext()).build().also {
            Log.w("video Uri", "video uri: ${Uri.parse(args.videoUri)}")
            binding.playerView.player = it
            val videoUri = Uri.parse(args.videoUri)
            val mediaItem = MediaItem.fromUri(videoUri)
            it.setMediaItem(mediaItem)
            it.prepare()
        }

        binding.playerView.apply {
            setFullscreenButtonClickListener { isFullScreen ->
                with(context) {
                    if (isFullScreen){

                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}