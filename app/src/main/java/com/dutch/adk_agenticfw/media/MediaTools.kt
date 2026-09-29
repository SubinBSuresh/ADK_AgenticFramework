package com.dutch.adk_agenticfw.media

import android.util.Log
import com.google.adk.kt.annotations.Param
import com.google.adk.kt.annotations.Tool

class MediaTools {

    companion object {
        private const val TAG = "Yotei__MediaTools"
    }

    /**
     * Plays music or podcasts based on user search query.
     */
    @Tool
    fun playMusic(
        @Param("Music genre, artist name, album, or song title") query: String
    ): Map<String, String> {
        Log.d(TAG, "--> playMusic: query='$query'")

        // Mock playback execution
        val result = mapOf(
            "status" to "SUCCESS",
            "action" to "PLAYING",
            "query" to query,
            "message" to "Now playing '$query' on automotive audio system."
        )

        Log.d(TAG, "<-- playMusic returned: $result")
        return result
    }

    /**
     * Adjusts the infotainment audio volume level.
     */
    @Tool
    fun adjustVolume(
        @Param("Volume level from 0 to 100") level: Int
    ): Map<String, String> {
        Log.d(TAG, "--> adjustVolume: level=$level")

        val clampedLevel = level.coerceIn(0, 100)
        val result = mapOf(
            "status" to "SUCCESS",
            "action" to "VOLUME_SET",
            "level" to clampedLevel.toString(),
            "message" to "Volume adjusted to $clampedLevel%."
        )

        Log.d(TAG, "<-- adjustVolume returned: $result")
        return result
    }

    /**
     * Controls playback state (PLAY, PAUSE, NEXT, PREVIOUS).
     */
    @Tool
    fun controlPlayback(
        @Param("Playback command: PLAY, PAUSE, NEXT, or PREVIOUS") action: String
    ): Map<String, String> {
        Log.d(TAG, "--> controlPlayback: action='$action'")

        val result = mapOf(
            "status" to "SUCCESS",
            "action" to action.uppercase(),
            "message" to "Media playback state updated to ${action.uppercase()}."
        )

        Log.d(TAG, "<-- controlPlayback returned: $result")
        return result
    }
}
