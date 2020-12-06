package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input6_1.txt")
    val text = file.fold("", { total, it -> total + "\n" + it })
    val groups = text.split("\n\n")
    val range = 'a'..'z'
    val result = groups.sumBy{ range.count{ it2 -> it.split("\n").filter { it4 -> it4 != "" } .all{ it3 -> it3.contains(it2) }}}

    print(result)
}
