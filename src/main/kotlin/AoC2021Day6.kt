import java.io.File

fun räknaFiskarA(input: List<String>, days: Int): Int {
    var fiskLista: MutableList<Int> = mutableListOf()

    for (fisk in input) {
        fiskLista.addAll(fisk.split(",").map { it.toInt() })
    }

    for (index in 1..days) {
        var nyLista: MutableList<Int> = mutableListOf()

        for (fisk in fiskLista) {
            if (fisk > 0) nyLista.add(fisk - 1)
            else {
                nyLista.add(8)
                nyLista.add(6)
            }
        }
        fiskLista = nyLista
    }
    return fiskLista.size
}

//Den här lösningen är kraftigt inspirerad av https://github.com/jkpr/advent-of-code-2021-kotlin/blob/master/src/day06/Day06.kt
//Nu när man gått igenom den här lösningen och ser allt bra med den så är det svårt att inte göra väldigt lika
//Det har iallafall varit lärorikt att se en bra lösning. Den övre är min lösning men den här kan jag inte ta åt mig äran för och jag
//vill heller inte göra så mycket annorlunda för att den här lösningen är enligt mig nära optimal speciellt med tanke på minnesanvändningen
fun förbättradRäknaFiskar(input: List<String>, days: Int): Long {
    var fiskMap = input[0].split(",").map { it.toInt() }.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        .toMutableMap()
    //println(fiskMap)
    repeat(days) {
        val nästaGenMap = fiskMap.filterKeys { it != 0 }.mapKeys { it.key - 1 }.toMutableMap()
        nästaGenMap[6] = nästaGenMap.getOrDefault(6, 0) + fiskMap.getOrDefault(0, 0)
        nästaGenMap[8] = fiskMap.getOrDefault(0, 0)
        fiskMap = nästaGenMap
        println(fiskMap)
    }
    return fiskMap.values.sum() //Förenklad läsbarhet här
}


fun main() {
    val data: List<String> = File("src\\main\\kotlin\\AoC2021Day6.txt").readLines()

    println("lösning: " + räknaFiskarA(data, 80))
    println(förbättradRäknaFiskar(data, 80))

}


//https://www.reddit.com/r/adventofcode/comments/r9z49j/2021_day_6_solutions/
//Kortaste lösningen jag sett men också väldigt svårläst med för mycket saker som händer på varje rad
//Använder .fold och har bra minnesanvändning för att kunna klara av del 2
private fun breedFish(h: List<Int>, d: Int) =
    (0..d - 1).fold((0..8).map { a -> h.count { a == it }.toLong() }.toMutableList())
    { f, e -> f[(e + 7) % 9] += f[e % 9];f }.sum()


//https://github.com/tginsberg/advent-2021-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2021/Day06.kt
//Använder sig av en LongArray för ökad minnesanvändning
class Day06(inputt: String) {
    val input = File("src\\main\\kotlin\\AoC2021Day6.txt").readText()

    private val fishiesPerDay: LongArray = parseInput(input)

    fun solvePart1(): Long =
        simulateDays(80)

    fun solvePart2(): Long =
        simulateDays(256)

    private fun simulateDays(days: Int): Long {
        repeat(days) {
            fishiesPerDay.rotateLeftInPlace()
            fishiesPerDay[6] += fishiesPerDay[8]
        }
        return fishiesPerDay.sum()
    }

    private fun LongArray.rotateLeftInPlace() {
        val leftMost = first()
        this.copyInto(this, startIndex = 1)
        this[this.lastIndex] = leftMost
    }

    private fun parseInput(input: String): LongArray =
        LongArray(9).apply {
            input.split(",").map { it.toInt() }.forEach { this[it] += 1L }
        }
}



//https://www.reddit.com/r/adventofcode/comments/r9z49j/2021_day_6_solutions/
//https://github.com/jkpr/advent-of-code-2021-kotlin/blob/master/src/day06/Day06.kt
//Mappar nyckel till "dagar kvar till förökning" och value till "Totalt antal fiskar" för bra minnesanvändning som krävs för del2
//Använder .groupingBy för att fish<dagarTillFörökning, antalFiskar> ska innehålla objekt som ser ut: {3=42, 5=36, 1=112, 4=50, 2=60}
//repeat satsen filtrerar ut fiskar som har ålder 0 med.filterKeys och .mapKeys sänker dagarTillFörökning med 1
fun spawnLanternfish(input: List<String>, days: Int): Long {
    var fish = input[0].split(",").map { it.toInt() }.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        .toMutableMap()
    println(fish)
    repeat(days) { _ ->
        val nextGen = fish.filterKeys { it != 0 }.mapKeys { it.key - 1 }.toMutableMap()
        nextGen[6] = nextGen.getOrDefault(6, 0) + fish.getOrDefault(0, 0)
        nextGen[8] = fish.getOrDefault(0, 0)
        fish = nextGen
        println(fish)
    }
    return fish.values.reduce { a, b -> a + b }
}

fun part1(input: List<String>) = spawnLanternfish(input, 80)
fun part2(input: List<String>) = spawnLanternfish(input, 256)



//https://www.reddit.com/r/adventofcode/comments/r9z49j/2021_day_6_solutions/
//Ganska lättläst för en nybörjare
/*override var DAY = 6

    override fun challenge1(): Long {
        var fish = getFishFromInput()
        return passTime(80, fish)
    }

    override fun challenge2(): Long {
        var fish = getFishFromInput()
        return passTime(256, fish)
    }

    private fun getFishFromInput(): Array<Long> {
        var fileContent = FileReader.readResource("input6.txt")
        var ages = fileContent[0].split(',').map { Integer.parseInt(it) }
        var fishPerDay: Array<Long> = Array(9) { 0 }
        ages.forEach {
            fishPerDay[it] ++
        }
        return fishPerDay
    }

    fun passTime(days: Int, startingCounts: Array<Long>): Long{
        var currentCounts = startingCounts
        for( i in 1..days ) {
            var nexDay: Array<Long> = Array(9) { 0 }
            for( i in 0..8) {
                if(i == 0){
                    nexDay[6] += currentCounts[i]
                    nexDay[8] += currentCounts[i]
                } else {
                    nexDay[i-1] += currentCounts[i]
                }
            }
            currentCounts = nexDay.clone()
        }
        var count: Long = 0
        currentCounts.forEach {
            count += it
        }
        return count
    }*/
