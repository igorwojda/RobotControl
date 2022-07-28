package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class TurnRightCommandTest {
    private var robot: Robot = mockk(relaxUnitFun = true)

    private var cut = TurnRightCommand().apply {
        receiver = robot
    }

    @Test
    fun `given orientation N when execute orientation E`() {
        // given
        every { robot.orientation }  returns Orientation.N

        // when
        cut.execute()

        // then
        verify { robot.orientation = Orientation.E }
    }

    @Test
    fun `given orientation S when execute orientation W`() {
        // given
        every { robot.orientation }  returns Orientation.S

        // when
        cut.execute()

        // then
        verify { robot.orientation = Orientation.W }
    }


    @Test
    fun `given orientation E when execute orientation S`() {
        // given
        every { robot.orientation }  returns Orientation.E

        // when
        cut.execute()

        // then
        verify { robot.orientation = Orientation.S }
    }


    @Test
    fun `given orientation W when execute orientation N`() {
        // given
        every { robot.orientation }  returns Orientation.W

        // when
        cut.execute()

        // then
        verify { robot.orientation = Orientation.N }
    }
}
