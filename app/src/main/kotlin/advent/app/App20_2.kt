package advent.app


fun main() {

    var file = fileToStringList("app/src/main/kotlin/input/input20_1.txt")
    file = file.filter { it != "" }
    val chunkedFile = file.chunked(file.drop(1).indexOfFirst { "Tile" in it } + 1)
    val imagesSize = Math.sqrt(chunkedFile.size.toDouble()).toInt()

    val idMap = HashMap<Int, Long>()
    chunkedFile.forEachIndexed { index, s -> idMap[index] = s[0].replace("Tile ", "").replace(":", "").toLong() }
    val image = chunkedFile.map { it.drop(1).map { it2 -> it2.toCharArray() } }
    val imageSize = image[0].size

    val imageVariants = image.map { possibleCombinations(it.toTypedArray(), imageSize) }
    val matches = imageVariants.map { countMatching(it, imageVariants, imageSize) - 1 }

    val corners = mutableListOf<List<Array<CharArray>>>()
    val sides = mutableListOf<List<Array<CharArray>>>()
    val middle = mutableListOf<List<Array<CharArray>>>()
    matches.forEachIndexed { index, i ->
        when (i) {
            4 -> {
                middle.add(imageVariants[index])
            }
            3 -> {
                sides.add(imageVariants[index])
            }
            else -> {
                corners.add(imageVariants[index])
            }
        }
    }


    val resultMap = buildMap(corners, sides, middle, imageSize, 0, 0, imagesSize, Array(imagesSize) { Array(imagesSize) { Array(imageSize) { CharArray(imageSize) } } })
    val verified = verifyImages(resultMap, imagesSize, imageSize)
    assert(verified)

    val result = buildString(resultMap, imagesSize, imageSize)
    val count = findDragon(result)
    print(result.sumBy { it.count { it2 -> it2 == '#' } } - count.sumBy { it.count { it2 -> it2 } })

}


fun findDragon(resultString: Array<String>): Array<Array<Boolean>> {
    val length = resultString[0].length

    val combinedMap = Array(length) { Array(length) { false } }
    val allMap = possibleCombinations(resultString.map { it.toCharArray() }.toTypedArray(), length).map { it.map { it3 -> it3.joinToString("") } }
    val part1 = "                  # "
    val part2 = "#    ##    ##    ###"
    val part3 = " #  #  #  #  #  #   "
    var counter = 0
    val rot = 4
    for (i in allMap.indices) {
        for (y in 0 until (length - 2)) {
            for (x in 0 until length - part1.length) {
                val subSequence1 = allMap[rot][y].subSequence(x, x + part1.length)
                if (matchingStrings(part1, subSequence1.toString())) {
                    val subSequence2 = allMap[rot][y + 1].subSequence(x, x + part1.length)
                    val subSequence3 = allMap[rot][y + 2].subSequence(x, x + part1.length)
                    if (matchingStrings(part2, subSequence2.toString()) && matchingStrings(part3, subSequence3.toString())) {

                        part1.forEachIndexed { index, c ->
                            if (c == '#') {
                                combinedMap[y][x + index] = true
                            }
                        }
                        part2.forEachIndexed { index, c ->
                            if (c == '#') {
                                combinedMap[y + 1][x + index] = true
                            }
                        }
                        part3.forEachIndexed { index, c ->
                            if (c == '#') {
                                combinedMap[y + 2][x + index] = true
                            }
                        }
                        counter += 15
                    }
                }
            }
        }
    }

    return combinedMap
}

