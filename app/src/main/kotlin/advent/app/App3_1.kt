package advent.app

var width = 0
var pos = 0
fun main() {
    var file = fileToStringList("app/src/main/kotlin/input/input3_1.txt")
    val line = file.first()
    width = line.length
    file = file.drop(1)
    val matches = file.count{ isTree(it) }
    println(matches)

}

fun isTree(line: String): Boolean {
    pos = (pos + 3) % width
    return line[pos] == '#'
}