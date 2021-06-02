package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.BuildConfig.NASA_API_KEY
import com.udacity.asteroidradar.api.ImageApi
import com.udacity.asteroidradar.database.AsteroidDatabase.Companion.getInstance
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getInstance(application)
    private val repository = Repository(database)

    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid>()

    private val _image = MutableLiveData<PictureOfDay>()

    val image: LiveData<PictureOfDay>
        get() = _image

    val navigateToSelectedAsteroid: LiveData<Asteroid>
        get() = _navigateToSelectedAsteroid

    val asteroids = repository.asteroids

    init {
        viewModelScope.launch {
            repository.refreshData()
        }

        getPictureOfTheDay()
    }

    private fun getPictureOfTheDay() {
        viewModelScope.launch {
            try {
                val result = ImageApi.imageOfTheDayApiService.getImageOfTheDay(NASA_API_KEY)
                _image.value = result
            } catch (e: Exception) {
                _image.value = PictureOfDay("", "", "")
            }
        }
    }

    fun displayPropertyDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }

    fun applyFilter(filterType: FilterType) {
        repository.applyFilter(filterType)
    }
}