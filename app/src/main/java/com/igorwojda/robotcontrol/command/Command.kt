package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

sealed class Command(
    val code: Char,
) {
    var receiver: Robot? = null

    abstract fun execute()

    companion object {

        // sealedSubclasses property has bug with incremental compile
        // https://youtrack.jetbrains.com/issue/KT-46906
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
            val constructor = getParameterlessConstructor(clazz)
            return constructor.call()
        }

        private fun getParameterlessConstructor(clazz: KClass<out Command>) = requireNotNull(
            clazz.constructors.firstOrNull { it.parameters.isEmpty() }
        ) {
            "No parameterless constructor found for command type: ${clazz.qualifiedName}"
        }

        val values = map.toList()
    }
}
