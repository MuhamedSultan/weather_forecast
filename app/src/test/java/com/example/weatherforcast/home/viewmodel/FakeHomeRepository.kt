package com.example.weatherforcast.home.viewmodel

import com.example.weatherforcast.home.repo.HomeRepository
import com.example.weatherforcast.pojo.current_weather.WeatherResponse
import com.example.weatherforcast.pojo.days_weather.DaysWeatherResponse
import com.example.weatherforcast.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHomeRepository : HomeRepository {

    private var weatherResponse: Result<WeatherResponse>? = null
    private var daysWeatherResponse: Result<DaysWeatherResponse>? = null

    var weatherAdded: WeatherResponse? = null
    var daysWeatherAdded: DaysWeatherResponse? = null

    fun setWeatherResponse(response: Result<WeatherResponse>) {
        weatherResponse = response
    }

    fun setDaysWeatherResponse(response: Result<DaysWeatherResponse>) {
        daysWeatherResponse = response
    }

    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): Flow<Result<WeatherResponse?>> {
        return flow {
            weatherResponse?.let {
                emit(it as Result<WeatherResponse?>)
            } ?: emit(Result.Error("No weather response set"))
        }
    }

    override suspend fun getDaysWeather(
        lat: Double,
        lon: Double
    ): Flow<Result<DaysWeatherResponse?>> {
        return flow {
            daysWeatherResponse?.let {
                emit(it as  Result<DaysWeatherResponse?>)
            } ?: emit(Result.Error("No days weather response set"))
        }
    }

    override suspend fun addCurrentWeather(weatherResponse: WeatherResponse) {
        this.weatherAdded = weatherResponse
    }

    override suspend fun addDaysWeather(daysWeatherResponse: DaysWeatherResponse) {
        this.daysWeatherAdded = daysWeatherResponse
    }
}
