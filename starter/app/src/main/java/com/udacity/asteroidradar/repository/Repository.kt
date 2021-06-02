package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.main.FilterType
import com.udacity.asteroidradar.utils.DateUtils
import com.udacity.asteroidradar.utils.toDatabaseModel
import com.udacity.asteroidradar.utils.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

class Repository(private val database: AsteroidDatabase) {
    private val filterType = MutableLiveData(FilterType.WEEK)

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.switchMap(filterType) { filter ->
            when (filter) {
                FilterType.TODAY ->
                    Transformations.map(database.asteroidDatabaseDao.getAsteroidsForToday(DateUtils.getNow())) { it.toDomainModel() }
                FilterType.WEEK ->
                    Transformations.map(
                        database.asteroidDatabaseDao.getAsteroidsForWeek(
                            DateUtils.getNow(),
                            DateUtils.getOneWeekFromNow()
                        )
                    ) { it.toDomainModel() }
                FilterType.SAVED ->
                    Transformations.map(database.asteroidDatabaseDao.getAsteroids()) { it.toDomainModel() }
            }
        }

    fun applyFilter(filter: FilterType) {
        filterType.value = filter
    }

    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            refreshAsteroidData()
        }
    }

    private suspend fun refreshAsteroidData() {
        try {
            val result = Network.api.getAsteroids(DateUtils.getNow(), DateUtils.getOneWeekFromNow())
            val asteroids = parseAsteroidsJsonResult(JSONObject(result))
            database.asteroidDatabaseDao.insert(*asteroids.toDatabaseModel())

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Timber.e(e, "Asteroid data refresh failed")
            }
        }
    }

    suspend fun deleteOldAsteroids() {
        withContext(Dispatchers.IO) {
            database.asteroidDatabaseDao.deleteOldAsteroids(DateUtils.getNow())
        }
    }
}