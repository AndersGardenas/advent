package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input22_1.txt")
    var p1 = file.subList(1, file.indexOf("Player 2:") - 1).toMutableList()
    var p2 = file.subList(file.indexOf("Player 2:") + 1, file.lastIndex + 1).toMutableList()

    val pair = recursiveCombat(p1, p2, mutableListOf())
    p1 = pair.first
    p2 = pair.second

    val result = if (p1.isEmpty()) {
        p2
    } else {
        p1
    }
    result.reverse()
    val indexedResult = result.mapIndexed { index, s -> (index + 1) * s.toInt() }
    print(indexedResult.sum())
}

private fun recursiveCombat(p1: MutableList<String>, p2: MutableList<String>,
                            previousGames: MutableList<Pair<String, String>>): Pair<MutableList<String>, MutableList<String>> {

    var p1New = p1
    var p2New = p2
    while (p1New.isNotEmpty() && p2New.isNotEmpty()) {
        val p1OriginalValue = toNum(p1New)
        val p2OriginalValue = toNum(p2New)
        val c1 = p1New[0]
        val c2 = p2New[0]
        p1New = p1New.drop(1).toMutableList()
        p2New = p2New.drop(1).toMutableList()


        val p1Wins: Boolean = if (hasHappenedBefore(previousGames, p1OriginalValue, p2OriginalValue)) {
            return Pair(p1New,p2New)
        } else if (c1.toInt() > p1New.count() || c2.toInt() > p2New.count()) {
            c1.toInt() > c2.toInt()
        } else {
            val p1Sub = p1New.subList(0, c1.toInt())
            val p2Sub = p2New.subList(0, c2.toInt())
            val p1SubValue = toNum(p1Sub)
            val p2SubValue = toNum(p2Sub)
            if(knownResult(p1SubValue,p2SubValue)){
                p1Won(p1SubValue,p2SubValue)
            }else {
                val resultSubGame = recursiveCombat(p1Sub, p2Sub, mutableListOf())
                val notEmpty = resultSubGame.first.isNotEmpty()
                update(p1SubValue,p2SubValue,notEmpty)
                notEmpty
            }
        }
        previousGames.add(Pair(p1OriginalValue,p2OriginalValue))
        if (p1Wins) {
            p1New.add(c1)
            p1New.add(c2)
        } else {
            p2New.add(c2)
            p2New.add(c1)
        }
    }
    return Pair(p1New, p2New)
}

fun hasHappenedBefore(previousGames: List<Pair<String, String>>, p1Value : String, p2Value: String): Boolean {
    return previousGames.any {
        it.first == p1Value || it.second == p2Value
    }
}

fun toNum(p: List<String>): String{
    return p.toString()
}

val mapOfPaths = HashMap<String,HashMap<String,Boolean>>()
fun knownResult(p1Value: String,p2Value: String):Boolean{
    return if (p1Value > p2Value){
        mapOfPaths[p1Value]?.contains(p2Value) ?: false
    }else{
        mapOfPaths[p2Value]?.contains(p1Value) ?: false
    }

}

fun p1Won(p1Value: String, p2Value: String):Boolean{
    return if (p1Value > p2Value){
        mapOfPaths[p1Value]!![p2Value] ?: false
    }else{
        mapOfPaths[p2Value]!![p1Value] ?: false
    }
}

fun update(p1Value: String, p2Value: String, result:Boolean){
    if (p1Value > p2Value) {
        if (!mapOfPaths.containsKey(p1Value)) {
            mapOfPaths[p1Value] = HashMap()
        }
        val map = mapOfPaths[p1Value]
        map!![p2Value] = result
    }else{
        if (!mapOfPaths.containsKey(p2Value)) {
            mapOfPaths[p2Value] = HashMap()
        }
        val map = mapOfPaths[p2Value]
        map!![p1Value] = result
    }
}