package com.igorwojda.robotcontrol.command

import com.google.common.truth.Truth.assertThat
import com.igorwojda.robotcontrol.data.Coordinate
import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class MoveForwardCommandTest {
    private var cut = MoveForwardCommand()

    @ParameterizedTest(name = "given {1}, orientation {0} when execute then position {2}")
    @MethodSource("provideValues")
    fun `execute`(
        orientation: Orientation,
        startCoordinate: Coordinate,
        endCoordinate: Coordinate,
    ) {
        // given
        val robot = Robot(
            orientation = orientation,
            coordinate = startCoordinate
        )
        cut.receiver = robot

        // when
        cut.execute()

        // then
        assertThat(robot.coordinate.x).isEqualTo(endCoordinate.x)
        assertThat(robot.coordinate.y).isEqualTo(endCoordinate.y)
    }

    companion object {
        private const val START_COORDINATE_X = 2
        private const val START_COORDINATE_Y = 4

        @Suppress("unused")
        @JvmStatic
        fun provideValues(): List<Arguments> = listOf(
            arguments(
                Orientation.N,
                Coordinate(START_COORDINATE_X, START_COORDINATE_Y),
                Coordinate(START_COORDINATE_X, START_COORDINATE_Y + 1)
            ),
            arguments(
                Orientation.S,
                Coordinate(START_COORDINATE_X, START_COORDINATE_Y),
                Coordinate(START_COORDINATE_X, START_COORDINATE_Y - 1)
            ),
            arguments(
                Orientation.E,
                Coordinate(START_COORDINATE_X, START_COORDINATE_Y),
                Coordinate(START_COORDINATE_X + 1, START_COORDINATE_Y)
            ),
            arguments(
                Orientation.W,
                Coordinate(START_COORDINATE_X, START_COORDINATE_Y),
                Coordinate(START_COORDINATE_X - 1, START_COORDINATE_Y)
            ),
        )
    }
}
