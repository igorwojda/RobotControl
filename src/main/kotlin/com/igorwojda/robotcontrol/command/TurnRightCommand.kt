package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation.E
import com.igorwojda.robotcontrol.enum.Orientation.N
import com.igorwojda.robotcontrol.enum.Orientation.S
import com.igorwojda.robotcontrol.enum.Orientation.W

class TurnRightCommand : Command('R') {
    override fun execute(robot: Robot) {
        when (robot.orientation) {
            N -> robot.orientation = E
            S -> robot.orientation = W
            E -> robot.orientation = S
            W -> robot.orientation = N
        }
    }
}
