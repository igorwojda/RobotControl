package com.igorwojda.robotcontrol.data

import com.igorwojda.robotcontrol.enum.Orientation

data class Robot(
    var coordinateX: Int,
    var coordinateY: Int,
    var orientation: Orientation,
) {
    val coordinate get() = Coordinate(coordinateX, coordinateY)

    val status: String
        get() = "$coordinateX $coordinateY $orientation"

    constructor(coordinate: Coordinate, orientation: Orientation): this(
        coordinate.x,
        coordinate.y,
        orientation
    )
}
