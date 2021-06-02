package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface AsteroidDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg asteroid: AsteroidEntity)

    @Update
    suspend fun update(asteroid: AsteroidEntity)

    @Query("delete from near_earth_asteroids")
    suspend fun deleteAllAsteroids()

    @Query("delete from near_earth_asteroids where closeApproachDate < :days")
    suspend fun deleteOldAsteroids(days: String)

    @Query("select * from near_earth_asteroids order by closeApproachDate ASC")
    fun getAsteroids(): LiveData<List<AsteroidEntity>>

    @Query("select * from near_earth_asteroids where closeApproachDate = :today")
    fun getAsteroidsForToday(today: String): LiveData<List<AsteroidEntity>>

    @Query("select * from near_earth_asteroids where closeApproachDate between :startDate and :endDate")
    fun getAsteroidsForWeek(startDate: String, endDate: String): LiveData<List<AsteroidEntity>>
}