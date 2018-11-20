package com.igorwojda.robotcontrol

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import com.igorwojda.robotcontrol.data.Robot
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val inputParser by lazy { InputParser(readAssetString("directions.txt")) }
    private var logMessages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log.movementMethod = ScrollingMovementMethod()

        executeEarthInstructions()
        displayLog()
    }

    private fun executeEarthInstructions() {
        inputParser.moveSequences.forEach { moveSequence ->
            val robot = Robot(moveSequence.startPosition, moveSequence.startOrientation)
            addLogLine(moveSequence.toString())

            moveSequence.commands.forEach {
                it.receiver = robot

                val oldStatus = robot.status
                it.execute()

                addLogLine("${it.javaClass.simpleName}: $oldStatus -> ${robot.status}")
            }

            addLogLine()
        }
    }

    private fun displayLog() {
        log.text = logMessages.joinToString(transform = { "$it \n" }, separator = "")
    }

    private fun addLogLine(line: String = "") {
        logMessages.add(line)
    }

    private fun readAssetString(fileName: String) =
        application.assets.open(fileName).bufferedReader().use { it.readText() }
}
