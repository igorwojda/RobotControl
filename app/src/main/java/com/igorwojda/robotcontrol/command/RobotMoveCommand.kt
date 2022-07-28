package com.igorwojda.robotcontrol.command

abstract class RobotMoveCommand : RobotCommand() {
    abstract override fun execute()
}
