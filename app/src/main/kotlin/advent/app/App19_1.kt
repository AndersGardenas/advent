package advent.app

import java.lang.Integer.max


fun main() {

    var file = fileToStringList("app/src/main/kotlin/input/input19_1.txt")

    val middle = file.indexOf("")
    val ruleFile = file.subList(0, middle).toMutableList()
    val stringFile = file.subList(middle + 1, file.size)
    val ruleMap = HashMap<Int,List<List<String>>>()

    ruleFile.forEach { it3 ->  ruleMap[it3.split(": ")[0].toInt()] =
            it3.split(": ")[1].replace("\"","").split(" | ").map { it.split(" ")
                    }}
    val result = stringFile.filter{
        cIndex = 0
        matchingRule(it,ruleMap,0,0)}

    println(result.filter { it.length == cIndexMax + 1 }.count())
    print(result.filter { it.length == cIndexMax + 1 })
}
var cIndex = 0
var cIndexMax = 0
fun matchingRule(fullString: String, rules: HashMap<Int,List<List<String>>>, ruleIndex: Int, charIndex: Int): Boolean {
    cIndexMax = max(cIndex, cIndexMax)
    if (charIndex >= fullString.count()){
        return false
    }
    if(ruleIndex == 11){
        val stop = 0
    }
    val currentRule = rules[ruleIndex]
    return currentRule!!.any {
        cIndex = charIndex
        if (it.size == 1){
            if (it[0][0] in 'a'..'z'){
                return fullString[cIndex++] == it[0][0]
            }
        }
        it.all {it2 ->  matchingRule(fullString,rules,it2.toInt(),cIndex) }
    }

}
