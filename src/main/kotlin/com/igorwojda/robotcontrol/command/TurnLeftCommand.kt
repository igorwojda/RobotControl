package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation.E
import com.igorwojda.robotcontrol.enum.Orientation.N
import com.igorwojda.robotcontrol.enum.Orientation.S
import com.igorwojda.robotcontrol.enum.Orientation.W

class TurnLeftCommand : Command('L') {
    override fun execute(robot: Robot) {
        when (robot.orientation) {
            N -> robot.orientation = W
            S -> robot.orientation = E
            E -> robot.orientation = N
            W -> robot.orientation = S
        }
    }
}
