package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.ext.parameterlessConstructor
import kotlin.reflect.full.primaryConstructor

sealed class Command(
    val code: Char
) {
    abstract fun execute(robot: Robot)

    override fun toString(): String {
        return "Command $code"
    }

    companion object {

        private val map by lazy {
            Command::class.sealedSubclasses
                .mapNotNull { clazz -> clazz.primaryConstructor?.call() }
                .associateBy { value -> value.code }
        }

        fun valueOf(code: Char): Command {
            val cachedCommand = requireNotNull(map[code]) {
                "No command found for the code $code"
            }

            return getNewInstance(cachedCommand)
        }

        private fun getNewInstance(command: Command): Command {
            val clazz = command::class
            val constructor = clazz.parameterlessConstructor

            requireNotNull(constructor) {
                "No parameterless constructor found for the command ${clazz.simpleName}"
            }

            return constructor.call()
        }
    }
}
