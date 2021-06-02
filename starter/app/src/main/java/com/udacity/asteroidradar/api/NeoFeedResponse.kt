package com.udacity.asteroidradar.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NeoFeedResponse(
    val id: Long,
    val name: String,
    val closeApproachDate: String,
    val absoluteMagnitudeH: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardousAsteroid: Boolean
) : Parcelable