package com.dutch.adk_agenticfw

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.dutch.adk_agenticfw.runner.InfotainmentRunner
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Yotei__MainActivity"
    }

    private val runner = InfotainmentRunner()
    private lateinit var tvLogs: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "--> onCreate: Activity starting")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvLogs = findViewById(R.id.tvLogs)

        findViewById<Button>(R.id.btnComfortTest).setOnClickListener {
            runAgentCommand("Set the driver side temperature to 70 degrees and turn fan speed to 4")
        }

        findViewById<Button>(R.id.btnMediaTest).setOnClickListener {
            runAgentCommand("Play smooth jazz music at volume level 45")
        }

        findViewById<Button>(R.id.btnProductivityTest).setOnClickListener {
            runAgentCommand("What are my upcoming meetings for today?")
        }

        findViewById<Button>(R.id.btnMultiDomainTest).setOnClickListener {
            runAgentCommand("Set driver temperature to 68 degrees, set seat heating to level 2, and start playing jazz music")
        }

        Log.d(TAG, "<-- onCreate: Activity initialized")
    }

    private fun runAgentCommand(command: String) {
        Log.d(TAG, "--> runAgentCommand: command='$command'")
        appendLog("\n> User: $command")

        lifecycleScope.launch {
            runner.sendCommand(
                userId = "driver-01",
                sessionId = "infotainment-session-01",
                messageText = command
            ).collect { response ->
                Log.d(TAG, "  [AGENT_RESPONSE] $response")
                appendLog("Agent: $response")
            }
            Log.d(TAG, "<-- runAgentCommand finished for: '$command'")
        }
    }

    private fun appendLog(text: String) {
        val currentText = tvLogs.text.toString()
        tvLogs.text = "$currentText\n$text"
    }
}
