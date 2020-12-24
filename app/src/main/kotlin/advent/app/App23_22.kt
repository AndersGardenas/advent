package advent.app

import java.util.*

@ExperimentalStdlibApi
fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input23_1.txt")
    var cupsArray = file[0].split("").filter { it != "" }.map { it.toInt() }.toMutableList()
    val max = cupsArray.max()
    val size = cupsArray.max()
    cupsArray = (cupsArray + ((max!! + 1)..(million - size!! + max)).toList()).toMutableList()
    val cups = LinkedList23()
    cups.addAtHead(0)
    cupsArray.forEach { cups.addAtTail(it) }
    var start = System.currentTimeMillis()

    for (i in 0 until 10000) {
        val remmovedCups = mutableListOf(cups.get(toIndex(i * 4 + 1)), cups.get(toIndex(i * 4 + 2)), cups.get(toIndex(i * 4 + 3)))
        val currentCup = cups.get(toIndex(i * 4))
        var destinationCup = currentCup - 1
        while (cups.indexOf(destinationCup) == -1 || remmovedCups.indexOf(destinationCup) != -1) {
            destinationCup -= 1
            if (destinationCup < 0) {
                destinationCup = million
            }
        }
        val index = cups.indexOf(destinationCup)
        insertLinked(remmovedCups, cups, index + 1)
        if (i % 1000 == 0) {
            println(i)
            println(System.currentTimeMillis() - start)
            start = System.currentTimeMillis()
            println("--------------------------------------------------------------------")
        }
    }
    val index = cups.indexOf(1)
    println(cups.get(index + 1).toString() + " * " + cups.get(index + 2) + " = " + cups.get(index + 1) * cups.get(index + 2)   )
}


fun insertLinked(toInsert: List<Int>, all: LinkedList23, index: Int) {
    //all.addAll(index, toInsert )
}