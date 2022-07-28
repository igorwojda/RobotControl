package com.igorwojda.robotcontrol.data

import android.graphics.Point
import com.igorwojda.robotcontrol.enum.Orientation

data class ProhibitedMove(
    val coordinateX: Int,
    val coordinateY: Int,
    val orientation: Orientation,
) {
    val coordinate = Point(coordinateX, coordinateY)
}
