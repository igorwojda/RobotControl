package com.igorwojda.robotcontrol.command

import com.igorwojda.robotcontrol.data.Robot
import com.igorwojda.robotcontrol.enum.Orientation
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RobotTurnRightCommandTest {
    @Mock
    lateinit var mockRobot: Robot

    private var cut = RobotTurnRightCommand()

    @Test
    fun `given orientation N when execute orientation E`() {
        // given
        given(mockRobot.orientation).willReturn(Orientation.N)
        cut.receiver = mockRobot

        // when
        cut.execute()

        // then
        verify(mockRobot).orientation = Orientation.E
    }

    @Test
    fun `given orientation S when execute orientation W`() {
        // given
        given(mockRobot.orientation).willReturn(Orientation.S)
        cut.receiver = mockRobot

        // when
        cut.execute()

        // then
        verify(mockRobot).orientation = Orientation.W
    }


    @Test
    fun `given orientation E when execute orientation S`() {
        // given
        given(mockRobot.orientation).willReturn(Orientation.E)
        cut.receiver = mockRobot

        // when
        cut.execute()

        // then
        verify(mockRobot).orientation = Orientation.S
    }


    @Test
    fun `given orientation W when execute orientation N`() {
        // given
        given(mockRobot.orientation).willReturn(Orientation.W)
        cut.receiver = mockRobot

        // when
        cut.execute()

        // then
        verify(mockRobot).orientation = Orientation.N
    }
}
