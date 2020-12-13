package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input13_1.txt")
    val time = file[0].toInt()
    val ids = file[1].split(",").filter { it != "x" }.map { it.toInt() }
    //Does not work when it % mod == 0
    val min = ids.minBy { (it - time % it) } ?: return
    print(min * (min - time % min))
}
