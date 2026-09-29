package com.dutch.adk_agenticfw.runner

import android.util.Log
import com.dutch.adk_agenticfw.router.RouterAgent
import com.google.adk.kt.runners.InMemoryRunner
import com.google.adk.kt.sessions.InMemorySessionService
import com.google.adk.kt.types.Content
import com.google.adk.kt.types.Part
import com.google.adk.kt.types.Role
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InfotainmentRunner {

    companion object {
        private const val TAG = "Yotei__InfotainmentRunner"
    }

    private val sessionService = InMemorySessionService()
    private val runner = InMemoryRunner(
        agent = RouterAgent.rootAgent,
        sessionService = sessionService
    )

    fun sendCommand(
        userId: String,
        sessionId: String,
        messageText: String
    ): Flow<String> = flow {
        Log.d(TAG, "--> sendCommand: userId='$userId', sessionId='$sessionId', messageText='$messageText'")

        val newContent = Content(
            role = Role.USER,
            parts = listOf(Part(text = messageText))
        )

        try {
            runner.runAsync(
                userId = userId,
                sessionId = sessionId,
                newMessage = newContent
            ).collect { event ->
                val responseText = event.content?.parts?.firstOrNull()?.text
                if (!responseText.isNullOrBlank()) {
                    Log.d(TAG, "  [EVENT_RESPONSE] Received fragment: '$responseText'")
                    emit(responseText)
                }
            }
            Log.d(TAG, "<-- sendCommand completed for messageText='$messageText'")
        } catch (e: Exception) {
            Log.e(TAG, "<-- sendCommand error for messageText='$messageText': ${e.message}", e)
            emit("Error executing infotainment command: ${e.localizedMessage}")
        }
    }.flowOn(Dispatchers.IO)
}
