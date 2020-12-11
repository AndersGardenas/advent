package advent.app

var widthApp11 = 0;
var heightApp11 = 0;
fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input11_1.txt")
    heightApp11 = file.size
    widthApp11 = file[0].length

    var oldMap = file.map { it.map {it2 ->  it2 } }
    while (true) {
        val seatedMap = oldMap.mapIndexed { y, it -> it.mapIndexed { x, it2 -> seat(y, x, it2, oldMap) } }
        val unseatedMap = seatedMap.mapIndexed { y, it -> it.mapIndexed { x, it2 -> unSet(y, x, it2, seatedMap) } }
        if (oldMap.toString() == unseatedMap.toString()){
            break
        }
        oldMap = unseatedMap
    }
    print(oldMap.fold(0,{total, subList -> total + subList.count { it == '#' } }))
}

fun seat(y: Int, x: Int, token: Char, file: List<List<Char>>): Char {
    if (token == '#' || token == '.') {
        return token
    }
    val n = neighbours(y, x, file)
    if(n == 0){
        return '#'
    }
    return token
}

fun unSet(y: Int, x: Int, token: Char, file: List<List<Char>>): Char {
    if (token == 'L' || token == '.') {
        return token
    }
    val n = neighbours(y, x, file)
    if(n >= 4){
        return 'L'
    }
    return token
}

fun neighbours(y: Int, x: Int, file: List<List<Char>>): Int {
    val pairs = listOf(Pair(y - 1, x -1), Pair(y - 1, x + 0), Pair(y - 1, x + 1),
            Pair(y + 0, x - 1), Pair(y + 0, x + 1),
            Pair(y + 1, x - 1), Pair(y + 1, x + 0), Pair(y + 1, x + 1))

    return pairs.filter { it.first in 0 until heightApp11 && it.second in 0 until widthApp11 }.count { file[it.first][it.second] == '#' }
}



