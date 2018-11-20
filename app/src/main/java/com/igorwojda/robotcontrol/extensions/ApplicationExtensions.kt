package com.igorwojda.robotcontrol.extensions

import android.app.Application

fun Application.readAssetAsString(fileName: String) = assets.open(fileName).bufferedReader().use { it.readText() }
