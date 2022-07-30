package com.igorwojda.robotcontrol.data

import com.igorwojda.robotcontrol.enum.Orientation

data class Robot(
    var coordinate: Coordinate,
    var orientation: Orientation,
) {
    var coordinateX : Int
        get() {
            return coordinate.x
        }
        set(value) {
            coordinate = coordinate.copy(x = value)
        }

    var coordinateY : Int
        get() {
            return coordinate.y
        }
        set(value) {
            coordinate = coordinate.copy(y = value)
        }

    val status: String
        get() = "${coordinate.x} ${coordinate.y} $orientation"
}
