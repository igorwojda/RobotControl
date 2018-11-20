package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.enum.Orientation.E
import com.igorwojda.robotcontrol.enum.Orientation.N
import com.igorwojda.robotcontrol.enum.Orientation.S
import com.igorwojda.robotcontrol.enum.Orientation.W

class RobotTurnLeftCommand : RobotCommand() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }.also {
            when (it.orientation) {
                N -> it.orientation = W
                S -> it.orientation = E
                E -> it.orientation = N
                W -> it.orientation = S
            }
        }
    }
}
