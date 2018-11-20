package com.igorwojda.robotcontrol.data

import android.graphics.Point
import com.igorwojda.robotcontrol.command.RobotCommand
import com.igorwojda.robotcontrol.enum.Orientation

data class MoveSequence (
    val startPosition: Point,
    val startOrientation: Orientation,
    val commands: List<RobotCommand>
) {
    override fun toString() = "MoveSequence: ${startPosition.x} ${startPosition.y} ${startOrientation.name}"
}
