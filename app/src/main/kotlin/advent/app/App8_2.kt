package advent.app


fun main() {
    val start = System.currentTimeMillis()

    val file = fileToStringList("app/src/main/kotlin/input/input8_1.txt")

    val acc = file.foldIndexed(0){ index, sum, _ ->  sum + validAndCompute(file,index) }
    println(System.currentTimeMillis() - start)
    print(acc)
}

private fun validAndCompute(file: List<String>, index: Int): Int {
    val size = file.size

    var acc = 0
    var posistion = 0
    val positions = BooleanArray(file.size)
    while (size > posistion) {
        if (positions[posistion]) {
            return 0
        } else {
            positions[posistion] = true
        }

        val row = file[posistion]
        var tag = row.subSequence(0, 3)

        if (posistion == index){
            if (tag == "jmp"){
                tag = "nop"
            }else if (tag == "nop"){
                tag = "jmp"
            }
        }
        when (tag) {
            "acc" -> {
                acc += row.drop(4).toInt()
                posistion++
            }
            "jmp" -> {
                val jump = row.drop(4).toInt()
                posistion += jump
            }
            "nop" -> {
                posistion++
            }
        }
    }
    return acc
}

