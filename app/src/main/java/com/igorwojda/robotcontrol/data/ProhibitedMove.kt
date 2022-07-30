package com.igorwojda.robotcontrol.data

import android.graphics.Point
import com.igorwojda.robotcontrol.enum.Orientation

data class ProhibitedMove(
    val coordinate: Point,
    val orientation: Orientation,
)
