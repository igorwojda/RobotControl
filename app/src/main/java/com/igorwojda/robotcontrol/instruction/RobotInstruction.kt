package com.igorwojda.robotcontrol.instruction

import com.igorwojda.robotcontrol.data.Robot

abstract class RobotInstruction {
    var receiver: Robot? = null

    abstract fun execute()
}
