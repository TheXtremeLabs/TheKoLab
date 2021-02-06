package fr.lvmvrquxl.thekolab.core.weather

import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherApiService {
    companion object {
        const val BASE_URL: String = "http://api.openweathermap.org/data/2.5/"
        private const val ID: String = "47faa3e3eb39a298e9608fb62362c0a1"
        private const val UNITS: String = "metric"
        private const val URI: String = "weather?appid=$ID&units=$UNITS"
    }

    @GET(URI)
    fun getWeatherFromCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Call<WeatherDTO>
}