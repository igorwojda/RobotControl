package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TurnRightCommandTest {
    private var robot: Robot = mockk(relaxUnitFun = true)

    private var cut = TurnRightCommand().apply {
        receiver = robot
    }

    @ParameterizedTest(name = "given orientation {0} when execute then orientation {1}")
    @MethodSource("provideValues")
    fun `given orientation N when execute orientation W`(
        startOrientation: Orientation,
        endOrientation: Orientation,
    ) {
        // given
        every { robot.orientation } returns startOrientation

        // when
        cut.execute()

        // then
        verify { robot.orientation = endOrientation }
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues(): List<Arguments> = listOf(
            Arguments.arguments(
                Orientation.N,
                Orientation.E,
            ),
            Arguments.arguments(
                Orientation.S,
                Orientation.W,
            ),
            Arguments.arguments(
                Orientation.E,
                Orientation.S,
            ),
            Arguments.arguments(
                Orientation.W,
                Orientation.N,
            ),
        )
    }
}
