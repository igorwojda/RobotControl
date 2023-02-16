package com.igorwojda.robotcontrol.parser

import com.igorwojda.robotcontrol.data.config.EarthInstructionsConfig

class EarthInstructionsConfigParser(
    private val positionAndOrientationConfigParser: PositionAndOrientationConfigParser,
    private val gridConfigParser: GridConfigParser,
    private val commandsConfigParser: CommandsConfigParser,
) {
    fun parse(input: String): EarthInstructionsConfig {
        require(input.isNotBlank()) { "Input is blank" }

        val splitMainInput = input.split("\n").toMutableList()
        require(splitMainInput.size == NUM_LINES) { "Invalid input:\nActual: $input\nExpected: X Y Z" }

        val gridSize = gridConfigParser.parse(splitMainInput[0])
        val positionAndOrientation = positionAndOrientationConfigParser.parse(splitMainInput[1])
        val commands = commandsConfigParser.parse(splitMainInput[2])

        return EarthInstructionsConfig(
            robotX = positionAndOrientation.positionX,
            robotY = positionAndOrientation.positionY,
            robotOrientation = positionAndOrientation.orientation,
            gridWidth = gridSize.width,
            gridHeight = gridSize.height,
            commands = commands,
        )
    }

    companion object {
        private const val NUM_LINES = 3
    }
}
