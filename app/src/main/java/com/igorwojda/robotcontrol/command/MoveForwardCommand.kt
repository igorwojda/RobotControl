package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.enum.Orientation.E
import com.igorwojda.robotcontrol.enum.Orientation.N
import com.igorwojda.robotcontrol.enum.Orientation.S
import com.igorwojda.robotcontrol.enum.Orientation.W

class MoveForwardCommand : Command() {
    override fun execute() {
        requireNotNull(receiver) { "receiver is null" }.also {
            when (it.orientation) {
                N -> it.coordinateY++
                S -> it.coordinateY--
                E -> it.coordinateX++
                W -> it.coordinateX--
            }
        }
    }
}
