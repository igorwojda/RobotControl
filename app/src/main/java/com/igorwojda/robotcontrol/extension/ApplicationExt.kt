package com.igorwojda.robotcontrol.extension

import android.app.Application

fun Application.readAssetAsString(fileName: String) = assets.open(fileName).bufferedReader().use { it.readText() }
