package com.igorwojda.robotcontrol.command

import com.google.common.truth.Truth.assertThat
import com.igorwojda.robotcontrol.data.Coordinate
import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TurnLeftCommandTest {
    private var cut = TurnLeftCommand()

    @ParameterizedTest(name = "given orientation {0} when execute then orientation {1}")
    @MethodSource("provideValues")
    fun `given orientation N when execute orientation W`(
        startOrientation: Orientation,
        endOrientation: Orientation,
    ) {
        // given
        val robot = Robot(
            orientation = startOrientation,
            coordinate = Coordinate(0, 0)
        )

        // when
        cut.execute(robot)

        // then
        assertThat(robot.orientation).isEqualTo(endOrientation)
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues(): List<Arguments> = listOf(
            Arguments.arguments(
                Orientation.N,
                Orientation.W,
            ),
            Arguments.arguments(
                Orientation.S,
                Orientation.E,
            ),
            Arguments.arguments(
                Orientation.E,
                Orientation.N,
            ),
            Arguments.arguments(
                Orientation.W,
                Orientation.S,
            ),
        )
    }
}
