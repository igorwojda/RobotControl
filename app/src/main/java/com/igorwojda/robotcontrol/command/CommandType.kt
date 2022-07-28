package com.igorwojda.robotcontrol.command

import kotlin.reflect.KClass

enum class CommandType(
    val code: Char,
    val clazz: KClass<out RobotCommand>
) {
    MOVE_FORWARD('F', RobotMoveForwardCommand::class),
    TURN_LEFT('L', RobotTurnLeftCommand::class),
    TURN_RIGHT('R', RobotTurnRightCommand::class),
}
