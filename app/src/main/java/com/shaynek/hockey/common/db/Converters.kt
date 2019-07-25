package com.shaynek.hockey.common.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shaynek.hockey.common.model.Conference
import com.shaynek.hockey.common.model.Division
import com.shaynek.hockey.common.model.Franchise
import com.shaynek.hockey.common.model.Venue

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun venueToString(venue: Venue?): String? {
        return venue?.let { gson.toJson(venue) }
    }

    @TypeConverter
    fun stringToVenue(json: String?): Venue? {
        val venueType = object : TypeToken<Venue>() {}.type
        return gson.fromJson<Venue>(json, venueType)
    }

    @TypeConverter
    fun divisionToString(division: Division?): String? {
        return division?.let { gson.toJson(division) }
    }

    @TypeConverter
    fun stringToDivision(json: String?): Division? {
        val division = object : TypeToken<Division>() {}.type
        return gson.fromJson<Division>(json, division)
    }

    @TypeConverter
    fun conferenceToString(conference: Conference?): String? {
        return conference?.let { gson.toJson(conference) }
    }

    @TypeConverter
    fun stringToConference(json: String?): Conference? {
        val conference = object : TypeToken<Conference>() {}.type
        return gson.fromJson<Conference>(json, conference)
    }

    @TypeConverter
    fun franchiseToString(franchise: Franchise?): String? {
        return franchise?.let { gson.toJson(franchise) }
    }

    @TypeConverter
    fun stringToFranchise(json: String?): Franchise? {
        val franchise = object : TypeToken<Franchise>() {}.type
        return gson.fromJson<Franchise>(json, franchise)
    }
}