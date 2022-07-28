package com.igorwojda.robotcontrol.data

import android.graphics.Point
import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.enum.Orientation

data class CommandSequence(
    val startCoordinate: Point,
    val startOrientation: Orientation,
    val commands: List<Command>,
) {
    override fun toString() = "MoveSequence start coordinate: " +
        "${startCoordinate.x} ${startCoordinate.y} ${startOrientation.name}"
}
