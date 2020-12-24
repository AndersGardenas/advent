package advent.app

import java.util.*
import kotlin.math.min
import kotlin.math.max



@ExperimentalStdlibApi
fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input23_1.txt")
    var cupsArray = file[0].split("").filter { it != "" }.map { it.toInt() }.toMutableList()
    val max = cupsArray.max()
    val size = cupsArray.max()
    cupsArray = (cupsArray + ((max!! + 1)..(million - size!! + max)).toList()).toMutableList()

    val cups = cupsArray
    var start = System.currentTimeMillis()

    for (i in 0 until  100) {
        val remmovedCups = mutableListOf(cups[toIndex(i*4)],cups[toIndex(i*4 + 1)],cups[toIndex(i*4 + 2)],cups[toIndex(i*4 + 3)])
        val currentCup = remmovedCups[0]

        var destinationCup = currentCup - 1
        while (cups.indexOf(destinationCup) == -1 || remmovedCups.indexOf(destinationCup) != -1) {
            destinationCup -= 1
            if (destinationCup < 0) {
                destinationCup = cups.max()!!
            }
        }
        val index = cups.indexOf(destinationCup)
        insert(remmovedCups,cups,index + 1)
        if (i % 1000 == 0){
            println(System.currentTimeMillis() - start)
            start = System.currentTimeMillis()
            println("--------------------------------------------------------------------")
            val oneIndex = cups.indexOf(1)
            println(cups[toIndex(oneIndex + 1)].toString() + " * " + cups[toIndex(oneIndex + 2)] + " = " + cups[toIndex(oneIndex + 1)] * cups[toIndex(oneIndex + 2)])

        }
        //println(cups.subList(0,30).toString())
    }

    val oneIndex = cups.indexOf(1)
    println(cups[oneIndex + 1].toString() + " * " + cups[oneIndex + 2] + " = " + cups[oneIndex + 1] * cups[oneIndex + 2])
    println(cups.toString().replace(", ",""))

}


fun insert(toInsert: List<Int>, all: MutableList<Int>, index: Int) {
    var offset = 0
    var current = all[toIndex(index)]
    val firstToNotShift = toInsert[1]
    if(firstToNotShift == all[toIndex(index + 1)]){
        offset = 3
    }
    var next = all[toIndex(index + 1 + offset)]
    if(firstToNotShift == all[toIndex(index + 2)]){
        offset = 3
    }
    var inOne = all[toIndex(index + 2 + offset)]

    for (i in 0 until (size - 3)) {
        if(firstToNotShift == all[toIndex(index + i + 3)]){
            offset = 3
        }
        val inTwo = all[toIndex(index + i + 3 + offset)]
        all[toIndex(index + i + 3)] = current
        current = next
        next = inOne
        inOne = inTwo
    }
    all[toIndex(index)] = firstToNotShift
    all[toIndex(index + 1)] = toInsert[2]
    all[toIndex(index + 2)] = toInsert[3]

}


fun toIndex(index: Int): Int {
    return index % size
}
