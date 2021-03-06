package advent.app


fun main() {
    val file = fileToStringList("app/src/main/kotlin/input/input4_1.txt")
    val text = file.fold("", { total, it -> total + "\n" + it })
    val passport = text.split("\n\n")
    val total = passport.count { listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid").all { it2 -> check(it2, it) } }
    print(total)

}

private fun check(type: String, passport: String): Boolean {
    val values = passport.split(" ", "\n")
    try {
        val line = values.first { type in it }
        var value = line.split(":")[1]

        when (type) {
            "byr" -> {
                return value.toInt() in 1920..2002
            }
            "iyr" -> {
                return value.toInt() in 2010..2020
            }
            "eyr" -> {
                return value.toInt() in 2020..2030
            }
            "hgt" -> {
                return if (value.contains("in")) {
                    value = value.removeSuffix("in")
                    value.toInt() in 59..76
                } else {
                    value = value.removeSuffix("cm")
                    value.toInt() in 150..193
                }
            }
            "hcl" -> {
                return value[0] == '#' && value.length == 7 && value.drop(1).toCharArray().all { it.isDigit() || it in 'a'..'f' }
            }
            "ecl" -> {
                return value in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") && value.length == 3
            }
            "pid" -> {
                return value.all { it.isDigit() } && value.length == 9
            }
        }
    } catch (e: NoSuchElementException) {
        return false
    }
    return false
}
