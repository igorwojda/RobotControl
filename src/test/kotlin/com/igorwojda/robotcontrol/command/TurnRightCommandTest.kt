package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.*
import org.junit.jupiter.params.provider.MethodSource

class TurnRightCommandTest {
    private var cut = TurnRightCommand()

    @ParameterizedTest(name = "given orientation {0} when execute then orientation {1}")
    @MethodSource("provideValues")
    fun `execute`(
        startOrientation: Orientation,
        endOrientation: Orientation,
    ) {
        // given
        val robot = Robot(
            orientation = startOrientation,
            x = 0,
            y = 0,
        )

        // when
        cut.execute(robot)

        // then
        robot.orientation shouldBeEqualTo endOrientation
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                Orientation.N,
                Orientation.E,
            ),
            arguments(
                Orientation.S,
                Orientation.W,
            ),
            arguments(
                Orientation.E,
                Orientation.S,
            ),
            arguments(
                Orientation.W,
                Orientation.N,
            ),
        )
    }
}