fun findDRAGONS(resultString: Array<String>): Array<Array<Boolean>> {
    val length = resultString[0].length

    val map = Array(8) { Array(length) { Array(length) { false } } }
    val allMap = possibleCombinations(resultString.map { it.toCharArray() }.toTypedArray(), length).map { it.map { it.joinToString("") } }
    val part1 = "                  # "
    val part2 = "#    ##    ##    ###"
    val part3 = " #  #  #  #  #  #   "

    for (i in allMap.indices) {
        for (y in 0 until (length - 2)) {
            for (x in 0 until length - part1.length) {
                val subSequence1 = allMap[i][y].subSequence(x, x + part1.length)
                if (matchingStrings(part1, subSequence1.toString())) {
                    val subSequence2 = allMap[i][y + 1].subSequence(x, x + part1.length)
                    val subSequence3 = allMap[i][y + 2].subSequence(x, x + part1.length)
                    if (matchingStrings(part2, subSequence2.toString()) && matchingStrings(part3, subSequence3.toString())) {

                        part1.forEachIndexed { index, c ->
                            if (c == '#') {
                                map[i][y][x + index] = true
                            }
                        }
                        part2.forEachIndexed { index, c ->
                            if (c == '#') {
                                map[i][y + 1][x + index] = true
                            }
                        }
                        part3.forEachIndexed { index, c ->
                            if (c == '#') {
                                map[i][y + 2][x + index] = true
                            }
                        }
                    }
                }
            }
        }
    }
    val combinedMap = Array(length) { Array(length) { false } }

    possibleCombinationsBoolean(map, length).forEach { it.forEachIndexed { y, booleans -> booleans.forEachIndexed { x, b -> combinedMap[y][x] = combinedMap[y][x] || b } } }
    return combinedMap
}

fun matchingStrings(part: String, stringToMatch: String): Boolean {
    for (i in part.indices) {
        if (part[i] == '#') {
            if (stringToMatch[i] != '#') {
                return false
            }
        }
    }
    return true
}

fun buildString(builderAction: Array<Array<Array<CharArray>>>, imagesSize: Int, imageSize: Int): Array<String> {

    val newImageSize = imageSize - 2
    val result = Array(imagesSize * newImageSize) { "" }

    for (y in 0 until imagesSize) {
        for (yImage in 0 until imageSize) {
            for (x in 0 until imageSize) {
                print(builderAction[y][x][yImage].joinToString("") + ":")
            }
            println("")
        }
        println("")

    }


    for (y in 0 until imagesSize) {
        for (yImage in 0 until newImageSize) {
            for (x in 0 until imagesSize) {
                result[y * newImageSize + yImage] += builderAction[y][x][yImage + 1].drop(1).dropLast(1).joinToString("")
            }
        }
    }
    return result
}


fun buildMap(corners: MutableList<List<Array<CharArray>>>, sides: MutableList<List<Array<CharArray>>>, middles: MutableList<List<Array<CharArray>>>,
             imageSize: Int, y: Int, x: Int, imagesSize: Int,
             currentMap: Array<Array<Array<CharArray>>>): Array<Array<Array<CharArray>>> {


    val lists: MutableList<List<Array<CharArray>>>
    var corner = false
    var side = false
    var middle = false
    if ((y == 0 || y == imagesSize - 1) && (x == 0 || x == imagesSize - 1)) {
        lists = corners
        corner = true
    } else if (y == 0 || x == 0 || x == imagesSize - 1 || y == imagesSize - 1) {
        lists = sides
        side = true
    } else {
        lists = middles
        middle = true
    }
    var counter = 0
    for (list in lists) {
        for (rotation in list) {
            if (verifyImages(rotation, currentMap, imagesSize, imageSize, y, x)) {


                currentMap[y][x] = rotation
                if (y == imagesSize - 1 && x == imagesSize - 1) {
                    return currentMap
                }
                val newCorner = corners.toMutableList()
                val newSide = sides.toMutableList()
                val newMiddle = middles.toMutableList()
                if (corner) {
                    newCorner.removeAt(counter)
                } else if (side) {
                    newSide.removeAt(counter)
                } else {
                    newMiddle.removeAt(counter)

                }

                val newResult = buildMap(newCorner, newSide, newMiddle, imageSize,
                        y + (x + 1) / imagesSize, ((x + 1) % imagesSize), imagesSize, currentMap)

                if (newResult.isNotEmpty()) {
                    return newResult
                }
                currentMap[y][x] = Array(imageSize) { CharArray(imageSize) }

            }
        }
        counter += 1
    }
    return emptyArray()
}

