package com.igorwojda.robotcontrol.parser

import com.igorwojda.robotcontrol.enum.Orientation
import com.igorwojda.robotcontrol.factory.OrientationFactory
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class PositionAndOrientationConfigEarthInstructionsConfigParserTest {
    private val orientationFactory: OrientationFactory = mockk()

    private val sut = PositionAndOrientationConfigParser(
        orientationFactory,
    )

    @Test
    fun `throw exception when input is blank`() {
        // given
        val input = ""

        // when
        val actual = { sut.parse(input) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Input is blank"
    }

    @Test
    fun `throw exception when robot position is invalid`() {
        // given
        val input = "abc"

        // when
        val actual = { sut.parse(input) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Invalid robot position"
    }

    @Test
    fun `transform correct input`() {
        // given
        val robotPosition = "30 5 E"
        every { orientationFactory.create('E') } returns Orientation.E

        // when
        val actual = sut.parse(robotPosition)

        // then
        actual.positionX shouldBeEqualTo 30
        actual.positionY shouldBeEqualTo 5
        actual.orientation shouldBeEqualTo Orientation.E
    }
}
