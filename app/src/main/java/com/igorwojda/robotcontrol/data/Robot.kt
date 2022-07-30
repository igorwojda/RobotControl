package com.igorwojda.robotcontrol.data

import com.igorwojda.robotcontrol.enum.Orientation

data class Robot(
    var coordinate: Coordinate,
    var orientation: Orientation,
) {
    var coordinateX : Int by coordinate::x
    var coordinateY : Int by coordinate::y

    val status: String
        get() = "${coordinate.x} ${coordinate.y} $orientation"
}
