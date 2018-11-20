package com.igorwojda.robotcontrol.data

import android.graphics.Point
import com.igorwojda.robotcontrol.enum.Orientation

data class ProhibitedRobotMove(
    val positionX: Int,
    val positionY: Int,
    val orientation: Orientation
) {
    val position = Point(positionX, positionY)
}
