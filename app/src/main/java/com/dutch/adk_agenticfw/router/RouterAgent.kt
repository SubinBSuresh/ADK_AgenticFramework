package com.dutch.adk_agenticfw.router

import android.util.Log
import com.dutch.adk_agenticfw.BuildConfig
import com.dutch.adk_agenticfw.comfort.ComfortAgent
import com.dutch.adk_agenticfw.media.MediaAgent
import com.dutch.adk_agenticfw.productivity.ProductivityAgent
import com.google.adk.kt.agents.Instruction
import com.google.adk.kt.agents.LlmAgent
import com.google.adk.kt.models.Gemini

object RouterAgent {

    private const val TAG = "Yotei__RouterAgent"

    @JvmStatic
    fun createRootAgent(): LlmAgent {
        Log.d(TAG, "--> createRootAgent: Initializing RouterAgent with sub-agents")

        val agent = LlmAgent(
            name = "router_agent",
            description = "Master orchestrator for automotive infotainment commands. Delegates requests to media_agent, comfort_agent, or productivity_agent.",
            model = Gemini(
                name = "gemini-3.1-flash-lite",
                apiKey = BuildConfig.GEMINI_API_KEY
            ),
            instruction = Instruction(
                "You are the master Router Agent for an automotive infotainment system. " +
                "Evaluate the driver's request and delegate execution to the most appropriate sub-agent: " +
                "'media_agent' for audio/music/volume, " +
                "'comfort_agent' for HVAC/climate/seats, or " +
                "'productivity_agent' for calendar/navigation/messaging."
            ),
            subAgents = listOf(
                MediaAgent.agent,
                ComfortAgent.agent,
                ProductivityAgent.agent
            )
        )

        Log.d(TAG, "<-- createRootAgent returned LlmAgent(name='${agent.name}', subAgentsCount=${agent.subAgents.size})")
        return agent
    }

    val rootAgent: LlmAgent by lazy {
        createRootAgent()
    }
}
