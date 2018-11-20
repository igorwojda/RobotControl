package com.igorwojda.robotcontrol.data

import android.graphics.Point
import com.igorwojda.robotcontrol.enum.Orientation

data class Robot(
    val position: Point,
    var orientation: Orientation
) {
    val status: String
     get() = "${position.x} ${position.y} $orientation"
}
