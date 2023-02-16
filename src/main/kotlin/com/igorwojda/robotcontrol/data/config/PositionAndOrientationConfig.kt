package com.igorwojda.robotcontrol.data.config

import com.igorwojda.robotcontrol.enum.Orientation

data class PositionAndOrientationConfig(
    val positionX: Int,
    val positionY: Int,
    val orientation: Orientation,
)
