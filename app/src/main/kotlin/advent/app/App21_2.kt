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
    val clean = HashMap<String,String>()
    var updated = true
    while (updated){
        updated = false
        for (m in map){
            if (m.value.size == 1){
                updated = true
                val valueToRemove = m.value[0]
                clean[m.key] = valueToRemove
                map.forEach { it.value -= valueToRemove }
            }
        }
    }
    println(clean.toSortedMap().values.toString().replace(" ","").replace("[","").replace("]",""))
    val stop = 0



}
