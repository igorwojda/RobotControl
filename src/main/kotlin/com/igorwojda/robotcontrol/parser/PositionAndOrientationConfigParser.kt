package com.igorwojda.robotcontrol.parser

import com.igorwojda.robotcontrol.data.config.PositionAndOrientationConfig
import com.igorwojda.robotcontrol.factory.OrientationFactory

class PositionAndOrientationConfigParser(
    private val orientationFactory: OrientationFactory,
) {
    fun parse(input: String): PositionAndOrientationConfig {
        require(input.isNotBlank()) { "Input is blank" }

        val robotPositionSplitWithSpace = input.split(" ")

        require(robotPositionSplitWithSpace.size == NUM_ITEMS) { "Invalid robot position" }

        val positionX = robotPositionSplitWithSpace[0].toInt()
        val positionY = robotPositionSplitWithSpace[1].toInt()

        val orientation = orientationFactory.create(robotPositionSplitWithSpace[2].first())

        return PositionAndOrientationConfig(positionX, positionY, orientation)
    }

    companion object {
        private const val NUM_ITEMS = 3
    }
}
