package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input3_1.txt")
    val line = file.first()
    width = line.length

    val slopes = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
    val matches = slopes.fold(1, { total: Long, it2 ->
        pos = 0
        total *
                file.filterIndexed { index, _ -> (index != 0) && (index % it2.second == 0) }
                        .count { isTreeIndexed(it, it2.first) }
    })
    println(matches)

}
fun isTreeIndexed(line: String, xJump: Int): Boolean {
    pos = (pos + xJump) % width
    return line[pos] == '#'
}