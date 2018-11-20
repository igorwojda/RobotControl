package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RobotMoveForwardCommandTest {
    companion object {
        const val START_POSITION = 2
    }

    @Mock
    lateinit var mockRobot: Robot

    private var cut = RobotMoveForwardCommand()

    @Test
    fun `given positionY 2 and orientation N when execute positionY 3`() {
        // given
        given(mockRobot.orientation).willReturn(Orientation.N)
        given(mockRobot.positionY).willReturn(START_POSITION)
        cut.receiver = mockRobot

        // when
        cut.execute()

        // then
        verify(mockRobot).positionY = START_POSITION + 1
    }

    @Test
    fun `given positionY 2 and orientation S when execute positionY 1`() {
        // given
        given(mockRobot.orientation).willReturn(Orientation.S)
        given(mockRobot.positionY).willReturn(START_POSITION)
        cut.receiver = mockRobot

        // when
        cut.execute()

        // then
        verify(mockRobot).positionY = START_POSITION - 1
    }

    @Test
    fun `given positionX 2 and orientation E when execute positionX 1`() {
        // given
        given(mockRobot.orientation).willReturn(Orientation.E)
        given(mockRobot.positionX).willReturn(START_POSITION)
        cut.receiver = mockRobot

        // when
        cut.execute()

        // then
        verify(mockRobot).positionX = START_POSITION + 1
    }

    @Test
    fun `given positionX 2 and orientation W when execute positionX 1`() {
        // given
        given(mockRobot.orientation).willReturn(Orientation.W)
        given(mockRobot.positionX).willReturn(START_POSITION)
        cut.receiver = mockRobot

        // when
        cut.execute()

        // then
        verify(mockRobot).positionX = START_POSITION - 1
    }
}
