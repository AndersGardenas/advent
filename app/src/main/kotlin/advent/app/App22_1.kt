package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input22_1.txt")
    var p1 = file.subList(1, file.indexOf("Player 2:") - 1).toMutableList()
    var p2 = file.subList(file.indexOf("Player 2:") + 1, file.lastIndex + 1).toMutableList()

    while (p1.isNotEmpty() && p2.isNotEmpty()) {
        val c1 = p1[0]
        val c2 = p2[0]
        p1 = p1.drop(1).toMutableList()
        p2 = p2.drop(1).toMutableList()
        if (c1.toInt() > c2.toInt()) {
            p1.add(c1)
            p1.add(c2)
        } else {
            p2.add(c2)
            p2.add(c1)
        }
    }

    val result = if (p1.isEmpty()){
        p2
    }else{
        p1
    }
    result.reverse()
    val indexedResult = result.mapIndexed { index, s -> (index + 1) * s.toInt() }
    print( indexedResult.sum())
}