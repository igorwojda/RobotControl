package com.igorwojda.robotcontrol.instruction

import com.igorwojda.robotcontrol.enum.Orientation.E
import com.igorwojda.robotcontrol.enum.Orientation.N
import com.igorwojda.robotcontrol.enum.Orientation.S
import com.igorwojda.robotcontrol.enum.Orientation.W

class RobotTurnLeftInstruction : RobotInstruction() {
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
