package com.igorwojda.robotcontrol.parser

import com.igorwojda.robotcontrol.data.config.GridConfig

class GridConfigParser {
    fun parse(input: String): GridConfig {
        require(input.isNotBlank()) { "Input is blank" }

        val splitGridSize = input.split(" ").toMutableList()
        splitGridSize.removeIf { it.isEmpty() }

        require(splitGridSize.size == NUM_ITEMS) { "Invalid grid size format: $input" }

        val width = splitGridSize.first().toInt()
        val height = splitGridSize.last().toInt()

        require(width in 0..MAX_GRID_SIZE && height in 0..MAX_GRID_SIZE) {
            "Grid is to large. Max 50x50. Grid size is width $width, height $height"
        }
        return GridConfig(width, height)
    }

    companion object {
        private const val NUM_ITEMS = 2
        private const val MAX_GRID_SIZE = 50
    }
}
