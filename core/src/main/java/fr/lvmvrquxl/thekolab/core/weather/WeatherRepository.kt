package fr.lvmvrquxl.thekolab.core.weather

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Weather repository.
 *
 * This singleton object should be used for accessing weather info from API.
 *
 * @since 1.0.0
 */
internal object WeatherRepository {
    private var apiService: WeatherApiService? = null

    init {
        val converter: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        this.apiService = Retrofit.Builder()
            .baseUrl(WeatherApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(converter))
            .build()
            .create(WeatherApiService::class.java)
    }

    /**
     * Get weather info from given coordinates.
     *
     * @param latitude Location's latitude
     * @param longitude Location's longitude
     * @param callback Weather callback
     *
     * @since 1.0.0
     *
     * @see [Callback]
     */
    fun getWeatherFromCoordinates(
        latitude: Double,
        longitude: Double,
        callback: Callback<WeatherDTO>
    ) = this.apiService?.let { service: WeatherApiService ->
        service.getWeatherFromCoordinates(latitude, longitude).enqueue(callback)
    }
}