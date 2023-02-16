package com.igorwojda.robotcontrol.parser

import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.factory.CommandFactory

class CommandsConfigParser(
    private val commandFactory: CommandFactory,
) {
    fun parse(input: String): List<Command> {
        require(input.isNotBlank()) { "Input is blank" }

        return input
            .toList()
            .map { commandFactory.create(it) }
    }
}
