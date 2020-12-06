package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input1_1.txt")
    val fileSize = file.size - 1
    for (first in 0..fileSize) {
        for (second in (first + 1)..fileSize) {
            if (file[first].toInt() + file[second].toInt() == 2020) {
                print(file[first].toInt() * file[second].toInt())
                return
            }
        }
    }
}
