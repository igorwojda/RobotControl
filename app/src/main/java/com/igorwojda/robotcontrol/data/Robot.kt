package com.igorwojda.robotcontrol.data

import com.igorwojda.robotcontrol.enum.Direction

data class Robot(
    val position: Pair<Int, Int>,
    val direction: Direction
)
