package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input2_1.txt")
    val matches = file.count{ validPassword2(it) }
    println(matches)

}

fun validPassword2(line: String): Boolean {
    val splitLine = line.split(" ")
    val min = splitLine[0].split("-")[0].toInt() -1
    val max = splitLine[0].split("-")[1].toInt() -1
    val token = splitLine[1].first()
    val password = splitLine[2]
    val toCharArray = password.toCharArray()
    return (toCharArray[min] == token).xor(toCharArray[max] == token)
}