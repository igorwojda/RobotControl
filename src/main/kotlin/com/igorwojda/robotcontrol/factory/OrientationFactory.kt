package com.igorwojda.robotcontrol.factory

import com.igorwojda.robotcontrol.enum.Orientation

class OrientationFactory {
    fun create(code: Char) = Orientation.values().firstOrNull { it.code == code }
        ?: throw IllegalArgumentException("Invalid orientation: $code")
}
