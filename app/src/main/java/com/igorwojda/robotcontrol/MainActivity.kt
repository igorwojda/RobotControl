package com.igorwojda.robotcontrol

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import com.igorwojda.robotcontrol.command.RobotMoveCommand
import com.igorwojda.robotcontrol.data.ProhibitedRobotMove
import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.extensions.compareTo
import com.igorwojda.robotcontrol.extensions.readAssetAsString
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val inputParser by lazy { InputParser(application.readAssetAsString("directions.txt")) }
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
            val robot = Robot(moveSequence.startPosition.x, moveSequence.startPosition.y, moveSequence.startOrientation)
            addLogLine(moveSequence.toString())

            for(commandIndex in 0 until moveSequence.commands.size) {
                val command = moveSequence.commands[commandIndex]

                //one robot died here, so we want to save another
                if (command is RobotMoveCommand
                    && prohibitedMoves.any { it.position == robot.position && it.orientation == robot.orientation }
                ) {
                    addLogLine("Robot was saved")
                    continue
                }

                command.receiver = robot
                val oldRobot = robot.copy()
                command.execute()

                addLogLine("${command.javaClass.simpleName}: ${oldRobot.status} -> ${robot.status}")

                if (robot.position >= inputParser.boardSize) {
                    //mark this tile as dangerous, so other robots will survive
                    if (prohibitedMoves.none { it.position == oldRobot.position && it.orientation == oldRobot.orientation }) {
                        prohibitedMoves.add(ProhibitedRobotMove(oldRobot.position, oldRobot.orientation))
                    }

                    addLogLine("Robot was lost")
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
}
