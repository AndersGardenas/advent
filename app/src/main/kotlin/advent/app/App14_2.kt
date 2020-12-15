package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input14_1.txt")
    var mask = ""
    val map = hashMapOf<Long, Long>()

    for (row in file) {
        val type = row.split(" = ")[0]
        val value = row.split(" = ")[1]
        if (type.subSequence(0, 3) == "mas") {
            mask = value
        } else {
            var valueArray = value.toInt().toString(2)
            valueArray = valueArray.padStart(36, '0')
            val address = uglySplit(type).toString(2).padStart(36, '0')
            val maskedAddress = mask.mapIndexed {index,it -> if (it == '0') address[index] else it  }.joinToString("")
            val indexes = rebuild("", maskedAddress)
            indexes.forEach{ map[it.toLong(2)] = valueArray.toLong(2)}
        }
    }
    println(map.map { (_,v) -> v }.sum())
}


private fun uglySplit(type: String) = type.split("[")[1].split("]")[0].toInt()

fun rebuild(preFix: String, string: String): List<String> {
    if (string.isEmpty()) {
        return listOf(preFix)
    }
    val c = string[0]
    val newString = string.drop(1)
    if (c == 'X') {
        return rebuild(preFix + '0', newString).toMutableList() + rebuild(preFix + '1', newString).toMutableList()
    }
    return rebuild(preFix + c, newString)
}
