package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input9_1.txt")
    val list = file.map { it.toLong() }
    val num = 258585477L
    var n = 0
    var sum = 0L
    var min = Long.MAX_VALUE
    var max = 0L
    var current = 0
    while (true) {
        val value = list[current]
        sum += value
        min = min.coerceAtMost(value)
        max = max.coerceAtLeast(value)
        if (sum > num) {
            n++
            sum = 0
            min = Long.MAX_VALUE
            max = 0
            current = n
        } else if (sum == num) {
            break
        } else{
            current++
        }


    }


    print(min + max)

}


