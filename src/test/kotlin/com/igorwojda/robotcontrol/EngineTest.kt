package com.igorwojda.robotcontrol

import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.data.ForbiddenCommand
import com.igorwojda.robotcontrol.data.Grid
import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.data.config.EarthInstructionsConfig
import com.igorwojda.robotcontrol.enum.Orientation
import com.igorwojda.robotcontrol.factory.GridFactory
import com.igorwojda.robotcontrol.factory.RobotFactory
import com.igorwojda.robotcontrol.parser.EarthInstructionsConfigParser
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class EngineTest {
    private val earthInstructionsConfigParser: EarthInstructionsConfigParser = mockk()
    private val robotFactory: RobotFactory = mockk()
    private val gridFactory: GridFactory = mockk()
    private val commandProcessor: CommandProcessor = mockk()

    private val sut = Engine(
        earthInstructionsConfigParser,
        robotFactory,
        gridFactory,
        commandProcessor,
    )

    @Test
    fun `processCommands is executed`() {
        // given
        val robotX = 1
        val robotY = 2
        val robotOrientation = Orientation.N
        val gridWidth = 3
        val gridHeight = 4
        val commands: List<Command> = mockk()
        val forbiddenCommands = listOf<ForbiddenCommand>(mockk())
        val input = "input"

        every { commandProcessor.output } returns ""

        val earthInstructionsConfig: EarthInstructionsConfig = mockk()
        every { earthInstructionsConfig.gridWidth } returns 3
        every { earthInstructionsConfig.gridHeight } returns 4
        every { earthInstructionsConfig.commands } returns commands

        val grid: Grid = mockk()
        every { earthInstructionsConfigParser.parse(input) } returns earthInstructionsConfig
        every { gridFactory.create(gridWidth, gridHeight) } returns grid

        val robot = Robot(robotX, robotY, Orientation.N)
        every { robotFactory.create(earthInstructionsConfig) } returns robot
        every { commandProcessor.processCommands(robot, grid, commands) } just runs
        every { commandProcessor.forbiddenCommands } returns forbiddenCommands

        // when
        sut.processData(input)

        // then
        verify { commandProcessor.processCommands(robot, grid, commands) }
    }
}
