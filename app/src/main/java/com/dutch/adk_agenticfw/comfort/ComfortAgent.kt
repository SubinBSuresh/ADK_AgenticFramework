package com.dutch.adk_agenticfw.comfort

import android.util.Log
import com.dutch.adk_agenticfw.BuildConfig
import com.google.adk.kt.agents.Instruction
import com.google.adk.kt.agents.LlmAgent
import com.google.adk.kt.models.Gemini

object ComfortAgent {

    private const val TAG = "Yotei__ComfortAgent"

    @JvmStatic
    fun createAgent(): LlmAgent {
        Log.d(TAG, "--> createAgent: Initializing ComfortAgent")

        val comfortTools = ComfortTools()
        val agent = LlmAgent(
            name = "comfort_agent",
            description = "Handles HVAC cabin climate control, temperature adjustments, fan speed, and seat heating.",
            model = Gemini(
                name = "gemini-3.1-flash-lite",
                apiKey = BuildConfig.GEMINI_API_KEY
            ),
            instruction = Instruction(
                "You are the Comfort Agent for an automotive infotainment system. " +
                "You fulfill user requests regarding HVAC temperature, climate control, fan speed, " +
                "and seat heating/cooling using the provided tools."
            ),
            tools = comfortTools.generatedTools()
        )

        Log.d(TAG, "<-- createAgent returned LlmAgent(name='${agent.name}')")
        return agent
    }

    val agent: LlmAgent by lazy {
        createAgent()
    }
}
