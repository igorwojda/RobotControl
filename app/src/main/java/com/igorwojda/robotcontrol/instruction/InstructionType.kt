package com.igorwojda.robotcontrol.instruction

import kotlin.reflect.KClass

enum class InstructionType(
    val code: Char,
    val clazz: KClass<out RobotInstruction>
) {
    MOVE_FORWARD('F', RobotMoveForwardInstruction::class),
    TURN_LEFT('L', RobotTurnLeftInstruction::class),
    TURN_RIGHT('R', RobotTurnRightInstruction::class),
}
