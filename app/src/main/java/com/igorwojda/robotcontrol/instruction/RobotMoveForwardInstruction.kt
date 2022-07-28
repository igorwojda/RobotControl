package com.igorwojda.robotcontrol.instruction

import com.igorwojda.robotcontrol.enum.Orientation.E
import com.igorwojda.robotcontrol.enum.Orientation.N
import com.igorwojda.robotcontrol.enum.Orientation.S
import com.igorwojda.robotcontrol.enum.Orientation.W

class RobotMoveForwardInstruction : RobotMoveInstruction() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }.also {
            when (it.orientation) {
                N -> it.positionY++
                S -> it.positionY--
                E -> it.positionX++
                W -> it.positionX--
            }
        }
    }
}
