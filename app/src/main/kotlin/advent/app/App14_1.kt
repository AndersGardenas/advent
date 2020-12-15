package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input14_1.txt")
    var mask = ""
    val map = hashMapOf<Int, Long>()

    for (row in file) {
        val type = row.split(" = ")[0]
        val value = row.split(" = ")[1]
        if (type.subSequence(0,3) == "mas"){
            mask = value
        }else{
            var valueArray = value.toInt().toString(2)
            valueArray = valueArray.padStart(36,'0')
            map[uglySplit(type)] = mask.mapIndexed {index,it -> if (it == 'X') valueArray[index] else it  }.joinToString("").toLong(2)
        }
    }
    println(map.map { (_,v) -> v }.sum())
}

private fun uglySplit(type: String) = type.split("[")[1].split("]")[0].toInt()
