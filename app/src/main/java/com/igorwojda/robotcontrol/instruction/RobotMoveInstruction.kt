package com.igorwojda.robotcontrol.instruction

abstract class RobotMoveInstruction : RobotInstruction() {
    abstract override fun execute()
}
