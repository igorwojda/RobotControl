package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class MoveForwardCommandTest {
    private var cut = MoveForwardCommand()

    @ParameterizedTest(name = "given {1}x{2}, orientation {0} when execute then position {2}x{3}")
    @MethodSource("provideValues")
    fun `execute`(
        orientation: Orientation,
        startPositionX: Int,
        startPositionY: Int,
        endPositionX: Int,
        endPositionY: Int,
    ) {
        // given
        val robot = Robot(
            orientation = orientation,
            x = startPositionX,
            y = startPositionY,
        )

        // when
        cut.execute(robot)

        // then
        robot.x shouldBeEqualTo endPositionX
        robot.y shouldBeEqualTo endPositionY
    }

    companion object {
        private const val START_COORDINATE_X = 2
        private const val START_COORDINATE_Y = 4

        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                Orientation.N,
                START_COORDINATE_X,
                START_COORDINATE_Y,
                START_COORDINATE_X,
                START_COORDINATE_Y + 1,
            ),
            arguments(
                Orientation.S,
                START_COORDINATE_X,
                START_COORDINATE_Y,
                START_COORDINATE_X,
                START_COORDINATE_Y - 1,
            ),
            arguments(
                Orientation.E,
                START_COORDINATE_X,
                START_COORDINATE_Y,
                START_COORDINATE_X + 1,
                START_COORDINATE_Y,
            ),
            arguments(
                Orientation.W,
                START_COORDINATE_X,
                START_COORDINATE_Y,
                START_COORDINATE_X - 1,
                START_COORDINATE_Y,
            ),
        )
    }
}
