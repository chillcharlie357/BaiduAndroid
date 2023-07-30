package com.example.myapplication.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.entity.NewsEntity
import com.example.myapplication.databinding.FeedItemWithImageBinding
import com.example.myapplication.databinding.FeedItemWithoutImageBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import java.net.URLEncoder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val fakeFeed = ArrayList<FeedItem>(1000)

    private lateinit var homeViewModel: HomeViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val recyclerView = _binding!!.feedList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
//        initFakeFeed()
//        val adapter = FeedAdapter(fakeFeed)
        homeViewModel.allNews.observe(viewLifecycleOwner) {
            val adapter = FeedAdapter(it)
            recyclerView.adapter = adapter
        }


        /**weather*/
        setWeatherInfo()

        Log.d(TAG, "get textview")


        binding.WeatherTextView.setOnClickListener {
            Log.d(TAG, "set textview")
            val action = HomeFragmentDirections.actionNavigationHomeToWeatherFragment()
            findNavController().navigate(action)
        }

        binding.homeImageButton.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToEditNewsFragment()
            findNavController().navigate(action)
        }

        binding.searchButton.setOnClickListener {
            Log.v(TAG, "Start search: $binding.searchBox.text")
            val query = binding.searchBox.text.toString()
            val action = HomeFragmentDirections.actionNavigationHomeToSearchFragment(query)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initFakeFeed() {
        val imageIds = listOf(R.drawable.ic_home_black_24dp, null)
        repeat(100) {
            fakeFeed.add(FeedItem(getString(R.string.title_home), imageIds.random()))
        }
    }

    private fun setWeatherInfo() {
        val temperature: String = "33"
        val location: String = "兰溪"
        val airQuality: String = "优"
        val weatherInfo: String =
            String.format("气温：%s\n位置：%s\n空气质量：%s", temperature, location, airQuality)

        val weatherInfoTextView = _binding!!.WeatherTextView
        weatherInfoTextView.text = weatherInfo
    }

    data class FeedItem(val title: String, val imageId: Int? = null)
    private companion object {
        const val TYPE_WITH_IMAGE = 1
        const val TYPE_WITHOUT_IMAGE = 2
    }

    inner class FeedAdapter(val feedList: List<NewsEntity>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        inner class FeedItemWithImageViewHolder(val binding: FeedItemWithImageBinding) :
            RecyclerView.ViewHolder(binding.root)

        inner class FeedItemWithoutImageViewHolder(val binding: FeedItemWithoutImageBinding) :
            RecyclerView.ViewHolder(binding.root)


        override fun getItemViewType(position: Int): Int {
            return if (feedList[position].thumbnailImageUri != null) {
                TYPE_WITH_IMAGE
            } else {
                TYPE_WITHOUT_IMAGE
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                TYPE_WITH_IMAGE -> {
                    val binding = FeedItemWithImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                    FeedItemWithImageViewHolder(binding)
                }

                TYPE_WITHOUT_IMAGE -> {
                    val binding = FeedItemWithoutImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                    FeedItemWithoutImageViewHolder(binding)
                }

                else -> throw IllegalArgumentException("Invalid view type")
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val feedItem = feedList[position]

            when (holder.itemViewType) {
                TYPE_WITH_IMAGE -> {
                    val viewHolder = holder as FeedItemWithImageViewHolder

                    viewHolder.binding.also {
                        it.feedImage.setImageURI(Uri.parse(feedItem.thumbnailImageUri))
                        it.feedAuthor.text = feedItem.author
                        it.feedTitle.text = feedItem.title
                    }
                }

                TYPE_WITHOUT_IMAGE -> {
                    val viewHolder = holder as FeedItemWithoutImageViewHolder

                    viewHolder.binding.also {
                        it.feedAuthor.text = feedItem.author
                        it.feedTitle.text = feedItem.title
                    }
                }

                else -> throw IllegalArgumentException("Invalid view type")
            }

            holder.itemView.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionNavigationHomeToDetailFragment(feedItem.id)
                findNavController().navigate(action)
            }
        }

        override fun getItemCount(): Int {
            return feedList.size
        }

    }
}