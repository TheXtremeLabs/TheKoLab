package fr.lvmvrquxl.thekolab.core.weather

import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Weather's API service.
 *
 * This interface is responsible for managing data from OpenWeatherMap API.
 *
 * @since 0.1.3
 */
internal interface WeatherApiService {
    companion object {
        /**
         * Base URL of the API.
         *
         * @since 0.1.3
         */
        const val BASE_URL: String = "http://api.openweathermap.org/data/2.5/"
        private const val ID: String = "47faa3e3eb39a298e9608fb62362c0a1"
        private const val UNITS: String = "metric"
        private const val URI: String = "weather?appid=$ID&units=$UNITS"
    }

    /**
     * Retrieve weather from given coordinates.
     *
     * @param latitude Location's latitude
     * @param longitude Location's longitude
     *
     * @return Representation of the HTTP request
     *
     * @since 0.1.3
     *
     * @see [Call]
     * @see [WeatherDTO]
     */
    @GET(URI)
    fun getWeatherFromCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Call<WeatherDTO>
}