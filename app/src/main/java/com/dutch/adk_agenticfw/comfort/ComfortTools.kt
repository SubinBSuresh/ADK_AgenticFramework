package com.dutch.adk_agenticfw.comfort

import android.util.Log
import com.google.adk.kt.annotations.Param
import com.google.adk.kt.annotations.Tool

class ComfortTools {

    companion object {
        private const val TAG = "Yotei__ComfortTools"
    }

    /**
     * Sets HVAC temperature for a specified cabin zone.
     */
    @Tool
    fun setTemperature(
        @Param("Cabin zone e.g. DRIVER, PASSENGER, REAR, or ALL") zone: String,
        @Param("Target temperature in degrees Fahrenheit or Celsius e.g. 70.0") degrees: Double
    ): Map<String, String> {
        Log.d(TAG, "--> setTemperature: zone='$zone', degrees=$degrees")

        val result = mapOf(
            "status" to "SUCCESS",
            "zone" to zone.uppercase(),
            "targetTemperature" to "$degrees",
            "message" to "HVAC temperature for $zone set to $degrees degrees."
        )

        Log.d(TAG, "<-- setTemperature returned: $result")
        return result
    }

    /**
     * Sets climate fan speed level.
     */
    @Tool
    fun setFanSpeed(
        @Param("Fan speed level from 1 to 7") speed: Int
    ): Map<String, String> {
        Log.d(TAG, "--> setFanSpeed: speed=$speed")

        val clampedSpeed = speed.coerceIn(1, 7)
        val result = mapOf(
            "status" to "SUCCESS",
            "fanSpeed" to clampedSpeed.toString(),
            "message" to "Cabin fan speed set to level $clampedSpeed."
        )

        Log.d(TAG, "<-- setFanSpeed returned: $result")
        return result
    }

    /**
     * Controls seat heating or ventilation.
     */
    @Tool
    fun setSeatHeating(
        @Param("Seat position e.g. DRIVER, PASSENGER") seat: String,
        @Param("Heating/ventilation level from 0 (OFF) to 3 (HIGH)") level: Int
    ): Map<String, String> {
        Log.d(TAG, "--> setSeatHeating: seat='$seat', level=$level")

        val clampedLevel = level.coerceIn(0, 3)
        val result = mapOf(
            "status" to "SUCCESS",
            "seat" to seat.uppercase(),
            "level" to clampedLevel.toString(),
            "message" to "Seat heating for $seat set to level $clampedLevel."
        )

        Log.d(TAG, "<-- setSeatHeating returned: $result")
        return result
    }
}
