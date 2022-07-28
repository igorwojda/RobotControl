package com.igorwojda.robotcontrol.command

import kotlin.reflect.KClass

enum class CommandType(
    val code: Char,
    val clazz: KClass<out Command>
) {
    MOVE_FORWARD('F', MoveForwardCommand::class),
    TURN_LEFT('L', TurnLeftCommand::class),
    TURN_RIGHT('R', TurnRightCommand::class),
}
