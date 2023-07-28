package com.example.myapplication.ui.video

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentVideoBinding
import com.example.myapplication.databinding.VideoItemBinding

class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val dashboardViewModel =
            ViewModelProvider(this)[VideoViewModel::class.java]

        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initSampleVideos()
        val recyclerView = binding.videoList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = VideoListAdapter(sampleVideo)

        return root

//        val b = VideoItemBinding.inflate(inflater, container, false)
//        initializePlayer(b)
//        return b.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        binding.videoList.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private val sampleVideo = ArrayList<Video>(100)
    private fun initSampleVideos() {
        val videoUris = listOf<Uri>(
            Uri.parse(getString(R.string.media_url)),
            RawResourceDataSource.buildRawResourceUri(R.raw.sample_video)
        )
        repeat(100) {
            sampleVideo.add(
                Video(
                    title = "Sample",
                    description = "Sample",
                    thumbnailUri = RawResourceDataSource.buildRawResourceUri(R.drawable.icons8_avatar_48),
                    videoUri = videoUris.random()
                )
            )
        }
    }

    private var player: ExoPlayer? = null
    private fun initializePlayer(binding: VideoItemBinding) {
        player = ExoPlayer.Builder(requireContext())
            .build()
            .also {
                binding.playerView.player = it
                val videoUri =
                    RawResourceDataSource.buildRawResourceUri(R.raw.sample_video).toString()
                val mediaItem =
                    MediaItem.fromUri(videoUri)
                it.setMediaItem(mediaItem)
            }
        player?.playWhenReady = true
        player?.prepare()
        player?.play()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    data class Video(
//        val id: String,
        val title: String,
        val description: String,
        val thumbnailUri: Uri,
        val videoUri: Uri
    )

    inner class VideoListAdapter(private val videoList: List<Video>) :
        RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
            val binding = VideoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return VideoViewHolder(binding)
        }

        override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
            val video = videoList[position]
            holder.bind(video)
            holder.binding.playerTitle.text = video.title
        }

        override fun getItemCount(): Int {
            return videoList.size
        }


        inner class VideoViewHolder(val binding: VideoItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            private var title: TextView? = binding.playerTitle
            private val playerView: PlayerView = binding.playerView
            private var player: ExoPlayer? = null

            fun bind(video: Video) {
                // Create a new ExoPlayer instance for this video
                player = ExoPlayer.Builder(itemView.context).build().also {
                    playerView.player = it
                    val videoUri = video.videoUri
                    val mediaItem =
                        MediaItem.fromUri(videoUri)
                    it.setMediaItem(mediaItem)
                }

                player?.prepare()

                playerView.setOnClickListener {
                }
            }

            fun releasePlayer() {
                player?.release()
                player = null
            }
        }
    }
}