package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input2_1.txt")
    val matches = file.count{ validPassword(it) }
    println(matches)

}

fun validPassword(line: String): Boolean {
    val splitLine = line.split(" ")
    val min = splitLine[0].split("-")[0].toInt()
    val max = splitLine[0].split("-")[1].toInt()
    val token = splitLine[1].first()
    val password = splitLine[2]
    val matches = password.filter{ it == token }.count()
    return matches in min..max
}