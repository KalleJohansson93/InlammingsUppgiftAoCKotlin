import java.io.File


fun riskLevel(input: List<String>): Int {

    var dubbeltFöregåendeString =
        "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"
    var föregåendeString =
        "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"
    var lågaNummer = 0
    var input2 = input.toMutableList()
    var radLängd = input2.get(1).length
    var string = "9".repeat(radLängd)
    println(string)
    input2.add(string)

    for (numbers in input2) {
        var index = 0

        for (number in numbers) {
            var före = föregåendeString.getOrNull(index - 1)?.digitToInt() ?: 9
            var efter = föregåendeString.getOrNull(index + 1)?.digitToInt() ?: 9
            var över = dubbeltFöregåendeString.get(index).digitToInt()
            var under = numbers.get(index).digitToInt()
            var nuvarande = föregåendeString.get(index).digitToInt()
            if (under > nuvarande
                && över > nuvarande
                && efter > nuvarande
                && före > nuvarande
            ) lågaNummer += nuvarande + 1
            index++
        }
        dubbeltFöregåendeString = föregåendeString
        föregåendeString = numbers
    }
    return lågaNummer
}


fun riskLevel2(input: List<String>): Int {
    var totalRiskLevel = 0

    for (y in input.indices) {
        for (x in input[y].indices) {
            val nuvarande = input[y][x]
            val gränsar = listOfNotNull(
                input.getOrNull(y - 1)?.getOrNull(x),
                input.getOrNull(y + 1)?.getOrNull(x),
                input[y].getOrNull(x - 1),
                input[y].getOrNull(x + 1)
            )

            if (gränsar.all { nuvarande < it }) {
                totalRiskLevel += nuvarande.digitToInt() + 1
            }
        }
    }
    return totalRiskLevel
}


fun main() { //Den här AdventOfCode gjorde jag extra på måndagen
    val data = File("src\\main\\kotlin\\AoC2021Day9.txt").readLines()
    println("lösning: " + riskLevel2(data))
    //println("lösning: " + förbättradDecoding(data) )

}

//https://github.com/tginsberg/advent-2021-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2021/Day09.kt
//Lite onödigt komplicerad
/*class Day09(input: List<String>) {

    private val caves: Array<IntArray> = parseInput(input)

    fun solvePart1(): Int =
        caves.findLowPoints().sumOf {
            caves[it] + 1
        }

    fun solvePart2(): Int =
        caves.findLowPoints()
            .map { getBasinSize(it) }
            .sortedDescending()
            .take(3)
            .reduce { a, b -> a * b }

    private fun getBasinSize(point: Point2d): Int {
        val visited = mutableSetOf(point)
        val queue = mutableListOf(point)
        while (queue.isNotEmpty()) {
            val newNeighbors = queue.removeFirst()
                .validNeighbors()
                .filter { it !in visited }
                .filter { caves[it] != 9 }
            visited.addAll(newNeighbors)
            queue.addAll(newNeighbors)
        }
        return visited.size
    }

    private fun Array<IntArray>.findLowPoints(): List<Point2d> =
        flatMapIndexed { y, row ->
            row.mapIndexed { x, height ->
                Point2d(x, y).takeIf { point ->
                    point.validNeighbors().map { caves[it] }.all { height < it }
                }
            }.filterNotNull()
        }

    private fun Point2d.validNeighbors(): List<Point2d> =
        neighbors().filter { it in caves }

    private operator fun Array<IntArray>.get(point: Point2d): Int =
        this[point.y][point.x]

    private operator fun Array<IntArray>.contains(point: Point2d): Boolean =
        point.y in this.indices && point.x in this[point.y].indices

    private fun parseInput(input: List<String>): Array<IntArray> =
        input.map { row ->
            row.map { it.digitToInt() }.toIntArray()
        }.toTypedArray()
        */



//https://www.reddit.com/r/adventofcode/comments/rca6vp/2021_day_9_solutions/
//Intressant lösning för att han använder en rekursiv funktion. Kankse inte bästa lösningen men intressant
private fun doSomeWork(
    currentLocation: Pair<Int, Int>,
    matrix: Array<Array<Int>>,
    visited: Array<Array<Boolean>>,
    maxRow: Int,
    maxCol: Int
): Int {
    val row = currentLocation.first
    val col = currentLocation.second
    if (visited[row][col]) return 0
    val cur = matrix[row][col]
    if (cur == 9) return 0
    visited[row][col] = true
    var size = 1
    if (row > 0 && matrix[row - 1][col] > cur) {
        size += doSomeWork(Pair(row - 1, col), matrix, visited, maxRow, maxCol)
    }
    if (row < maxRow && matrix[row + 1][col] > cur) {
        size += doSomeWork(Pair(row + 1, col), matrix, visited, maxRow, maxCol)
    }
    if (col > 0 && matrix[row][col - 1] > cur) {
        size += doSomeWork(Pair(row, col - 1), matrix, visited, maxRow, maxCol)
    }
    if (col < maxCol && matrix[row][col + 1] > cur) {
        size += doSomeWork(Pair(row, col + 1), matrix, visited, maxRow, maxCol)
    }
    return size
}


//https://www.reddit.com/r/adventofcode/comments/rca6vp/2021_day_9_solutions/
/*
fun solve { lines ->
    val heights = mutableMapOf<Point, Int>()
    lines.mapIndexed { y, row -> row.toList().forEachIndexed { x, height -> heights[Point(x, y)] = height.digitToInt() } }
    heights.filter { entry -> entry.key.getAdjacentSides().all { heights.getOrDefault(it, 9) > entry.value } }.map { (point, _) ->
        val visited = mutableListOf<Point>()
        val candidates = mutableListOf(point)
        while (candidates.isNotEmpty()) {
            val next = candidates.removeFirst()
            candidates += next.getAdjacentSides().filter { it in heights.keys && it !in visited && it !in candidates &&
                    heights.getOrDefault(it, 9) > heights.getValue(next) && heights[it] != 9 }
            visited += next
        }
        visited.size
    }.sortedDescending().take(3).reduce { acc, i -> acc * i }
}*/


//Chat-GPT
//Det här är nog den lösningen som ser snyggast ut
fun solution(input: List<String>): Int {
    var totalRiskLevel = 0

    for (y in input.indices) {
        for (x in input[y].indices) {
            val height = input[y][x].digitToInt()
            val up = if (y > 0) input[y - 1][x].digitToInt() else 10
            val down = if (y < input.size - 1) input[y + 1][x].digitToInt() else 10
            val left = if (x > 0) input[y][x - 1].digitToInt() else 10
            val right = if (x < input[y].length - 1) input[y][x + 1].digitToInt() else 10

            if (height < up && height < down && height < left && height < right) {
                totalRiskLevel += height + 1
            }
        }
    }

    return totalRiskLevel
}
