package com.igorwojda.robotcontrol.data

import com.igorwojda.robotcontrol.enum.Orientation

data class Robot(
    var coordinateX: Int,
    var coordinateY: Int,
    var orientation: Orientation,
) {
    val coordinate = Coordinate(coordinateX, coordinateY)

    val status: String
        get() = "$coordinateX $coordinateY $orientation"
}
