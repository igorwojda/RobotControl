package com.igorwojda.robotcontrol.instruction

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class RobotMoveForwardInstructionTest {
    private var robot: Robot = mockk(relaxUnitFun = true)

    private var cut = RobotMoveForwardInstruction().apply {
        receiver = robot
    }

    @Test
    fun `given positionY 2 and orientation N when execute positionY 3`() {
        // given
        every { robot.orientation } returns Orientation.N
        every { robot.positionY } returns START_POSITION

        // when
        cut.execute()

        // then
        verify { robot.positionY  = 3 }
    }

    @Test
    fun `given positionY 2 and orientation S when execute positionY 1`() {
        // given
        every { robot.orientation } returns Orientation.S
        every { robot.positionY } returns START_POSITION

        // when
        cut.execute()

        // then
        verify { robot.positionY = START_POSITION - 1 }
    }

    @Test
    fun `given positionX 2 and orientation E when execute positionX 1`() {
        // given
        every { robot.orientation } returns Orientation.E
        every { robot.positionX } returns START_POSITION

        // when
        cut.execute()

        // then
        verify { robot.positionX = START_POSITION + 1 }
    }

    @Test
    fun `given positionX 2 and orientation W when execute positionX 1`() {
        // given
        every { robot.orientation } returns Orientation.W
        every { robot.positionX } returns START_POSITION

        // when
        cut.execute()

        // then
        verify { robot.positionX = START_POSITION - 1 }
    }

    companion object {
        private const val START_POSITION = 2
    }
}
