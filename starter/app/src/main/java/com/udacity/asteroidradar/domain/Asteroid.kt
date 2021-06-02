package com.udacity.asteroidradar.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(val id: Long, val name: String, val closeApproachDate: String,
                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
                    val relativeVelocity: Double, val distanceFromEarth: Double,
                    val isPotentiallyHazardousAsteroid: Boolean) : Parcelable