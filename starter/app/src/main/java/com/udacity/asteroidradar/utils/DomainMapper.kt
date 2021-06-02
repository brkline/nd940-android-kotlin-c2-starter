package com.udacity.asteroidradar.utils

import com.udacity.asteroidradar.api.NeoFeedResponse
import com.udacity.asteroidradar.database.AsteroidEntity
import com.udacity.asteroidradar.domain.Asteroid

fun List<AsteroidEntity>.toDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            name = it.name,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitudeH,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardousAsteroid = it.isPotentiallyHazardousAsteroid
        )
    }
}

fun List<NeoFeedResponse>.toDatabaseModel(): Array<AsteroidEntity> {
    return map {
        AsteroidEntity(
            id = it.id,
            name = it.name,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitudeH = it.absoluteMagnitudeH,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardousAsteroid = it.isPotentiallyHazardousAsteroid
        )

    }.toTypedArray()
}