package com.igorwojda.robotcontrol

import com.igorwojda.robotcontrol.data.CommandSequence
import com.igorwojda.robotcontrol.data.StartData
import com.igorwojda.robotcontrol.enum.Orientation
import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.data.Coordinate

class InputParser(str: String) {
    private val inputLines by lazy {
        str.split("\n")
    }

    val boardWidth: Int
    val boardHeight: Int

    init {
        val boardSizeLine = inputLines[0]
        val (width, height) = boardSizeLine
            .split(" ")
            .map { it.toInt() }

        require(width <= 50 && height <= 50) { "Maximum allowed board size is 50x50, current size ${width}x$height" }

        boardWidth = width
        boardHeight = height
    }

    private val boardSize by lazy {

    }

    val commandSequences by lazy {
        inputLines
            .drop(1) //drop board size line
            .filter { it.isNotBlank() }
            .chunked(2)
            .map {
                val (startDataLine, commandsLine) = it
                val startData = getStartData(startDataLine)
                val commands = getCommands(commandsLine)
                CommandSequence(startData.coordinate, startData.orientation, commands)
            }
    }

    private fun getCommands(str: String) = str.map { code ->
        require(str.length <= 100) {
            "Maximum number of commands allowed is 100, current number of commands: ${str.length}"
        }

        Command.valueOf(code)
    }

    private fun getStartData(str: String): StartData {
        val (x, y, orientation) = str.split(" ", limit = 3)
        val coordinate = Coordinate(x.toInt(), y.toInt())
        return StartData(coordinate, Orientation.valueOf(orientation))
    }
}
