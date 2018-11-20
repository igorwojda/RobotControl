package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot

abstract class RobotCommand {
    var receiver: Robot? = null

    abstract fun execute()
}
