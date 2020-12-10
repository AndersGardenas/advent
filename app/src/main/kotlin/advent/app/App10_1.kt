package advent.app


var one = 0;
var three = 1;

fun main() {

    val file = fileToStringList("app/src/main/kotlin/input/input10_1.txt")
    val list = file.map { it.toLong() }.sorted()

    list.forEachIndexed{i,_ -> next(i,list)}


    print(one * three)

}


fun next(i: Int,list: List<Long>){
    var diff = 0L;
    if (i == 0){
        diff = list[i]
    }else{
        diff = list[i] - list[i - 1]
    }
    if (diff == 1L){
        one++
    }else if (diff == 3L){
        three++
    }
}

