package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input7_1.txt")
    val bags = mutableMapOf<String,String>()

    file.forEach {
        val subStrings = it.split("contain")
        bags.put(subStrings[0].replace(" bags ", ""),
                subStrings[1].replace(".", "").replace(" bags", "").replace(" bag", ""))
    }

    val result = bagsThatFit(setOf("shiny gold"), bags, mutableListOf())
    print(result)
}

fun bagsThatFit(bagToFit: Set<String>, allBags: Map<String,String>, resultBags: MutableList<String>): Int {
    val newBagsToFit = allBags.filter{ (_,entry) -> bagToFit.any{entry.contains(it)} }
    resultBags.addAll(newBagsToFit.keys)
    if (newBagsToFit.isNotEmpty()) {
        return bagsThatFit(newBagsToFit.keys, allBags, resultBags)
    }
    return resultBags.distinct().size
}
