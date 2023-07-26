package com.example.myapplication.ui.home

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
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val fakeFeed = ArrayList<FeedItem>(1000)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val recyclerView = _binding!!.feedList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        initFakeFeed()
        val adapter = FeedAdapter(fakeFeed)
        recyclerView.adapter = adapter


        /**weather*/
        setWeatherInfo()

        val weatherTextView = _binding!!.WeatherTextView

        Log.d(TAG, "get textview")


        weatherTextView.setOnClickListener {
            Log.d(TAG, "set textview")
            val action = HomeFragmentDirections.actionNavigationHomeToWeatherFragment()
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

    inner class FeedItem(val title: String, val imageId: Int? = null)
    private companion object {
        const val TYPE_WITH_IMAGE = 1
        const val TYPE_WITHOUT_IMAGE = 2
    }

    inner class FeedAdapter(val feedList: List<FeedItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        inner class FeedItemWithImageViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            val titleTextView: TextView = itemView.findViewById(R.id.feedTitle)
            val imageView: ImageView = itemView.findViewById(R.id.feedImage)
        }

        inner class FeedItemWithoutImageViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            val titleTextView: TextView = itemView.findViewById(R.id.feedTitle)
        }


        override fun getItemViewType(position: Int): Int {
            return if (feedList[position].imageId != null) {
                TYPE_WITH_IMAGE
            } else {
                TYPE_WITHOUT_IMAGE
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                TYPE_WITH_IMAGE -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.feed_item_with_image, parent, false)
                    FeedItemWithImageViewHolder(view)
                }

                TYPE_WITHOUT_IMAGE -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.feed_item_without_image, parent, false)
                    FeedItemWithoutImageViewHolder(view)
                }

                else -> throw IllegalArgumentException("Invalid view type")
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val feedItem = feedList[position]

            when (holder.itemViewType) {
                TYPE_WITH_IMAGE -> {
                    val viewHolder = holder as FeedItemWithImageViewHolder
                    viewHolder.imageView.setImageResource(feedItem.imageId!!)
                    viewHolder.titleTextView.text = feedItem.title
                }

                TYPE_WITHOUT_IMAGE -> {
                    val viewHolder = holder as FeedItemWithoutImageViewHolder
                    viewHolder.titleTextView.text = feedItem.title
                }

                else -> throw IllegalArgumentException("Invalid view type")
            }

            holder.itemView.setOnClickListener {
                val action = HomeFragmentDirections.actionNavigationHomeToDetailFragment("test")
                findNavController().navigate(action)
            }
        }

        override fun getItemCount(): Int {
            return feedList.size
        }

    }
}