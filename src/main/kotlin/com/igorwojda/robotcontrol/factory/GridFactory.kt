package com.igorwojda.robotcontrol.factory

import com.igorwojda.robotcontrol.data.Grid

class GridFactory {
    fun create(width: Int, height: Int) =
        Grid(width, height)
}
