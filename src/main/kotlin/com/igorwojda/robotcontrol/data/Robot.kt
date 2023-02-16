package com.igorwojda.robotcontrol.data

import com.igorwojda.robotcontrol.enum.Orientation

data class Robot(
    var x: Int,
    var y: Int,
    var orientation: Orientation,
)
