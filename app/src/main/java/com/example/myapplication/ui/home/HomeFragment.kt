package com.example.myapplication.ui.home

import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val fakeFeed = ArrayList<FeedItem>(1000)

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
}

class FeedItem(val title: String, val imageId: Int)

class FeedAdapter(val feedList: List<FeedItem>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTitle = view.findViewById<TextView>(R.id.feedTitle)
        val itemImage = view.findViewById<ImageView>(R.id.feedImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
//        TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
//        TODO("Not yet implemented")
        val feedItem = feedList[position]
        holder.itemImage.setImageResource(feedItem.imageId)
        holder.itemTitle.text = feedItem.title
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return feedList.size
    }


}