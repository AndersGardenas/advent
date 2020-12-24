package advent.app

const val million = 20
val size  = 1000

@ExperimentalStdlibApi
fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input23_1.txt")
    var cupsArray = file[0].split("").filter { it != "" }.map { it.toInt() }.toMutableList()
    val max = cupsArray.max()
    val sizeOfElemtns = cupsArray.max()
    cupsArray = (cupsArray + ((max!! + 1)..(million - sizeOfElemtns!! + max)).toList()).toMutableList()

    //val cups2 = LinkedList<Int>()

    val cups = cupsArray
    var i = 0
    var start: Long
    var start2 = System.currentTimeMillis()
    var stop1 = System.currentTimeMillis()
    var stop2 = System.currentTimeMillis()
    var stop3 = System.currentTimeMillis()
    while (i < million*10){
        start = System.currentTimeMillis()


        val removedCups = cups.subList(1,4).toMutableList()
        val currentCup = cups[0]
        cups.removeFirst()
        cups.removeFirst()
        cups.removeFirst()
        cups.removeFirst()
        stop1 += System.currentTimeMillis() - start
        if (i % size == 0){
            stop1 = 1
        }
        start = System.currentTimeMillis()

        var destinationCup = currentCup - 1
        while (cups.indexOf(destinationCup)  == -1){
            destinationCup -= 1
            if(destinationCup < 0){
                destinationCup = cups.max()!!
            }
        }
        stop2 += System.currentTimeMillis() - start
        if (i % size == 0){
            start2 = 0
        }
        start = System.currentTimeMillis()

        val index = cups.indexOf(destinationCup)
        cups.add(index + 1,removedCups[0])
        cups.add(index + 2,removedCups[1])
        cups.add(index + 3,removedCups[2])
        cups.add(cups.size,currentCup)
        stop3 += System.currentTimeMillis() - start
        if (i % size == 0){
            stop3 = 0
            println(i)
            val oneIndex = cups.indexOf(1)
            println(cups[toIndex(oneIndex + 1)].toString() + " * " + cups[toIndex(oneIndex + 2)] + " = " + cups[toIndex(oneIndex + 1)].toLong() * cups[toIndex(oneIndex + 2)].toLong())
            println("--------------------------------------------------------------------")


        }
        //println(cups.subList(0,30).toString())
        i += 1
    }

    val oneIndex = cups.indexOf(1)
    println(cups.toString())
    println(cups[toIndex(oneIndex + 1)].toString() + " * " + cups[toIndex(oneIndex + 2)] + " = " + cups[toIndex(oneIndex + 1)].toLong() * cups[toIndex(oneIndex + 2)].toLong())

}

