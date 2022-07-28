package com.igorwojda.robotcontrol.data

import android.graphics.Point
import com.igorwojda.robotcontrol.instruction.RobotInstruction
import com.igorwojda.robotcontrol.enum.Orientation

data class InstructionSequence (
    val startPosition: Point,
    val startOrientation: Orientation,
    val commands: List<RobotInstruction>
) {
    override fun toString() = "MoveSequence start position: " +
        "${startPosition.x} ${startPosition.y} ${startOrientation.name}"
}
