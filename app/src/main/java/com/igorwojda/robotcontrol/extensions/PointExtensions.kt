package com.igorwojda.robotcontrol.extensions

import android.graphics.Point

operator fun Point.compareTo(other: Point): Int = when {
    y != other.y -> (y - other.y)
    else -> (x - other.x)
}
