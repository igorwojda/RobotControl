package com.igorwojda.robotcontrol.factory

import com.igorwojda.robotcontrol.command.Command

class CommandFactory {
    fun create(code: Char) = Command.valueOf(code)
}
