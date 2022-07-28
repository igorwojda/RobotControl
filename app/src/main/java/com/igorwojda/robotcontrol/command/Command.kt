package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot

abstract class Command {
    var receiver: Robot? = null

    abstract fun execute()
}
