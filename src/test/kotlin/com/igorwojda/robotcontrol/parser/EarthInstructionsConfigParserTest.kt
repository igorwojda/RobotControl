package com.igorwojda.robotcontrol.parser

import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.data.config.EarthInstructionsConfig
import com.igorwojda.robotcontrol.data.config.GridConfig
import com.igorwojda.robotcontrol.data.config.PositionAndOrientationConfig
import com.igorwojda.robotcontrol.enum.Orientation
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class EarthInstructionsConfigParserTest {

    private val positionAndOrientationConfigParser: PositionAndOrientationConfigParser = mockk()
    private val gridConfigParser: GridConfigParser = mockk()
    private val commandsConfigParser: CommandsConfigParser = mockk()

    private val sut = EarthInstructionsConfigParser(
        positionAndOrientationConfigParser,
        gridConfigParser,
        commandsConfigParser,
    )

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
    fun `throw exception when input does not have 3 lines`() {
        // given
        val input = "abc"

        // when
        val actual = { sut.parse(input) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage
            """
            Invalid input:
            Actual: abc
            Expected: X Y Z
            """.trimIndent()
    }

    @Test
    fun `parse correct input`() {
        // given
        val positionAndOrientationConfig = "positionAndOrientationConfig"
        val gridConfig = "gridConfig"
        val commandsConfig = "commandsConfig"
        val earthInstructionsConfig = "$gridConfig\n$positionAndOrientationConfig\n$commandsConfig"

        val commands: List<Command> = mockk()

        every { positionAndOrientationConfigParser.parse(positionAndOrientationConfig) } returns
            PositionAndOrientationConfig(1, 2, Orientation.E)

        every { gridConfigParser.parse(gridConfig) } returns GridConfig(3, 4)
        every { commandsConfigParser.parse(commandsConfig) } returns commands

        // when
        val actual = sut.parse(earthInstructionsConfig)

        // then
        actual shouldBeEqualTo EarthInstructionsConfig(
            1,
            2,
            Orientation.E,
            3,
            4,
            commands,
        )
    }
}
