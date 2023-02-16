package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation.E
import com.igorwojda.robotcontrol.enum.Orientation.N
import com.igorwojda.robotcontrol.enum.Orientation.S
import com.igorwojda.robotcontrol.enum.Orientation.W

class MoveForwardCommand : Command('F') {
    override fun execute(robot: Robot) {
        when (robot.orientation) {
            N -> robot.y++
            S -> robot.y--
            E -> robot.x++
            W -> robot.x--
        }
    }
}
