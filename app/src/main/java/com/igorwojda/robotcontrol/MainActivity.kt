package com.igorwojda.robotcontrol

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.command.MoveForwardCommand
import com.igorwojda.robotcontrol.data.ProhibitedMove
import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.databinding.ActivityMainBinding
import com.igorwojda.robotcontrol.extension.readAssetAsString

class MainActivity : AppCompatActivity() {
    private val defaultDirections by lazy { application.readAssetAsString("directions.txt") }
    private val earthCommands: String
        get() = if (binding.commandsTextView.text.isNullOrBlank()) {
            defaultDirections
        } else {
            binding.commandsTextView.text.toString()
        }

    private var logMessages = mutableListOf<String>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.log.movementMethod = ScrollingMovementMethod()
        binding.commandsTextView.text = defaultDirections

        binding.runEarthCommandsButton.setOnClickListener {
            executeEarthCommands()
        }

        executeEarthCommands()
    }

    private fun initLog() {
        clearLog()
        displayListOfSupportedCommands()
    }

    private fun displayListOfSupportedCommands() {
        addLogLine("Supported commands:")
        addLogLine()

        Command.values.forEach {
            addLogLine("${it.first} - ${it.second::class.simpleName}")
        }

        addLogLine()
    }

    private fun executeEarthCommands() {
        initLog()

        val inputParser = InputParser(earthCommands)

        addLogLine("Board size ${inputParser.boardSize.x}x${inputParser.boardSize.y}")
        addLogLine()

        val prohibitedMoves = mutableSetOf<ProhibitedMove>()

        inputParser.commandSequences.forEach { commandSequence ->
            val robot = Robot(commandSequence.startCoordinate.x, commandSequence.startCoordinate.y, commandSequence.startOrientation)
            addLogLine(commandSequence.toString())

            for (commandIndex in 0 until commandSequence.commands.size) {
                val command = commandSequence.commands[commandIndex]

                //one robot died here, so we want to save another
                if (command is MoveForwardCommand
                    && prohibitedMoves.any {
                        it.coordinateX == robot.coordinateX
                            && it.coordinateY == robot.coordinateY
                            && it.orientation == robot.orientation
                    }
                ) {
                    addLogLine("Robot was saved")
                    continue
                }

                command.receiver = robot
                val prevRobot = robot.copy()
                command.execute()
                addLogLine("${command.javaClass.simpleName}: ${prevRobot.status} -> ${robot.status}")

                if (robot.coordinateX > inputParser.boardSize.x
                    || robot.coordinateY > inputParser.boardSize.y
                ) {
                    //mark this move as dangerous, so other robots will survive
                    addLogLine("Robot was lost")

                    val prohibitedMove = ProhibitedMove(
                        prevRobot.coordinateX,
                        prevRobot.coordinateY,
                        prevRobot.orientation
                    )

                    if (prohibitedMoves.add(prohibitedMove)) {
                        addLogLine("Added $prohibitedMove")
                    }

                    break
                }
            }

            addLogLine()
        }

        displayLog()
    }

    private fun displayLog() {
        binding.log.text = logMessages.joinToString(transform = { "$it \n" }, separator = "")
    }

    private fun clearLog() {
        logMessages.clear()
    }

    private fun addLogLine(line: String = "") {
        logMessages.add(line)
    }
}
