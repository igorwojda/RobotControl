package com.igorwojda.robotcontrol.parser

import com.igorwojda.robotcontrol.command.MoveForwardCommand
import com.igorwojda.robotcontrol.command.TurnRightCommand
import com.igorwojda.robotcontrol.factory.CommandFactory
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class CommandsConfigParserTest {
    private val commandFactory: CommandFactory = mockk()

    private val sut = CommandsConfigParser(commandFactory)

    @Test
    fun `throw exception when input is blank`() {
        // given
        val input = ""

        // when
        val actual = { sut.parse(input) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Input is blank"
    }

    @Test
    fun `parse correct input`() {
        // given
        val input = "RF"
        val turnRightCommand: TurnRightCommand = mockk()
        val moveForwardCommand: MoveForwardCommand = mockk()
        every { commandFactory.create('R') } returns turnRightCommand
        every { commandFactory.create('F') } returns moveForwardCommand

        // when
        val actual = sut.parse(input)

        // then
        actual shouldBeEqualTo listOf(turnRightCommand, moveForwardCommand)
    }
}
