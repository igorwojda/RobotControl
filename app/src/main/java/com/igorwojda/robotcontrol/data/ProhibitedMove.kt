package com.igorwojda.robotcontrol.data

import com.igorwojda.robotcontrol.enum.Orientation

data class ProhibitedMove(
    val coordinate: Coordinate,
    val orientation: Orientation,
)
