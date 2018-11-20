package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.enum.Orientation

class RobotMoveForwardCommand : RobotMoveCommand() {
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
