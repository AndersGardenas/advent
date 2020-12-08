package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input8_1.txt")
    val positions = BooleanArray(file.size)
    var acc = 0
    var posistion = 0
    while (true) {
        if (positions[posistion]) {
            break
        } else {
            positions[posistion] = true
        }

        val row = file[posistion]
        val tag = row.subSequence(0, 3)
        when {
            tag == "acc" -> {
                acc += row.drop(4).toInt()
                posistion++
            }
            tag == "jmp" -> {
                val jump = row.drop(4).toInt()
                posistion += jump
            }
            tag == "nop" -> {
                posistion++
            }
        }
    }

    print(acc)
}

