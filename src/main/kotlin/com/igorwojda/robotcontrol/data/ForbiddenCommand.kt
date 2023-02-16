package com.igorwojda.robotcontrol.data

import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.enum.Orientation
import kotlin.reflect.KClass

data class ForbiddenCommand(
    val positionX: Int,
    val positionY: Int,
    val orientation: Orientation,
    val command: KClass<out Command>,
)
