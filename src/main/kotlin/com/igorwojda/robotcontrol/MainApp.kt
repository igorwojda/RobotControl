package com.igorwojda.robotcontrol

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainApp : KoinComponent {
    private val engine: Engine by inject()

    init {
        val o1 = engine.processData(
            """
            5 3
            1 1 E
            RFRFRFRF
            """.trimIndent(),
        )

        val o2 = engine.processData(
            """
            5 3
            3 2 N
            FRRFLLFFRRFLL
            """.trimIndent(),
        )

        val o3 = engine.processData(
            """
            5 3
            0 3 W
            LLFFFLFLFL
            """.trimIndent(),
        )

        println(o1)
        println(o2)
        println(o3)
    }
}
