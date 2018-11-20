package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.enum.Orientation

class RobotTurnRightCommand : RobotCommand() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }.also {
            when (it.orientation) {
                Orientation.N -> it.orientation =
                    Orientation.E
                Orientation.S -> it.orientation =
                    Orientation.W
                Orientation.E -> it.orientation =
                    Orientation.S
                Orientation.W -> it.orientation =
                    Orientation.N
            }
        }
    }
}
