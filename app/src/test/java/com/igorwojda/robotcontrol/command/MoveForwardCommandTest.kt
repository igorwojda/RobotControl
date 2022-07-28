package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class MoveForwardCommandTest {
    private var robot: Robot = mockk(relaxUnitFun = true)

    private var cut = MoveForwardCommand().apply {
        receiver = robot
    }

    @Test
    fun `given coordinateY 2 and orientation N when execute coordinateY 3`() {
        // given
        every { robot.orientation } returns Orientation.N
        every { robot.coordinateY } returns START_POSITION

        // when
        cut.execute()

        // then
        verify { robot.coordinateY = 3 }
    }

    @Test
    fun `given coordinateY 2 and orientation S when execute coordinateY 1`() {
        // given
        every { robot.orientation } returns Orientation.S
        every { robot.coordinateY } returns START_POSITION

        // when
        cut.execute()

        // then
        verify { robot.coordinateY = START_POSITION - 1 }
    }

    @Test
    fun `given coordinateX 2 and orientation E when execute coordinateX 1`() {
        // given
        every { robot.orientation } returns Orientation.E
        every { robot.coordinateX } returns START_POSITION

        // when
        cut.execute()

        // then
        verify { robot.coordinateX = START_POSITION + 1 }
    }

    @Test
    fun `given coordinateX 2 and orientation W when execute coordinateX 1`() {
        // given
        every { robot.orientation } returns Orientation.W
        every { robot.coordinateX } returns START_POSITION

        // when
        cut.execute()

        // then
        verify { robot.coordinateX = START_POSITION - 1 }
    }

    companion object {
        private const val START_POSITION = 2
    }
}
