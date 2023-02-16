package com.igorwojda.robotcontrol

import com.igorwojda.robotcontrol.command.Command
import com.igorwojda.robotcontrol.data.ForbiddenCommand
import com.igorwojda.robotcontrol.data.Grid
import com.igorwojda.robotcontrol.data.Robot

class CommandProcessor() {
    private var _forbiddenCommands = mutableSetOf<ForbiddenCommand>()
    val forbiddenCommands get() = _forbiddenCommands.toList()
    private var isLost = false
    private var lastValidRobot: Robot? = null

    val output: String
        get() {
            val localLastValidRobot = lastValidRobot

            return if (localLastValidRobot != null) {
                var output = "${localLastValidRobot.x} ${localLastValidRobot.y} ${localLastValidRobot.orientation.code}"

                if (isLost) {
                    output += " LOST"
                }

                output
            } else {
                ""
            }
        }

    fun processCommands(robot: Robot, grid: Grid, commands: List<Command>) {
        isLost = false

        commands.forEach {
            val beforeRobot = robot.copy()

            if (!isForbiddenCommand(robot, it)) {
                it.execute(robot)

                if (isRobotLost(robot, grid)) {
                    val forbiddenCommand =
                        ForbiddenCommand(
                            beforeRobot.x,
                            beforeRobot.y,
                            beforeRobot.orientation,
                            it::class,
                        )

                    _forbiddenCommands.add(forbiddenCommand)
                    isLost = true
                    println("Add forbidden command: $forbiddenCommand")
                    return
                }
            }

            lastValidRobot = robot.copy()
        }
    }

    private fun isForbiddenCommand(
        robot: Robot,
        command: Command,
    ): Boolean {
        val forbiddenCommand = ForbiddenCommand(robot.x, robot.y, robot.orientation, command::class)
        return _forbiddenCommands.contains(forbiddenCommand)
    }

    private fun isRobotLost(robot: Robot, grid: Grid) =
        robot.x < 0 ||
            robot.x >= grid.width ||
            robot.y < 0 ||
            robot.y > grid.height
}
