package com.dutch.adk_agenticfw.core

import android.util.Log
import com.google.adk.kt.models.Model
import com.google.adk.kt.models.mlkit.GenaiPrompt
import com.google.mlkit.genai.prompt.Generation

object OnDeviceModelFactory {

    private const val TAG = "Yotei__OnDeviceModelFactory"

    /**
     * Creates or returns an on-device Model instance powered by Gemini Nano via ML Kit GenAI API.
     */
    @JvmStatic
    fun createOnDeviceModel(): Model {
        Log.d(TAG, "--> createOnDeviceModel: Initializing on-device Gemini Nano GenaiPrompt")

        val generativeModel = Generation.getClient()
        val model = GenaiPrompt.create(
            generativeModel = generativeModel,
            name = "gemini-nano"
        )

        Log.d(TAG, "<-- createOnDeviceModel returned GenaiPrompt(name='gemini-nano')")
        return model
    }

    val onDeviceModel: Model by lazy {
        createOnDeviceModel()
    }
}
