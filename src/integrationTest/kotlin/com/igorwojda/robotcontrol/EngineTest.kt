package com.igorwojda.robotcontrol

import com.igorwojda.robotcontrol.di.appModule
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.core.component.inject
import org.koin.test.KoinTest
import org.koin.test.junit5.KoinTestExtension

class EngineTest : KoinTest {
    @JvmField
    @RegisterExtension
    val koinTestExtension = KoinTestExtension.create {
        modules(appModule)
    }

    private val engine: Engine by inject()

    @Test
    fun `test robot moves`() {
        // given
        val input = """
            5 3
            1 1 E
            RFRFRFRF
        """.trimIndent()

        // when
        val actual = engine.processData(input)

        // then
        actual shouldBeEqualTo "1 1 E"
    }

    @Test
    fun `test robot moves when robot is lost and saved`() {
        // given
        val input1 = """
            5 3
            3 2 N
            FRRFLLFFRRFLL
        """.trimIndent()

        // when

        val actual1 = engine.processData(input1)

        // then
        actual1 shouldBeEqualTo "3 3 N LOST"

        // given
        val input2 = """
            5 3
            0 3 W
            LLFFFLFLFL
        """.trimIndent()

        // when

        val actual2 = engine.processData(input2)

        // then
        actual2 shouldBeEqualTo "2 3 S"
    }
}
