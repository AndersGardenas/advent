package advent.app

fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input11_1.txt")
    heightApp11 = file.size
    widthApp11 = file[0].length

    var oldMap = file.map { it.map { it2 -> it2 } }
    while (true) {
        val seatedMap = oldMap.mapIndexed { y, it -> it.mapIndexed { x, it2 -> seatDiagonal(y, x, it2, oldMap) } }
        val unseatedMap = seatedMap.mapIndexed { y, it -> it.mapIndexed { x, it2 -> unSetDiagonal(y, x, it2, seatedMap) } }
        if (oldMap.toString() == unseatedMap.toString()) {
            break
        }
        oldMap = unseatedMap
    }
    print(oldMap.fold(0, { total, subList -> total + subList.count { it == '#' } }))
}

fun seatDiagonal(y: Int, x: Int, token: Char, file: List<List<Char>>): Char {
    if (token == '#' || token == '.') {
        return token
    }
    val n = neighboursDiagonal(y, x, file)
    if (n == 0) {
        return '#'
    }
    return token
}

fun unSetDiagonal(y: Int, x: Int, token: Char, file: List<List<Char>>): Char {
    if (token == 'L' || token == '.') {
        return token
    }
    val n = neighboursDiagonal(y, x, file)
    if (n >= 5) {
        return 'L'
    }
    return token
}

fun neighboursDiagonal(y: Int, x: Int, file: List<List<Char>>): Int {
    val pairs = listOf(Pair(-1, -1), Pair(-1, 0), Pair(-1, +1),
            Pair(0, -1), Pair(0, 1),
            Pair(1, -1), Pair(1, 0), Pair(1, 1))

    return pairs.count { findForMoveMovement(y, x, it, file) }
}

fun findForMoveMovement(y: Int, x: Int, direction: Pair<Int, Int>, map: List<List<Char>>): Boolean {
    var yVal = y + direction.first
    var xVal = x + direction.second
    while (yVal in 0 until heightApp11 && xVal in 0 until widthApp11) {
        if (map[yVal][xVal] != '.') {
            return map[yVal][xVal] == '#'
        }
        yVal += direction.first
        xVal += direction.second
    }
    return false
}



