package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation

abstract class RobotCommand {
    var receiver: Robot? = null

    abstract fun execute()
}

class RobotMoveForwardCommand : RobotCommand() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }.also {
            when (it.orientation) {
                Orientation.N -> it.position.y++
                Orientation.S -> it.position.y--
                Orientation.E -> it.position.x++
                Orientation.W -> it.position.x--
            }
        }
    }
}

class RobotTurnRightCommand : RobotCommand() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }.also {
            when (it.orientation) {
                Orientation.N -> it.orientation = Orientation.E
                Orientation.S -> it.orientation = Orientation.W
                Orientation.E -> it.orientation = Orientation.S
                Orientation.W -> it.orientation = Orientation.N
            }
        }
    }
}

class RobotTurnLeftCommand : RobotCommand() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }.also {
            when (it.orientation) {
                Orientation.N -> it.orientation = Orientation.W
                Orientation.S -> it.orientation = Orientation.E
                Orientation.E -> it.orientation = Orientation.N
                Orientation.W -> it.orientation = Orientation.S
            }
        }
    }
}
