package com.igorwojda.robotcontrol

import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.command.MoveForwardCommand
import com.igorwojda.robotcontrol.data.ProhibitedMove
import com.igorwojda.robotcontrol.data.Robot


class App {

    private val earthCommands by lazy {
        """
            5 3

            1 1 E
            RFRFRFRF

            3 2 N
            FRRFLLFFRRFLL

            0 3 W
            LLFFFLFLFL
        """.trimIndent()
    }

    init {
        displayListOfSupportedCommands()
        executeEarthCommands()
    }

    private fun executeEarthCommands() {
        val inputParser = InputParser(earthCommands)

        println("Board size ${inputParser.boardWidth}x${inputParser.boardHeight}")
        println()

        val prohibitedMoves = mutableSetOf<ProhibitedMove>()

        inputParser.commandSequences.forEach { commandSequence ->
            val robot = Robot(
                commandSequence.startCoordinate,
                commandSequence.startOrientation
            )
            println(commandSequence.toString())

            for (commandIndex in 0 until commandSequence.commands.size) {
                val command = commandSequence.commands[commandIndex]

                //one robot died here, so we want to save another
                if (command is MoveForwardCommand
                    && prohibitedMoves.any {
                        it.coordinate == robot.coordinate
                            && it.orientation == robot.orientation
                    }
                ) {
                    println("Robot was saved")
                    continue
                }

                val prevRobot = robot.copy()
                command.execute(robot)

                println("${command.javaClass.simpleName}: ${prevRobot.status} -> ${robot.status}")

                if (robot.coordinate.x > inputParser.boardWidth
                    || robot.coordinate.y > inputParser.boardHeight
                ) {
                    //mark this move as dangerous, so other robots will survive
                    println("Robot was lost")

                    val prohibitedMove = ProhibitedMove(prevRobot.coordinate, prevRobot.orientation)

                    if (prohibitedMoves.add(prohibitedMove)) {
                        println("Added $prohibitedMove")
                    }

                    break
                }
            }

            println()
        }
    }

    private fun displayListOfSupportedCommands() {
        println("Supported commands:")
        println()

        Command.values.forEach {
            println("${it.first} - ${it.second::class.simpleName}")
        }

        println()
        println()
    }
}
