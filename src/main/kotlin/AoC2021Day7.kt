import java.io.File
import kotlin.math.abs
import kotlin.math.absoluteValue

fun räknaMinstaBensinåtgång(input: String): Int {
    var minstaÅtgången = Int.MAX_VALUE
    var krabbList = input.split(",").toMutableList().map { it.toInt() }

    for (potentiellKrabbposition in 0 until krabbList.size) { //för varje potentiell krabbposition
        var åtgång = 0

        for (krabba in krabbList) { // räkna ut summa av förflyttningar
            åtgång += Math.abs(krabba - potentiellKrabbposition)
        }

        if (åtgång < minstaÅtgången) minstaÅtgången = åtgång
    }
    return minstaÅtgången
}

fun räknaMinstaBensinåtgångB(input: String): Int {
    var minstaÅtgången = Int.MAX_VALUE
    var krabbList = input.split(",").toMutableList().map { it.toInt() }

    for (potentiellKrabbposition in 0 until krabbList.size) {
        var totalÅtgång = 0

        for (krabba in krabbList) {
            var steg = Math.abs(krabba - potentiellKrabbposition)
            var åtgång = steg*(steg+1) / 2
            totalÅtgång += åtgång
        }

        if (totalÅtgång < minstaÅtgången) minstaÅtgången = totalÅtgång
    }
    return minstaÅtgången
}

fun förbättradRäknaMinstaBensinåtgång(input: String): Int {
    var minstaÅtgången = Int.MAX_VALUE
    val krabbList = input.split(",").toMutableList().map { it.toInt() }
    val minPossition = krabbList.minOrNull() ?: 0
    val maxPossition = krabbList.maxOrNull() ?: 0

    for (potentiellKrabbposition in minPossition until maxPossition) { //för varje potentiell krabbposition
        var åtgång = 0

        for (krabba in krabbList) { // räkna ut summa av förflyttningar
            åtgång += Math.abs(krabba - potentiellKrabbposition)
        }

        if (åtgång < minstaÅtgången) minstaÅtgången = åtgång
    }
    return minstaÅtgången
}

fun förbättradRäknaMinstaBensinåtgångB(input: String): Int {
    var minstaÅtgången = Int.MAX_VALUE
    val krabbList = input.split(",").toMutableList().map { it.toInt() }
    val minPossition = krabbList.minOrNull() ?: 0
    val maxPossition = krabbList.maxOrNull() ?: 0

    for (potentiellKrabbposition in minPossition until maxPossition) { //för varje potentiell krabbposition
        var åtgång = 0

        for (krabba in krabbList) { // räkna ut summa av förflyttningar
            var steg = Math.abs(krabba - potentiellKrabbposition)
            åtgång += steg*(steg+1) / 2
        }

        if (åtgång < minstaÅtgången) minstaÅtgången = åtgång
    }
    return minstaÅtgången
}

fun main() {
    val data: String = File("src\\main\\kotlin\\AoC2021Day7.txt").readText()

    println("lösning: " + räknaMinstaBensinåtgång(data))
    println("Förbättrad lösning: ${förbättradRäknaMinstaBensinåtgång(data)}")
    println("Förbättrad lösning B: " + förbättradRäknaMinstaBensinåtgångB(data))


}

// https://github.com/tginsberg/advent-2021-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2021/Day07.kt
//Använder en extension function asRange() för standard funktionen gör inte exakt det han vill för att få korrekt range. Jag har gjort det felet i min lösning
//solve() räknar ut antal steg som behövs och för att lösa del 2 har han lagt en extra uträkning i solvePart2
//Använder en Map för att samla ihop duplicerat data och effektivisera programmet
class Day07(input: String) {

    private val crabs: Map<Int, Int> = input.split(",").map { it.toInt() }.groupingBy { it }.eachCount()

    fun solvePart1(): Int =
        solve { it }

    fun solvePart2(): Int =
        solve { distance ->
            (distance * (distance + 1)) / 2
        }

    private fun solve(fuelCost: (Int) -> Int): Int =
        crabs.keys.asRange().minOf { target ->
            crabs.map { (crab, crabCount) ->
                fuelCost((target - crab).absoluteValue) * crabCount
            }.sum()
        }

    private fun Set<Int>.asRange(): IntRange =
        this.minOf { it }..this.maxOf { it }
}


//https://github.com/jkpr/advent-of-code-2021-kotlin/blob/master/src/day07/Day07.kt
//Räknar tid, säkert tiden för exekvering
//Svårläst men imponerande utträkning med minimal kod
fun moveCrabs(input: List<String>, fuelCost: (distance: Int) -> Int) = input[0].split(",").map { it.toInt() }.let { crabs ->
    (0..(crabs.maxOf { it })).minOf { pos -> crabs.sumOf { fuelCost(abs(it - pos)) }}
}

fun part11(input: List<String>) = moveCrabs(input) { it }

fun part22(input: List<String>) = moveCrabs(input) { it * (it + 1) / 2 }

fun mainn() {
    val subpackage = "day07"
//    val input = readInput(subpackage, "input")
//    val input = readInput(subpackage, "test_input")
    val start = System.currentTimeMillis()
//    val result1 = part1(input)
//    val result2 = part2(input)
//    println("Part 1: $result1")
//    println("Part 2: $result2")
    val end = System.currentTimeMillis()
    val delta = (end - start) / 1000.0
    println("Time elapsed: $delta seconds")
}


//https://www.reddit.com/r/adventofcode/comments/rar7ty/2021_day_7_solutions/
//SolveGeneric är en högre ordningens funktion som tar in methoden sumTransformation
//Använder korrekt range (min..max) för att kolla på positioner som krabbor faktiskt har

/*private val parsed = raw[0].split(",").map { it.toInt() }
private val min = parsed.minOf { it }
private val max = parsed.maxOf { it }

private fun solveGeneric(sumTransformation: (Int, Int) -> Int): Int = (min..max).minOf { largest -> parsed.sumOf { sumTransformation(largest, it) } }
override fun part1() = solveGeneric { largest, current -> abs(largest - current) }
override fun part2() = solveGeneric { largest, current ->
    abs(largest - current).let { it * (it + 1) / 2 } // gaussian sum aka formula for the sum of 1..n, yes also could do (1..abs()).sum() but thats slower
}*/



//https://github.com/nielsutrecht/adventofcode/blob/master/src/main/kotlin/com/nibado/projects/advent/y2021/Day07.kt
//Fina uträkningar. Här kännss det som att en matematiker har jobbat med kod
//Minimal kod men för mig lite svårläst
/*
object Day07 : Day {
    private val values = resourceString(2021, 7).split(',').map { it.toInt() }

    override fun part1() = (values.minOrNull()!!..values.maxOrNull()!!).minOf { pos ->
        values.sumOf { (it - pos).absoluteValue }
    }

    override fun part2() = (values.minOrNull()!!..values.maxOrNull()!!).minOf { pos ->
        values.sumOf { (it - pos).absoluteValue.let { it * (it + 1) / 2 } }
    }
}*/
