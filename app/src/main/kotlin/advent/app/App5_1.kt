package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input5_1.txt")
    val result = file.map { rowStringToNum(it) }.max()
    print(result)
}

fun rowStringToNum(row: String): Int{
    val binaryRow = row.replace("F","0").replace("B","1")
            .replace("R","1").replace("L","0")
    val rowNum = Integer.parseInt(binaryRow.subSequence(0,7).toString(),2).toInt()
    val columnNum = Integer.parseInt(binaryRow.subSequence(7,10).toString(),2).toInt()
    return rowNum * 8 + columnNum
}