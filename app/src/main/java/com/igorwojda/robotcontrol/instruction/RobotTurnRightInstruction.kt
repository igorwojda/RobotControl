package com.igorwojda.robotcontrol.instruction

import com.igorwojda.robotcontrol.enum.Orientation.E
import com.igorwojda.robotcontrol.enum.Orientation.N
import com.igorwojda.robotcontrol.enum.Orientation.S
import com.igorwojda.robotcontrol.enum.Orientation.W

class RobotTurnRightInstruction : RobotInstruction() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }.also {
            when (it.orientation) {
                N -> it.orientation = E
                S -> it.orientation = W
                E -> it.orientation = S
                W -> it.orientation = N
            }
        }
    }
}
