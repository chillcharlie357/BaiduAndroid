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
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
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

        Log.d(TAG,"get textview")


        weatherTextView.setOnClickListener {
            Log.d(TAG,"set textview")
            findNavController().navigate(R.id.weatherFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initFakeFeed() {
        repeat(100) {
            fakeFeed.add(FeedItem(getString(R.string.title_home), R.drawable.ic_home_black_24dp))
        }
    }

    private fun setWeatherInfo(){
        val temperature: String = "33"
        val location: String = "兰溪"
        val airQuality: String = "优"
        val weatherInfo: String =
            String.format("气温：%s\n位置：%s\n空气质量：%s", temperature, location, airQuality)

        val weatherInfoTextView = _binding!!.WeatherTextView
        weatherInfoTextView.text = weatherInfo
    }

   inner class FeedItem(val title: String, val imageId: Int)

    inner class FeedAdapter(val feedList: List<FeedItem>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val itemTitle = view.findViewById<TextView>(R.id.feedTitle)
            val itemImage = view.findViewById<ImageView>(R.id.feedImage)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
//        TODO("Not yet implemented")
            val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_item_with_image, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
//        TODO("Not yet implemented")
            val feedItem = feedList[position]
            holder.itemImage.setImageResource(feedItem.imageId)
            holder.itemTitle.text = feedItem.title
            holder.itemView.setOnClickListener {
                val action = HomeFragmentDirections.actionNavigationHomeToDetailFragment("test")
                findNavController().navigate(action)
            }
        }
        override fun getItemCount(): Int {
//        TODO("Not yet implemented")
            return feedList.size
        }

    }
}