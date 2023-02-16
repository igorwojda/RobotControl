package com.igorwojda.robotcontrol

import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.command.MoveForwardCommand
import com.igorwojda.robotcontrol.command.TurnRightCommand
import com.igorwojda.robotcontrol.data.Grid
import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class CommandProcessorTest {
    private val grid: Grid = mockk()

    private val sut = CommandProcessor()

    @Test
    fun `execute command`() {
        // given
        val robot = Robot(2, 2, Orientation.E)
        every { grid.width } returns 3
        every { grid.height } returns 2

        val command: MoveForwardCommand = mockk()
        every { command.execute(robot) } just runs

        // when
        sut.processCommands(robot, grid, listOf(command))

        // then
        verify { command.execute(robot) }
    }

    @Test
    fun `ignore forbidden command`() {
        // given
        val robot: Robot = mockk()
        val command: MoveForwardCommand = mockk()
        val commands = listOf<Command>()

        // when
        sut.processCommands(robot, grid, commands)

        // then
        verify(exactly = 0) { command.execute(robot) }
    }

    @Test
    fun `save 2nd robot`() {
        // given
        val robot1 = Robot(3, 2, Orientation.E)
        val robot2 = robot1.copy()
        every { grid.width } returns 3
        every { grid.height } returns 2
        val commands = listOf(MoveForwardCommand(), TurnRightCommand())

        // Run commands to lose robot1
        sut.processCommands(robot1, grid, commands)

        // when
        sut.processCommands(robot2, grid, commands)

        // then
        robot2 shouldBeEqualTo Robot(3, 2, Orientation.S)
    }
}
