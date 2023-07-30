package com.example.myapplication.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.DailyWeatherItemBinding
import com.example.myapplication.databinding.FragmentWeatherBinding
import com.example.myapplication.utils.tools

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        _binding = FragmentWeatherBinding.inflate(inflater, container, false)

        initWeatherInfo()


        val recyclerView = binding.dailyWeather
        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.HORIZONTAL,
            false
        )
        val adapter = WeatherItemAdapter(fakeDailyWeather())
        recyclerView.adapter = adapter

        return binding.root
    }

    private fun initWeatherInfo() {
        binding.location.text = "兰溪市"
        binding.realtimeTemperature.text = "33"
        binding.realtimeWeather.text = "多云"
        binding.realtimeTemperature.text = "26-33"
    }

    private fun fakeDailyWeather(): List<DailyWeatherItem> {
        return List(24) {
            val time = "${it}时"
            val weatherList = listOf(
                WeatherTypes.clearDay,
                WeatherTypes.clearNight,
                WeatherTypes.cloudy,
                WeatherTypes.fog,
                WeatherTypes.hail,
                WeatherTypes.heavyHaze,
                WeatherTypes.heavyRain,
                WeatherTypes.heavySnow,
                WeatherTypes.lightHaze,
                WeatherTypes.lightRain,
                WeatherTypes.lightSnow,
                WeatherTypes.moderateSnow,
                WeatherTypes.moderateRain,
                WeatherTypes.moderateHaze,
                WeatherTypes.partlyCloudDay,
                WeatherTypes.partlyCloudNight,
                WeatherTypes.sleet,
                WeatherTypes.stormRain,
                WeatherTypes.thunderShower
            )
            val weatherType = weatherList.random()
            DailyWeatherItem(time, weatherType)
        }
    }

    companion object WeatherTypes {
        val clearDay = R.drawable.ic_clear_day
        val clearNight = R.drawable.ic_clear_night
        val cloudy = R.drawable.ic_cloudy
        val fog = R.drawable.ic_fog
        val hail = R.drawable.ic_hail
        val heavyHaze = R.drawable.ic_heavy_haze
        val heavyRain = R.drawable.ic_heavy_rain
        val heavySnow = R.drawable.ic_heavy_snow
        val lightHaze = R.drawable.ic_light_haze
        val lightRain = R.drawable.ic_light_rain
        val lightSnow = R.drawable.ic_light_snow
        val moderateSnow = R.drawable.ic_moderate_snow
        val moderateRain = R.drawable.ic_moderate_rain
        val moderateHaze = R.drawable.ic_moderate_haze
        val partlyCloudDay = R.drawable.ic_partly_cloud_day
        val partlyCloudNight = R.drawable.ic_partly_cloud_night
        val sleet = R.drawable.ic_sleet
        val stormRain = R.drawable.ic_storm_rain
        val thunderShower = R.drawable.ic_thunder_shower
    }

    data class DailyWeatherItem(val time: String, val weatherType: Int)

    inner class WeatherItemAdapter(private val weatherItemList: List<DailyWeatherItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val binding = DailyWeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return WeatherItemViewHolder(binding)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val weatherItem = weatherItemList[position]
            (holder as WeatherItemViewHolder).binding.also {
                it.time.text = weatherItem.time
                it.weatherIcon.setImageURI(tools.resourceToUri(weatherItem.weatherType))
            }
        }

        override fun getItemCount(): Int {
            return weatherItemList.size
        }


        inner class WeatherItemViewHolder(val binding: DailyWeatherItemBinding) :
            RecyclerView.ViewHolder(binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}