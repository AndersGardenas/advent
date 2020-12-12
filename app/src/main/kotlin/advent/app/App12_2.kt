package advent.app

import kotlin.math.abs


var waypoint = Pair(10, 1)

fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input12_1.txt")

    val type = mutableListOf<Char>();
    val num = mutableListOf<String>();
    file.forEach {
        type.add(it.subSequence(0, 1)[0])
        num.add(it.drop(1))
    }
    type.forEachIndexed { index, s -> moveAroundWaypoint(s, num[index].toInt()) }
    print(abs(xPos) + abs(yPos))

}

fun moveAroundWaypoint(type: Char, num: Int) {
    if (type == 'F') {
        xPos += waypoint.first * num;
        yPos += waypoint.second * num;
    }
    if (type == 'N') {
        waypoint = Pair(waypoint.first, waypoint.second + num);
    }
    if (type == 'S') {
        waypoint = Pair(waypoint.first, waypoint.second - num);

    }
    if (type == 'W') {
        waypoint = Pair(waypoint.first - num, waypoint.second);

    }
    if (type == 'E') {
        waypoint = Pair(waypoint.first + num, waypoint.second);
    }
    if (type == 'R') {
        for (i in 1..(num / 90))
            waypoint = Pair(waypoint.second, -waypoint.first)
    }
    if (type == 'L') {
        for (i in 1..(num / 90))
            waypoint = Pair(-waypoint.second, waypoint.first)
    }
}