package com.example.myapplication.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        _binding = FragmentWeatherBinding.inflate(inflater,container,false)

        initWeatherInfo()

        return binding.root
    }

    private fun initWeatherInfo(){
        binding.location.text = "兰溪市"
        binding.realtimeTemperature.text = "33"
        binding.realtimeWeather.text = "多云"
        binding.dailyTemperature.text = "26-33"
    }

    class futureWeatherItem()
    class todayWeatherItem()
    class airItem()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}