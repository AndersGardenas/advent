package advent.app

import java.io.File
import java.io.FileNotFoundException

fun fileToStringList(fileName: String): List<String>
        = File(fileName).useLines { it.toList() }
