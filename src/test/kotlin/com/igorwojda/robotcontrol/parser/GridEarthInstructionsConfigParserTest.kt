package com.igorwojda.robotcontrol.parser

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class GridEarthInstructionsConfigParserTest {
    private val sut = GridConfigParser()

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
    fun `throw exception when gridSize is Invalid`() {
        // given
        val input = "72"

        // when
        val actual = { sut.parse(input) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Invalid grid size format: 72"
    }

    @Test
    fun `transform string '30 5' to grid width and grid height`() {
        // given
        val input = "30 5"

        // when
        val actual = sut.parse(input)

        // then
        actual.width shouldBeEqualTo 30
        actual.height shouldBeEqualTo 5
    }

    @Test
    fun `throw exception when width is 60 (it is too big)`() {
        // given
        val input = "60 5"

        // when
        val actual = { sut.parse(input) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Grid is to large. Max 50x50. Grid size is width 60, height 5"
    }
}
