package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input16_1.txt")
    val rulesSet = file.subList(0, file.indexOf("your ticket:") -1 ).map {
        it.split(": ")[1].split(" or ")  }


    val rules = rulesSet.map { it2 -> it2.map { it.split("-")[0].toInt()..it.split("-")[1].toInt() }}

    val tickets = file.drop(file.indexOf("nearby tickets:") + 1).map { it.split(",").map { it2 -> it2.toInt() } }
    val fitTickets = tickets.filter {ticketList -> ticketList.all{ ticket -> rules.any { rulePair -> rulePair.any { rule -> ticket in rule  } } }}

    val size = tickets[0].size
    val map = Array(size) { mutableListOf<Int>() }

    rules.forEachIndexed{index, rule ->
        for (i in 0 until size){
            if (fitTickets.all {ticket ->
                        ticket[i] in rule[0] || ticket[i] in rule[1]
            }){
                map[index].add(i)
            }
        }
    }
    val sortedMap = map.sortedBy { it.size }
    for (i in 0 until size - 1){

        sortedMap.drop(i + 1).forEach { it.remove(sortedMap[i][0]) }
    }


    val ticket = file[22].split(",").map { it.toInt() }

    println(ticket[map[0][0]].toLong()*ticket[map[1][0]].toLong()*ticket[map[2][0]].toLong()*ticket[map[3][0]].toLong()*ticket[map[4][0]].toLong()*ticket[map[5][0]].toLong())
}