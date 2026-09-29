package com.dutch.adk_agenticfw.productivity

import android.util.Log
import com.google.adk.kt.annotations.Param
import com.google.adk.kt.annotations.Tool

class ProductivityTools {

    companion object {
        private const val TAG = "Yotei__ProductivityTools"
    }

    /**
     * Checks upcoming calendar events or schedule.
     */
    @Tool
    fun getUpcomingMeetings(
        @Param("Time range or filter e.g. TODAY, TOMORROW, THIS_WEEK") filter: String
    ): Map<String, String> {
        Log.d(TAG, "--> getUpcomingMeetings: filter='$filter'")

        val result = mapOf(
            "status" to "SUCCESS",
            "filter" to filter,
            "events" to "10:00 AM - Design Sync with Automotive Team; 02:30 PM - Client Demo",
            "message" to "Found 2 calendar meetings for $filter."
        )

        Log.d(TAG, "<-- getUpcomingMeetings returned: $result")
        return result
    }

    /**
     * Sends a hands-free text message to a contact.
     */
    @Tool
    fun sendQuickMessage(
        @Param("Contact name or phone number") recipient: String,
        @Param("Message content to send") message: String
    ): Map<String, String> {
        Log.d(TAG, "--> sendQuickMessage: recipient='$recipient', message='$message'")

        val result = mapOf(
            "status" to "SUCCESS",
            "recipient" to recipient,
            "messageSent" to message,
            "message" to "Message successfully sent to $recipient."
        )

        Log.d(TAG, "<-- sendQuickMessage returned: $result")
        return result
    }

    /**
     * Sets GPS navigation route to specified location.
     */
    @Tool
    fun setNavigationDestination(
        @Param("Destination address or point of interest e.g. Central Station, Home") destination: String
    ): Map<String, String> {
        Log.d(TAG, "--> setNavigationDestination: destination='$destination'")

        val result = mapOf(
            "status" to "SUCCESS",
            "destination" to destination,
            "eta" to "18 mins",
            "distance" to "12.4 km",
            "message" to "Navigation route started to $destination."
        )

        Log.d(TAG, "<-- setNavigationDestination returned: $result")
        return result
    }
}
