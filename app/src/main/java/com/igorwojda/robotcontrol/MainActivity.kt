package com.igorwojda.robotcontrol

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import com.igorwojda.robotcontrol.command.RobotMoveCommand
import com.igorwojda.robotcontrol.data.ProhibitedRobotMove
import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.extensions.readAssetAsString
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val defaultDirections by lazy { application.readAssetAsString("directions.txt") }
    private val earthInstructions: String
        get() = if (instructionsTextView.text.isNullOrBlank()) defaultDirections else instructionsTextView.text.toString()
    private var logMessages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log.movementMethod = ScrollingMovementMethod()

        instructionsTextView.text = defaultDirections
        runEarthInstructionsButton.setOnClickListener { executeEarthInstructions() }

        executeEarthInstructions()
    }

    private fun executeEarthInstructions() {
        clearLog()
        val inputParser = InputParser(earthInstructions)

        addLogLine("Board size ${inputParser.boardSize.x}x${inputParser.boardSize.y}")
        addLogLine()

        val prohibitedMoves = mutableListOf<ProhibitedRobotMove>()

        inputParser.moveSequences.forEach { moveSequence ->
            val robot = Robot(moveSequence.startPosition.x, moveSequence.startPosition.y, moveSequence.startOrientation)
            addLogLine(moveSequence.toString())

            for (commandIndex in 0 until moveSequence.commands.size) {
                val command = moveSequence.commands[commandIndex]

                //one robot died here, so we want to save another
                if (command is RobotMoveCommand
                    && prohibitedMoves.any { it.positionX == robot.positionX
                        && it.positionY == robot.positionY
                        && it.orientation == robot.orientation }
                ) {
                    addLogLine("Robot was saved")
                    continue
                }

                command.receiver = robot
                val oldRobot = robot.copy()
                command.execute()
                addLogLine("${command.javaClass.simpleName}: ${oldRobot.status} -> ${robot.status}")

                if (robot.positionX > inputParser.boardSize.x
                || robot.positionY > inputParser.boardSize.y) {
                    //mark this tile as dangerous, so other robots will survive
                    addLogLine("Robot was lost")

                    if (prohibitedMoves.none { it.position == oldRobot.position && it.orientation == oldRobot.orientation }) {
                        ProhibitedRobotMove(oldRobot.positionX, oldRobot.positionY, oldRobot.orientation).also {
                            prohibitedMoves.add(it)
                            addLogLine("Added $it")
                        }
                    }
                    break
                }
            }

            addLogLine()
        }

        displayLog()
    }

    private fun displayLog() {
        log.text = logMessages.joinToString(transform = { "$it \n" }, separator = "")
    }

    private fun clearLog() {
        logMessages.clear()
    }

    private fun addLogLine(line: String = "") {
        logMessages.add(line)
    }
}
