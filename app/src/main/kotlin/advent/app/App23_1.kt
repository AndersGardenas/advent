package advent.app


fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input23_1.txt")
    var cups = file[0].split("").filter { it != "" }.map { it.toInt() }.toMutableList()
    var i = 0
    while (i < 101){
        val currentCup = cups.get(0)
        val remmovedCups = cups.subList(1,4)
        cups = cups.drop(4).toMutableList()
        var destinationCup = currentCup - 1
        while (cups.indexOf(destinationCup)  == -1){
            destinationCup -= 1
            if(destinationCup < 0){
                destinationCup = cups.max()!!
            }
        }
        val index = cups.indexOf(destinationCup)
        cups.add(index + 1, remmovedCups[0])
        cups.add(index + 2, remmovedCups[1])
        cups.add(index + 3, remmovedCups[2])
        cups = (cups + mutableListOf(currentCup)).toMutableList()
        i += 1
    }

    println(cups.toString().replace(", ",""))
}

