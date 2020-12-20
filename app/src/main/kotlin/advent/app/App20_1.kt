package advent.app


fun main() {

    var file = fileToStringList("app/src/main/kotlin/input/input20_1.txt")
    file = file.filter { it != "" }
    val chunkedFile = file.chunked(file.drop(1).indexOfFirst { "Tile" in it } + 1)
    val size = Math.sqrt(chunkedFile.size.toDouble())

    val idMap = HashMap<Int, Long>()
    chunkedFile.forEachIndexed { index, s -> idMap[index] = s[0].replace("Tile ", "").replace(":", "").toLong() }
    val image = chunkedFile.map { it.drop(1).map { it2 -> it2.toCharArray() } }
    val imageSize = image[0].size

    val imageVariants = image.map { possibleCombinations(it.toTypedArray(), imageSize) }
    val matches = imageVariants.map { countMatching(it, imageVariants, imageSize) - 1 }

    var sum = 1L
    for (i in 0 until idMap.size){
        if (matches[i] == 2){
            sum *= idMap[i]!!
        }
    }

    print(sum)
}

fun possibleCombinations(image: Array<CharArray>, size: Int): List<Array<CharArray>> {
    val result = mutableListOf<Array<CharArray>>()
    var currentImage = image
    result.add(currentImage)
    for (i in 0..2) {
        currentImage = rotate(currentImage, size)
        result.add(currentImage)
    }
    currentImage = flip(currentImage, size)
    result.add(currentImage)
    for (i in 0..2) {
        currentImage = rotate(currentImage, size)
        result.add(currentImage)
    }
    return result
}

fun rotate(image: Array<CharArray>, size: Int): Array<CharArray> {
    val result = Array(size) { CharArray(size) }
    val adjustedSize = size - 1
    image.forEachIndexed { y, chars -> chars.forEachIndexed { x, c -> result[adjustedSize - x][y] = c } }
    return result
}

fun flip(image: Array<CharArray>, size: Int): Array<CharArray> {
    val result = Array(size) { CharArray(size) }
    val adjustedSize = size - 1
    image.forEachIndexed { y, chars -> chars.forEachIndexed { x, c -> result[y][adjustedSize - x] = c } }
    return result
}


fun countMatching(imageToVerify: List<Array<CharArray>>, images: List<List<Array<CharArray>>>, imageSize: Int): Int {
    return images.count { image ->
        var result = false
        for (rotation in 0 until 8) {
            for (selfRotation in 0 until 8) {
                result = true
                for (row in 0 until imageSize) {
                    if (imageToVerify[selfRotation][0][row] != image[rotation][imageSize - 1][row]) {
                        result = false
                        break
                    }
                }
                if (result) {
                    break
                }
            }
            if (result) {
                break
            }
        }
        result
    }
}


