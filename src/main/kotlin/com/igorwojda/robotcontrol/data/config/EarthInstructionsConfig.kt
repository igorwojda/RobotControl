package com.igorwojda.robotcontrol.data.config

import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.enum.Orientation

data class EarthInstructionsConfig(
    val robotX: Int,
    val robotY: Int,
    val robotOrientation: Orientation,
    val gridWidth: Int,
    val gridHeight: Int,
    val commands: List<Command>,
)
