package com.igorwojda.robotcontrol.data

import android.graphics.Point
import com.igorwojda.robotcontrol.enum.Orientation

data class Robot(
    var positionX: Int,
    var positionY: Int,
    var orientation: Orientation,
) {
    val position = Point(positionX, positionY)

    val status: String
        get() = "$positionX $positionY $orientation"
}
