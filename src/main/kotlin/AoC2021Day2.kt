import java.io.BufferedReader
import java.io.File
import java.net.URL
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths


fun lösningA(lista: List<String>): Int {
    var horizontal = 0
    var depth = 0

    lista.filter { it.contains("forward ") }.map { it.last().digitToInt() }.forEach { horizontal += it }
    lista.filter { it.contains("down ") }.map { it.last().digitToInt() }.forEach { depth += it }
    lista.filter { it.contains("up ") }.map { it.last().digitToInt() }.forEach { depth -= it }

    val firstSolution = horizontal * depth
    println("Lösning: $firstSolution")
    return firstSolution
}


fun lösningB(read: BufferedReader): Int {
    var horizontal2 = 0
    var depth2 = 0
    var aim = 0

    var line: String?
    while (read.readLine().also { line = it } != null)
        if (line!!.contains("up ")) {
            aim -= line!!.last().digitToInt()
        } else if (line!!.contains("down ")) {
            aim += line!!.last().digitToInt()
        } else if (line!!.contains("forward ")) {
            horizontal2 += line!!.last().digitToInt()
            depth2 += aim * line!!.last().digitToInt()
        }
    println("Lösning 2 = " + horizontal2 * depth2)
    return horizontal2 * depth2
}


fun förbättradLösningA(input: List<String>): String {
    var framåt = 0
    var djup = 0

    input.forEach { line ->
        val value = line.substringAfter(' ').toInt()

        when (line[0]) {
            'f' -> framåt += value
            'u' -> djup -= value
            'd' -> djup += value
        }
    }
    return "${framåt * djup}"
}


fun förbättradLösningB(input: List<String>): String {
    var framåt = 0
    var djup = 0
    var aim = 0

    input.forEach { line ->
        val value = line.substringAfter(' ').toInt()

        when (line[0]) {
            'f' -> {framåt += value
                djup += value * aim
            }
            'u' -> aim -= value
            'd' -> aim += value
        }
    }
    return "${framåt * djup}"
}


fun main() {  // AoC 2021 dag 2
    val data: List<String> = File("src\\main\\kotlin\\AoC2021Day2.txt").readLines()
    val reader = File("src\\main\\kotlin\\AoC2021Day2.txt").bufferedReader()

    println(lösningA(data))
    println(lösningB(reader))

    println(förbättradLösningA(data))
    println(förbättradLösningB(data))

}



private fun inspirationFrånGithub() {
    //https://github.com/valiant-code/AdventOfCode/blob/master/2021/src/main/kotlin/Day2.kt
    //Verkar klocka tiden det tar att köra programmet.
    //Mappar först datat till en Map med Key=String med instruktioner och Value=en Int som används vid utträkningen
    //Känns lite svår att läsa och onödigt komplicerad

    TimeUtil.startClock(1, ::partOne)
    TimeUtil.startClock(2, ::partTwo)
}

private class SubmarineInstruction(
    val command: String,
    val value: Int
) {
    constructor(instructionString: String) : this(
        instructionString.substringBefore(" "),
        instructionString.substringAfter(" ").toInt()
    );
};
private infix fun Int.greaterThanEquals(that: Int): Boolean = this > that;
private fun Boolean.toInt() = if (this) 1 else 0


private fun partOne() {
    //forward X increases the horizontal position by X units.
    //down X increases the depth by X units.
    //up X decreases the depth by X units.
    var depth = 0;
    var horizontal = 0;
    val input = InputUtil.readFileAsStringList("src\\main\\kotlin\\AoC2021Day2.txt")
        .map(::SubmarineInstruction)
        .forEach {
            when (it.command) {
                "forward" -> horizontal += it.value;
                "down" -> depth += it.value;
                "up" -> depth -= it.value;
            }
        }

    println("pt 1 answer: ${horizontal * depth}")
}

private fun partTwo() {
//    down X increases your aim by X units.
//    up X decreases your aim by X units.
//    forward X does two things:
//      It increases your horizontal position by X units.
//      It increases your depth by your aim multiplied by X.
    var depth = 0;
    var horizontal = 0;
    var aim = 0;

    val input = InputUtil.readFileAsStringList("src\\main\\kotlin\\AoC2021Day2.txt")
        .map(::SubmarineInstruction)
        .forEach {
            when (it.command) {
                "forward" -> {
                    horizontal += it.value; depth += aim * it.value
                }

                "down" -> aim += it.value;
                "up" -> aim -= it.value;
            }
        }

    println("pt 2 answer: ${horizontal * depth}")
}

object TimeUtil {
    private var start = System.currentTimeMillis()
    private var pt = 0
    fun startClock(part: Int, functionToTime: () -> Unit) {
        pt = part
        start = System.currentTimeMillis()
        //execute the function, and then stop the timer
        functionToTime();
        time();
    }

    fun time() {
        val end = System.currentTimeMillis()
//        println("Pt $pt Start Time: $start");
//        println("Pt $pt End Time:   $end");
        println("Pt $pt Time Taken: ${(end - start) / 1000f}")
    }
}

object InputUtil {
    // sample filepath: day1/input.txt
    fun readFileAsString(filepath: String): String {
        val file = getFileFromResources(filepath)
        return readFile(file.path)
    }

    fun readFileAsStringList(filepath: String, delimiter: String = "\n"): List<String> {
        val file = getFileFromResources(filepath)
        return readFile(file.path).split(delimiter);
    }

    fun readFileAsIntList(filepath: String, delimiter: String = "\n"): List<Int> {
        return readFileAsStringList(filepath, delimiter).map { s: String -> s.toInt() }
    }

    fun readFileAsLongList(filepath: String, delimiter: String = "\n"): List<Long> {
        return readFileAsStringList(filepath, delimiter).map { s: String -> s.toLong() }
    }

    private fun readFile(path: String): String {
        val encoded = Files.readAllBytes(Paths.get(path))
        return String(encoded, StandardCharsets.UTF_8)
    }

    private fun getFileFromResources(fileName: String): File {
        val resource = this::class.java.getResource(fileName)
        return if (resource == null) {
            throw IllegalArgumentException("file is not found!")
        } else {
            File(resource.file)
        }
    }
}



//https://github.com/vini2003/Advent-of-Code-2021/blob/dev/src/main/kotlin/dev/vini2003/adventofcode/solution/Day2Solution.kt
//Enkel lösning med minimalt med kod
//Gillar hur lättläst och minimal koden är
//Skapar variabeln lines som är List<String>
//Skapar Int value, som är ett värde som associeras med varje command
//För varje command som börjar med Char 'f' så läggs det associerade value till antingen depth eller forward

fun solvePart1(input: URL): String {
    val lines = input.readText().trimEnd().lines()

    var forward = 0
    var depth = 0

    lines.forEach { line ->
        val value = line.substringAfter(' ').toInt()

        when (line[0]) {
            'f' -> forward += value
            'u' -> depth -= value
            'd' -> depth += value
        }
    }

    return "${forward * depth}"
}

fun solvePart2(input: URL): String {
    val lines = input.readText().trimEnd().lines()

    var aim = 0
    var depth = 0
    var forward = 0

    lines.forEach { line ->
        val value = line.substringAfter(' ').toInt()

        when (line[0]) {
            'f' -> {
                forward += value
                depth += value * aim
            }

            'u' -> aim -= value
            'd' -> aim += value
        }
    }

    return "${forward * depth}"
}
