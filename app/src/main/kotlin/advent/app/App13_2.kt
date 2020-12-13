package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input13_1.txt")
    var ids = file[1].split(",").mapIndexed { index, it -> Pair(if (it == "x") -1L else it.toLong(), index.toLong()) }.filter { it.first != -1L }

    var iter = ids[0].first
    var newVal = ids[0].first
    ids = ids.drop(1)
    ids = ids.sortedBy { -it.first }
    for (id in ids) {
        newVal += iter
        val modDiff = (10* id.first - id.second) % id.first
        while (((newVal)  % id.first) != modDiff){
            newVal += iter
        }
        iter *= id.first
    }
    println(newVal)
}
//Iter
//23
//11569
//4430927
//181668007