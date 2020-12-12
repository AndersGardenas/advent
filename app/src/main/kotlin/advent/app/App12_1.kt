package advent.app

import kotlin.math.abs


var xPos = 0
var yPos = 0
var direction = Pair(1, 0)
fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input12_1.txt")

    val type = mutableListOf<Char>();
    val num = mutableListOf<String>();
    file.forEach {
        type.add(it.subSequence(0, 1)[0])
        num.add(it.drop(1))
    }
    type.forEachIndexed { index, s -> move(s, num[index].toInt()) }
    print(abs(xPos) + abs(yPos))

}

fun move(type: Char, num: Int) {
    if (type == 'F') {
        xPos += direction.first * num;
        yPos += direction.second * num;
    }
    if (type == 'N') {
        yPos += num;
    }
    if (type == 'S') {
        yPos -= num;

    }
    if (type == 'W') {
        xPos -= num;

    }
    if (type == 'E') {
        xPos += num;
    }
    if (type == 'R') {
        for (i in 1..(num / 90))
            direction = Pair(direction.second, -direction.first)
    }
    if (type == 'L') {
        for (i in 1..(num / 90))
            direction = Pair(-direction.second, direction.first)
    }
}