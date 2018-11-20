package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.enum.Orientation

class RobotTurnLeftCommand : RobotCommand() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }.also {
            when (it.orientation) {
                Orientation.N -> it.orientation =
                    Orientation.W
                Orientation.S -> it.orientation =
                    Orientation.E
                Orientation.E -> it.orientation =
                    Orientation.N
                Orientation.W -> it.orientation =
                    Orientation.S
            }
        }
    }
}
