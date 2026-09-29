package com.dutch.adk_agenticfw.media

import android.util.Log
import com.dutch.adk_agenticfw.BuildConfig
import com.google.adk.kt.agents.Instruction
import com.google.adk.kt.agents.LlmAgent
import com.google.adk.kt.models.Gemini

object MediaAgent {

    private const val TAG = "Yotei__MediaAgent"

    @JvmStatic
    fun createAgent(): LlmAgent {
        Log.d(TAG, "--> createAgent: Initializing MediaAgent")

        val mediaTools = MediaTools()
        val agent = LlmAgent(
            name = "media_agent",
            description = "Handles infotainment audio playback, music selection, track navigation, and volume control.",
            model = Gemini(
                name = "gemini-3.1-flash-lite",
                apiKey = BuildConfig.GEMINI_API_KEY
            ),
            instruction = Instruction(
                "You are the Media Agent for an automotive infotainment system. " +
                "You fulfill user requests regarding music, podcasts, volume adjustment, and playback controls " +
                "using the provided tools."
            ),
            tools = mediaTools.generatedTools()
        )

        Log.d(TAG, "<-- createAgent returned LlmAgent(name='${agent.name}')")
        return agent
    }

    val agent: LlmAgent by lazy {
        createAgent()
    }
}