const val zero = 0.toChar()

fun verifyImages(imageToVerify: Array<CharArray>, image: Array<Array<Array<CharArray>>>, imagesSize: Int, imageSize: Int, y: Int, x: Int): Boolean {
    if (y != 0) {
        val imageAbow = image[y - 1][x]
        for (row in 0 until imageSize) {
            if (imageToVerify[0][row] != imageAbow[imageSize - 1][row]) {
                return false
            }
        }
    }
    if (y + 1 != imagesSize) {
        val imageBelow = image[y + 1][x]
        if (imageBelow[0][0] != zero) {
            for (row in 0 until imageSize) {
                if (imageToVerify[imageSize - 1][row] != imageBelow[0][row]) {
                    return false
                }
            }

        }
    }
    if (x != 0) {
        val imageToTheLeft = image[y][x - 1]
        if (imageToTheLeft[0][0] != zero) {
            for (column in 0 until imageSize) {
                if (imageToVerify[column][0] != imageToTheLeft[column][imageSize - 1]) {
                    return false
                }
            }
        }
    }

    if (x + 1 != imagesSize) {
        val imageToTheRight = image[y][x + 1]
        if (imageToTheRight[0][0] != zero) {
            for (column in 0 until imageSize) {
                if (imageToVerify[column][imageSize - 1] != imageToTheRight[column][0]) {
                    return false
                }
            }
        }
    }
    return true
}


fun verifyImages(image: Array<Array<Array<CharArray>>>, imagesSize: Int, imageSize: Int): Boolean {
    for (y in 0 until imagesSize) {
        for (x in 0 until imagesSize) {
            if (y != 0) {
                for (row in 0 until imageSize) {
                    if (image[y][x][0][row] != image[y - 1][x][imageSize - 1][row]) {
                        return false
                    }
                }
            }
            if (y + 1 != imagesSize) {
                for (row in 0 until imageSize) {
                    if (image[y][x][imageSize - 1][row] != image[y + 1][x][0][row]) {
                        return false
                    }
                }
            }

            if (x != 0) {
                for (column in 0 until imageSize) {
                    if (image[y][x][column][0] != image[y][x - 1][column][imageSize - 1]) {
                        return false
                    }
                }
            }
            if (x + 1 != imagesSize) {
                for (column in 0 until imageSize) {
                    if (image[y][x][column][imageSize - 1] != image[y][x + 1][column][0]) {
                        return false
                    }
                }
            }
        }
    }
    return true
}


fun possibleCombinationsBoolean(image: Array<Array<Array<Boolean>>>, size: Int): Array<Array<Array<Boolean>>> {
    val result = Array<Array<Array<Boolean>>>(8) { emptyArray() }
    val currentImage = image
    result[0] = currentImage[0]

    for (i in 1..3) {
        result[i] = rotateBool(currentImage[i], size, i)
    }
    result[4] = flipBool(currentImage[4], size)
    for (i in 5..7) {
        result[i] = flipBool(rotateBool(currentImage[i], size, i - 4), size)
    }
    return result
}

fun rotateBool(image: Array<Array<Boolean>>, size: Int, rotations: Int): Array<Array<Boolean>> {
    var result = Array(size) { Array(size) { false } }
    val adjustedSize = size - 1
    image.forEachIndexed { y, chars -> chars.forEachIndexed { x, c -> result[x][adjustedSize - y] = c } }


    for (i in 1 until rotations) {
        val newresult = Array(size) { Array(size) { false } }
        result.forEachIndexed { y, chars -> chars.forEachIndexed { x, c -> newresult[x][adjustedSize - y] = c } }
        result = newresult
    }
    return result
}

fun flipBool(image: Array<Array<Boolean>>, size: Int): Array<Array<Boolean>> {
    val result = Array(size) { Array(size) { false } }
    val adjustedSize = size - 1
    image.forEachIndexed { y, chars -> chars.forEachIndexed { x, c -> result[y][adjustedSize - x] = c } }
    return result
}