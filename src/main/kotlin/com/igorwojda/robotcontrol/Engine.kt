package com.igorwojda.robotcontrol

import com.igorwojda.robotcontrol.data.ForbiddenCommand
import com.igorwojda.robotcontrol.data.Grid
import com.igorwojda.robotcontrol.factory.GridFactory
import com.igorwojda.robotcontrol.factory.RobotFactory
import com.igorwojda.robotcontrol.parser.EarthInstructionsConfigParser

class Engine(
    private val earthInstructionsConfigParser: EarthInstructionsConfigParser,
    private val robotFactory: RobotFactory,
    private val gridFactory: GridFactory,
    private val commandProcessor: CommandProcessor,
) {
    private var forbiddenCommands = mutableListOf<ForbiddenCommand>()
    private lateinit var grid: Grid

    fun processData(input: String): String {
        val earthInstructionsConfig = earthInstructionsConfigParser.parse(input)

        if (::grid.isInitialized) {
            require(grid.width == earthInstructionsConfig.gridWidth && grid.height == earthInstructionsConfig.gridHeight) {
                "Grid size must be the same:\nExpected: ${grid.width}x${grid.height}\nActual:${earthInstructionsConfig.gridWidth}x${earthInstructionsConfig.gridHeight}"
            }
        } else {
            grid = gridFactory.create(earthInstructionsConfig.gridWidth, earthInstructionsConfig.gridHeight)
        }

        val robot = robotFactory.create(earthInstructionsConfig)

        commandProcessor.processCommands(robot, grid, earthInstructionsConfig.commands)
        forbiddenCommands.addAll(commandProcessor.forbiddenCommands)

        return commandProcessor.output
    }
}
