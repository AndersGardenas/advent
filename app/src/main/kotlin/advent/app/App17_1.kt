package advent.app

import java.lang.Integer.max
import java.lang.Integer.min


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input17_1.txt")
    val iterations = 6
    val middlePoint = file.size
    val offset = (iterations - 1) + middlePoint/2
    val dimensionSize = middlePoint + offset * 2
    val zSize = 1 + offset * 2
    val middle = file.map { it.toCharArray() }
    var cubes = Array(zSize) { Array(dimensionSize) { CharArray(dimensionSize) } }
    cubes.forEachIndexed { z, arrayOfCharArrays -> arrayOfCharArrays.forEachIndexed { y, chars -> chars.forEachIndexed { x, _ -> cubes[z][y][x] = '.' } } }
    middle.forEachIndexed { y, arrayOfCharArrays -> arrayOfCharArrays.forEachIndexed { x, c -> cubes[offset][y + offset][x + offset] = c } }
    for (i in 0 until iterations) {
        cubes = cubes.mapIndexed { index, arrayOfCharArrays ->
            arrayOfCharArrays.mapIndexed { index2, chars ->
                chars.mapIndexed { index3, char -> newState(cubes, char, index, index2, index3, dimensionSize, zSize) }.toCharArray()
            }.toTypedArray()
        }.toTypedArray()
    }
    println(cubes.sumBy { it2 -> it2.sumBy { it1 -> it1.count { it == '#' } } })
}

fun newState(cubes: Array<Array<CharArray>>, initial: Char, z: Int, y: Int, x: Int, dimSize: Int, zSize: Int): Char {
    val zRange = max(0, z - 1)..min(zSize - 1, z + 1)
    val yRange = max(0, y - 1)..min(dimSize - 1, y + 1)
    val xRange = max(0, x - 1)..min(dimSize - 1, x + 1)
    var neg = 0
    for (zVal in zRange) {
        for (yVal in yRange) {
            for (xVal in xRange) {
                if (cubes[zVal][yVal][xVal] == '#') {
                    neg++
                }
            }
        }
    }
    if (initial == '#' && neg in 3..4) {
        return '#'
    } else if (initial == '.' && neg == 3) {
        return '#'
    }
    return '.'
}