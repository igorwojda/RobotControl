package com.igorwojda.robotcontrol

import com.igorwojda.robotcontrol.di.appModule
import org.koin.core.context.GlobalContext.startKoin

fun main() {
    startKoin {
        modules(appModule)
    }

    MainApp()
}
