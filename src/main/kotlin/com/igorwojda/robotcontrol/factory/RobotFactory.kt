package com.igorwojda.robotcontrol.factory

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.data.config.EarthInstructionsConfig

class RobotFactory {
    fun create(earthInstructionsConfig: EarthInstructionsConfig) = Robot(
        earthInstructionsConfig.robotX,
        earthInstructionsConfig.robotY,
        earthInstructionsConfig.robotOrientation,
    )
}
