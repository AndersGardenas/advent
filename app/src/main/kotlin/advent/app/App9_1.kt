package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input9_1.txt")
    val list = file.map { it.toLong() }
    val size = 25
    val values = list.subList(0, size).toTypedArray()
    var n = size - 1
    val first = list.drop(size).first {
        values[(n % size)] = list[n]
        n++
        !values.any{ it2 -> values.any { it3 -> it2 != it3 && it2 + it3 == it }}
    }

    print(first)

}


