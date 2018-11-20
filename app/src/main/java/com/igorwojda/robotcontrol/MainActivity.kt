package com.igorwojda.robotcontrol

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import com.igorwojda.robotcontrol.command.RobotMoveForwardCommand
import com.igorwojda.robotcontrol.data.ProhibitedRobotMove
import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.extensions.compareTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val inputParser by lazy { InputParser(readAssetString("directions.txt")) }
    private var logMessages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log.movementMethod = ScrollingMovementMethod()

        addLogLine("Board size ${inputParser.boardSize.x}x${inputParser.boardSize.y} ")
        addLogLine()

        executeEarthInstructions()
        displayLog()
    }

    private fun executeEarthInstructions() {
        val prohibitedMoves = mutableListOf<ProhibitedRobotMove>()

        inputParser.moveSequences.forEach { moveSequence ->
            val robot = Robot(moveSequence.startPosition, moveSequence.startOrientation)
            addLogLine(moveSequence.toString())

            for(commandIndex in 0 until moveSequence.commands.size) {
                val command = moveSequence.commands[commandIndex]

                command.receiver = robot

                //one robot died here, so we want to save another
                if (command is RobotMoveForwardCommand
                    && prohibitedMoves.any { it.position == robot.position && it.orientation == robot.orientation }
                ) {
                    continue
                }

                val oldStatus = robot.status
                val oldPosition = robot.position
                val oldOrientation = robot.orientation

                command.execute()

                addLogLine("${command.javaClass.simpleName}: $oldStatus -> ${robot.status}")

                if (robot.position >= inputParser.boardSize) {
                    //mark this tile as dangerous, so other robots will survive
                    if (prohibitedMoves.none { it.position == oldPosition && it.orientation == oldOrientation }) {
                        prohibitedMoves.add(ProhibitedRobotMove(oldPosition, oldOrientation))
                    }

                    addLogLine("LOST")
                    break
                }
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
