package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input6_1.txt")
    val text = file.fold("", { total, it -> total + "\n" + it })
    val groups = text.split("\n\n")
    val range = "a".."z"
    val result = groups.sumBy{it.toCharArray().distinct().count { it2 -> it2.toString() in range }}

    print(result)
}
