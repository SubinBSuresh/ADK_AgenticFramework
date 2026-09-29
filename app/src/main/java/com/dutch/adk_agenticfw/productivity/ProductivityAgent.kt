package com.dutch.adk_agenticfw.productivity

import android.util.Log
import com.dutch.adk_agenticfw.BuildConfig
import com.google.adk.kt.agents.Instruction
import com.google.adk.kt.agents.LlmAgent
import com.google.adk.kt.models.Gemini

object ProductivityAgent {

    private const val TAG = "Yotei__ProductivityAgent"

    @JvmStatic
    fun createAgent(): LlmAgent {
        Log.d(TAG, "--> createAgent: Initializing ProductivityAgent")

        val productivityTools = ProductivityTools()
        val agent = LlmAgent(
            name = "productivity_agent",
            description = "Handles calendar scheduling, messaging, and vehicle navigation routing.",
            model = Gemini(
                name = "gemini-3.1-flash-lite",
                apiKey = BuildConfig.GEMINI_API_KEY
            ),
            instruction = Instruction(
                "You are the Productivity Agent for an automotive infotainment system. " +
                "You fulfill user requests regarding calendar events, messaging, " +
                "and setting navigation routes using the provided tools."
            ),
            tools = productivityTools.generatedTools()
        )

        Log.d(TAG, "<-- createAgent returned LlmAgent(name='${agent.name}')")
        return agent
    }

    val agent: LlmAgent by lazy {
        createAgent()
    }
}
