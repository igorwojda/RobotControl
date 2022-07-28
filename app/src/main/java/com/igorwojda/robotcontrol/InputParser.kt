package com.igorwojda.robotcontrol

import android.graphics.Point
import com.igorwojda.robotcontrol.data.CommandSequence
import com.igorwojda.robotcontrol.data.StartData
import com.igorwojda.robotcontrol.enum.Orientation
import com.igorwojda.robotcontrol.command.CommandType
import com.igorwojda.robotcontrol.command.Command
import kotlin.reflect.KClass

class InputParser(str: String) {
    private val inputLines = str.split("\n")

    val boardSize by lazy {
        val (x, y) = inputLines[0]
            .split(" ", limit = 2)
            .map { it.toInt() }

        require(x <= 50 && y <= 50) { "Maximum allowed board size is 50x50, current size ${x}x$y" }
        Point(x, y)
    }

    val commandSequences by lazy {
        inputLines
            .takeLast(inputLines.lastIndex)
            .filter { it.isNotBlank() }
            .chunked(2)
            .map {
                val (startDataLine, commandsLine) = it
                val startData = getStartData(startDataLine)
                val commands = getInstructions(commandsLine)
                CommandSequence(startData.coordinate, startData.orientation, commands)
            }
    }

    private fun getInstructions(str: String) = str.map { code ->
        require(str.length <= 100) {
            "Maximum number of commands allowed is 100, current number of commands: ${str.length}"
        }

        getInstruction(code)
    }

    private fun getInstruction(code: Char): Command {
        val commandType = getInstructionClass(code)
        val commandConstructor = getParameterlessConstructor(commandType)
        return commandConstructor.call()
    }

    private fun getInstructionClass(code: Char) = requireNotNull(
        CommandType.values().firstOrNull { it.code == code }
    ) {
        "Unknown command code: $code"
    }.clazz

    private fun getParameterlessConstructor(clazz: KClass<out Command>) = requireNotNull(
        clazz.constructors.firstOrNull { it.parameters.isEmpty() }
    ) {
        "No parameterless constructor found for command type: ${clazz.qualifiedName}"
    }

    private fun getStartData(str: String): StartData {
        val (x, y, orientation) = str.split(" ", limit = 3)
        val coordinate = Point(x.toInt(), y.toInt())
        return StartData(coordinate, Orientation.valueOf(orientation))
    }
}
