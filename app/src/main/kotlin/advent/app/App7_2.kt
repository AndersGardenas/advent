package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input7_1.txt")
    val bags = mutableMapOf<String, String>()

    file.forEach {
        val subStrings = it.split("contain")
        bags[subStrings[0].replace(" bags ", "")] = subStrings[1].replace(".", "")
                .replace(" no other bags", "")
                .replace(" bags", "")
                .replace(" bag", "")
    }

    val result = numBagsThatFit("shiny gold", bags)
    print(result - 1)
}

fun numBagsThatFit(bagToFit: String, allBags: Map<String, String>): Int {
    val newBagsToFit = allBags[bagToFit]

    if (newBagsToFit != null && newBagsToFit != "") {
        return newBagsToFit.split(",").sumBy { it[1].toString().toInt() * numBagsThatFit(it.drop(3), allBags)  } + 1
    }
    return 1
}
