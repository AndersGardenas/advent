package advent.app


var size = 0;
fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input10_1.txt")
    val list = file.map { it.toLong() }.toMutableList()
    list.add(0L)
    val sortedList = list.sorted();

    size = sortedList.size
    val hits = Array(size) { 0L }
    hits[size -1] = 1
    subTress(size - 2, sortedList, hits)


    print(hits[0])

}


fun subTress(i: Int, list: List<Long>, hits: Array<Long>) {

    var next = i + 1;
    var value = 0L;
    while (next < size && list[i] + 3 >= list[next]) {
        value += hits[next]
        next++
    }
    hits[i] = value;
    if (i == 0) {
        return
    }
    subTress(i - 1, list, hits)
}
