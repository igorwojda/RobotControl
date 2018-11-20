package com.igorwojda.robotcontrol

import android.graphics.Point
import com.igorwojda.robotcontrol.command.RobotMoveForwardCommand
import com.igorwojda.robotcontrol.command.RobotTurnLeftCommand
import com.igorwojda.robotcontrol.command.RobotTurnRightCommand
import com.igorwojda.robotcontrol.data.MoveSequence
import com.igorwojda.robotcontrol.data.StartData
import com.igorwojda.robotcontrol.enum.Orientation

class InputParser(str: String) {
    private val inputLines = str.split("\n")
    val boardSize by lazy {
        val (x, y) = inputLines[0]
            .split(" ", limit = 2)
            .map { it.toInt() }

        Point(x, y)
    }

    val moveSequences by lazy {
        inputLines
            .takeLast(inputLines.lastIndex)
            .filter { it.isNotBlank() }
            .chunked(2)
            .map {
                val startData = getStartData(it[0])
                val commands = getCommands(it[1])
                MoveSequence(startData.position, startData.orientation, commands)
            }
    }

    private fun getCommands(str: String) = str.map {
        when (it) {
            'F' -> RobotMoveForwardCommand()
            'R' -> RobotTurnRightCommand()
            'L' -> RobotTurnLeftCommand()
            else -> throw RuntimeException("Unknown Command")
        }
    }

    private fun getStartData(str: String): StartData {
        val (x, y, orientation) = str.split(" ", limit = 3)
        val position = Point(x.toInt(), y.toInt())
        return StartData(position, Orientation.valueOf(orientation))
    }
}
