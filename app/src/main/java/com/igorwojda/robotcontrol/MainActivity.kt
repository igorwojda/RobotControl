package com.igorwojda.robotcontrol

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStr = readAssetString("directions.txt")
        val inputParser = InputParser(inputStr)

        val a = inputParser.boardSize
        val abb = inputParser.moveSequences

    }

    private fun readAssetString(fileName: String) = application.assets.open(fileName).bufferedReader().use { it.readText() }
}
