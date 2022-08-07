package com.igorwojda.robotcontrol.data

import com.igorwojda.robotcontrol.enum.Orientation

data class StartData(
    val coordinate: Coordinate,
    val orientation: Orientation,
)
