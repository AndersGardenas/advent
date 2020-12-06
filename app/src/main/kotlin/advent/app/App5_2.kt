package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input5_1.txt")
    val range = 2..1022
    val result = range.filter{it2 ->  !file.map { rowStringToNum(it) }.contains(it2)}

    print(result)
}
