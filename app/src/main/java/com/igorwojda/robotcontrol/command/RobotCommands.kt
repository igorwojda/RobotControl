package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation

abstract class RobotCommand {
    val receiver: Robot? = null

    abstract fun execute()
}

class RobotMoveForwardCommand : RobotCommand() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }

        when (receiver.orientation) {
            Orientation.N -> receiver.position.y++
            Orientation.S -> receiver.position.y--
            Orientation.E -> receiver.position.x++
            Orientation.W -> receiver.position.x--
        }
    }
}

class RobotTurnRightCommand : RobotCommand() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }

        when (receiver.orientation) {
            Orientation.N -> receiver.orientation = Orientation.E
            Orientation.S -> receiver.orientation = Orientation.W
            Orientation.E -> receiver.orientation = Orientation.S
            Orientation.W -> receiver.orientation = Orientation.N
        }
    }
}

class RobotTurnLeftCommand : RobotCommand() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }

        when (receiver.orientation) {
            Orientation.N -> receiver.orientation = Orientation.W
            Orientation.S -> receiver.orientation = Orientation.E
            Orientation.E -> receiver.orientation = Orientation.N
            Orientation.W -> receiver.orientation = Orientation.S
        }
    }
}
