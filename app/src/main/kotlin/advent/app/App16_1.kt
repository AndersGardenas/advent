package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input16_1.txt")
    val rulesSet = file.subList(0, file.indexOf("your ticket:") -1 ).map {
        it.split(": ")[1].split(" or ")  }


    val rules = rulesSet.map { it2 -> it2.map { it.split("-")[0].toInt()..it.split("-")[1].toInt() }}

    val tickets = file.drop(file.indexOf("nearby tickets:") + 1).toString().replace("[","").replace(" ","").replace("]","").split(",").map { it.toInt() }

    val noFit = tickets.filter { !rules.any { it2 -> it2.any { it3 -> it in it3 } } }
    println(noFit.sum())
}

