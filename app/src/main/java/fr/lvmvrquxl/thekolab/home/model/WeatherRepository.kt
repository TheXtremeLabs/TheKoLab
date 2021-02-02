package fr.lvmvrquxl.thekolab.home.model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object WeatherRepository {
    private var apiService: WeatherApiService? = null

    init {
        val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        this.apiService = Retrofit.Builder()
            .baseUrl(WeatherApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WeatherApiService::class.java)
    }

    internal fun getWeatherFromCoordinates(
        latitude: Double,
        longitude: Double,
        callback: Callback<WeatherDTO>
    ) = this.apiService?.let { service: WeatherApiService ->
        service.getWeatherFromCoordinates(latitude, longitude).enqueue(callback)
    }
}