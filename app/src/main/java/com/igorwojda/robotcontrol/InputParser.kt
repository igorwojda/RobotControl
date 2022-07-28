package com.igorwojda.robotcontrol

import android.graphics.Point
import com.igorwojda.robotcontrol.data.InstructionSequence
import com.igorwojda.robotcontrol.data.StartData
import com.igorwojda.robotcontrol.enum.Orientation
import com.igorwojda.robotcontrol.instruction.InstructionType
import com.igorwojda.robotcontrol.instruction.RobotInstruction
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

    val instructionSequences by lazy {
        inputLines
            .takeLast(inputLines.lastIndex)
            .filter { it.isNotBlank() }
            .chunked(2)
            .map {
                val (startDataLine, commandsLine) = it
                val startData = getStartData(startDataLine)
                val commands = getInstructions(commandsLine)
                InstructionSequence(startData.position, startData.orientation, commands)
            }
    }

    private fun getInstructions(str: String) = str.map { code ->
        require(str.length <= 100) {
            "Maximum number of commands allowed is 100, current number of commands: ${str.length}"
        }

        getInstruction(code)
    }

    private fun getInstruction(code: Char): RobotInstruction {
        val instructionType = getInstructionClass(code)
        val instructionConstructor = getParameterlessConstructor(instructionType)
        return instructionConstructor.call()
    }

    private fun getInstructionClass(code: Char) = requireNotNull(
            InstructionType.values().firstOrNull { it.code == code }
        ) {
            "Unknown instruction code: $code"
        }.clazz

    private fun getParameterlessConstructor(clazz: KClass<out RobotInstruction>) = requireNotNull(
            clazz.constructors.firstOrNull { it.parameters.isEmpty() }
        ) {
            "No parameterless constructor found for instruction type: ${clazz.qualifiedName}"
        }

    private fun getStartData(str: String): StartData {
        val (x, y, orientation) = str.split(" ", limit = 3)
        val position = Point(x.toInt(), y.toInt())
        return StartData(position, Orientation.valueOf(orientation))
    }
}
