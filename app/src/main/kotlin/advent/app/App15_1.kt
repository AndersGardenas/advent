package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input15_1.txt")
    val map = hashMapOf<Int, Int>()
    var n1 = 1
    file[0].split(",").forEach{map[it.toInt()] = n1++}
    var current = 0
    val num = 30000000 - 1
    for (i in n1..num){
        if(!map.containsKey(current)){
           map[current] = i
           current = 0
        }else{
            val lastTime = map[current]!!.toInt()
            map[current] = i
            current = i - lastTime
        }
    }
    print(current)
}
// 0 - 1
// 3 - 2
// 6 - 3
// 0 -     ->
