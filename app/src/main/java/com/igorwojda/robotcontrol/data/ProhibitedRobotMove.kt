package com.igorwojda.robotcontrol.data

import android.graphics.Point
import com.igorwojda.robotcontrol.enum.Orientation

data class ProhibitedRobotMove(
    val position: Point,
    val orientation: Orientation
)
