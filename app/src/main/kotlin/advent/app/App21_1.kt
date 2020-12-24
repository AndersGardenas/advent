package advent.app


fun main() {

    var file = fileToStringList("app/src/main/kotlin/input/input21_1.txt")
    var map = HashMap<String,MutableList<String>>()
    val all =  MutableList(0){""}

    for (row in file){
        val ing = row.split(" (")[0].split(" ")
        all += ing
        val ags = row.split("(")[1].replace("contains ","").replace(")","").replace(" ","").split(",")
        for (ag in ags){
            if (!map.containsKey(ag)){
                map[ag] = ing.toMutableList()
            }else{
                map[ag] = map[ag]!!.filter { it in ing }.toMutableList()
            }
        }
    }
    val clean =  MutableList<String>(0){""}
    var updated = true
    while (updated){
        updated = false
        for (m in map){
            if (m.value.size == 1){
                updated = true
                val valueToRemove = m.value[0]
                clean.add(valueToRemove)
                map.forEach { it.value -= valueToRemove }
            }
        }
    }
    val result = all.filter { !(it in clean) }
    println(result.size)
    val stop = 0



}
