package com.udacity.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "near_earth_asteroids")
data class AsteroidEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val closeApproachDate: String,
    val absoluteMagnitudeH: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardousAsteroid: Boolean
)