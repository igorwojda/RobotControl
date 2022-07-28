package com.igorwojda.robotcontrol

import android.graphics.Point
import com.igorwojda.robotcontrol.data.InstructionSequence
import com.igorwojda.robotcontrol.data.StartData
import com.igorwojda.robotcontrol.enum.Orientation
import com.igorwojda.robotcontrol.instruction.RobotMoveForwardInstruction
import com.igorwojda.robotcontrol.instruction.RobotTurnLeftInstruction
import com.igorwojda.robotcontrol.instruction.RobotTurnRightInstruction

class InputParser(str: String) {
    private val inputLines = str.split("\n")
    val boardSize by lazy {
        val (x, y) = inputLines[0]
            .split(" ", limit = 2)
            .map { it.toInt() }

        require(x <= 50 && y <= 50) { "Maximum allowed board size is 50x50, current size ${x}x$y" }
        Point(x , y)
    }

    val instructionSequences by lazy {
        inputLines
            .takeLast(inputLines.lastIndex)
            .filter { it.isNotBlank() }
            .chunked(2)
            .map {
                val startDataLine = it[0]
                val startData = getStartData(startDataLine)
                val commandsLine = it[1]
                val commands = getInstructions(commandsLine)
                InstructionSequence(startData.position, startData.orientation, commands)
            }
    }

    private fun getInstructions(str: String) = str.map {
        require(str.length <= 100) {
            "Maximum number of commands allowed is 100, current number of commands: ${str.length}"
        }

        when (it) {
            'F' -> RobotMoveForwardInstruction()
            'R' -> RobotTurnRightInstruction()
            'L' -> RobotTurnLeftInstruction()
            else -> throw RuntimeException("Unknown Instruction")
        }
    }

    private fun getStartData(str: String): StartData {
        val (x, y, orientation) = str.split(" ", limit = 3)
        val position = Point(x.toInt(), y.toInt())
        return StartData(position, Orientation.valueOf(orientation))
    }
}
