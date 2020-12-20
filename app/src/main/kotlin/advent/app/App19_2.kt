package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input19_1.txt")

    val middle = file.indexOf("")
    val ruleFile = file.subList(0, middle).toMutableList()
    ruleFile[ruleFile.indexOfFirst { it[0] == '1' && it[1] == '1' && it[2] == ':' }] = "11: 42 31 | 42 11 31"
    val stringFile = file.subList(middle + 1, file.size)
    val ruleMap = HashMap<Int, List<List<String>>>()

    ruleFile.forEach { it3 ->
        ruleMap[it3.split(": ")[0].toInt()] =
                it3.split(": ")[1].replace("\"", "").split(" | ").map {
                    it.split(" ")
                }
    }
    val result = stringFile.filter {
        cIndex = 0
        matchingRuleLoop(it, ruleMap, 8, 0)
    }
    println(result.count())
    print(result)
}

fun matchingRuleLoop(fullString: String, rules: HashMap<Int, List<List<String>>>, ruleIndex: Int, charIndex: Int): Boolean {
    if (charIndex >= fullString.count()) {
        return false
    }
    var index = 0
    if (ruleIndex == 8) {
        while (true) {
            val rule1Size = 8
            val rule2Size = 8
            val result = matchingRuleLoop(fullString, rules, 42, index * rule1Size)
            index += 1
            if (result) {
                for (i in 0 until (index-1)) {
                    var inerResult: Boolean
                    val inerIndex = index * rule1Size + i * rule2Size
                    inerResult = matchingRuleLoop(fullString, rules, 31, inerIndex)
                    if (!inerResult) {
                        break
                    }
                    if (inerIndex == fullString.count() - rule2Size) {
                        return true
                    }
                }
            }
            if (!result || index * rule1Size >= fullString.count()) {
                return false
            }
        }
    }
    val currentRule = rules[ruleIndex]
    return currentRule!!.any {
        cIndex = charIndex
        if (it.size == 1) {
            if (it[0][0] in 'a'..'z') {
                return fullString[cIndex++] == it[0][0]
            }
        }
        it.all { it2 -> matchingRuleLoop(fullString, rules, it2.toInt(), cIndex) }
    }

}
