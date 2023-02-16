package com.igorwojda.robotcontrol.di

import com.igorwojda.robotcontrol.CommandProcessor
import com.igorwojda.robotcontrol.Engine
import com.igorwojda.robotcontrol.command.MoveForwardCommand
import com.igorwojda.robotcontrol.command.TurnLeftCommand
import com.igorwojda.robotcontrol.command.TurnRightCommand
import com.igorwojda.robotcontrol.factory.CommandFactory
import com.igorwojda.robotcontrol.factory.GridFactory
import com.igorwojda.robotcontrol.factory.OrientationFactory
import com.igorwojda.robotcontrol.factory.RobotFactory
import com.igorwojda.robotcontrol.parser.CommandsConfigParser
import com.igorwojda.robotcontrol.parser.EarthInstructionsConfigParser
import com.igorwojda.robotcontrol.parser.GridConfigParser
import com.igorwojda.robotcontrol.parser.PositionAndOrientationConfigParser
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

var appModule = module {
    singleOf(::Engine)
    singleOf(::CommandProcessor)
    singleOf(::CommandProcessor)
    singleOf(::TurnRightCommand)
    singleOf(::TurnLeftCommand)
    singleOf(::MoveForwardCommand)

    // Parser
    singleOf(::CommandsConfigParser)
    singleOf(::EarthInstructionsConfigParser)
    singleOf(::GridConfigParser)
    singleOf(::EarthInstructionsConfigParser)
    singleOf(::PositionAndOrientationConfigParser)

    // Factory
    singleOf(::GridFactory)
    singleOf(::RobotFactory)
    singleOf(::OrientationFactory)
    singleOf(::CommandFactory)
}
