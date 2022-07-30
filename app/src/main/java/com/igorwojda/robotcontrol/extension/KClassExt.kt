package com.igorwojda.robotcontrol.extension

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

val <T : Any> KClass<T>.parameterlessConstructor: KFunction<T>?
    get() = constructors.firstOrNull { it.parameters.isEmpty() }
