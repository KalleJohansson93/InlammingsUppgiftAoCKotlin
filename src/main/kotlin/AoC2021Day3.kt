import java.io.File

fun räknaEnergikonsumptionA(input: List<String>): Int {

    var gamma = ""
    var epsilon = ""
    val radLängd = input.get(0).length

    for (index in 0 until radLängd) {
        var ettor = 0
        var nollor = 0

        input.forEach { line ->
            if (line[index].equals('1')) ettor++ else nollor++
        }

        if (ettor > nollor) {
            gamma += '1'
            epsilon += '0'
        } else {
            gamma += '0'
            epsilon += '1'
        }
    }

    return binärTillDecimal(gamma) * binärTillDecimal(epsilon)
}

fun räknaEnergikonsumptionB(input: List<String>): Int {

    var oxygenList = input.toList()
    var CO2ScrubberList = input.toList()
    val radLängd = input.get(0).length

    for (index in 0 until radLängd) {
        if (oxygenList.size > 1) {
            var mestFörekommandeTalet =
                if (oxygenList.count { it[index] == '1' } >= oxygenList.count { it[index] == '0' }) '1' else '0'
            oxygenList = oxygenList.filter { it[index] == mestFörekommandeTalet }
        }

        if (CO2ScrubberList.size > 1) {
            var minstFörekommandeTalet =
                if (CO2ScrubberList.count { it[index] == '1' } < CO2ScrubberList.count { it[index] == '0' }) '1' else '0'
            CO2ScrubberList = CO2ScrubberList.filter { it[index] == minstFörekommandeTalet }
        }
    }
    return binärTillDecimal(oxygenList.get(0)) * binärTillDecimal(CO2ScrubberList.get(0))
}

fun förbättradRäknaEnergikonsumptionA(input: List<String>): Any {
    val values = input.map { it.toCharArray().map { it.digitToInt() } }

    val gamma = values.mestFörekommande()
    val epsilon = gamma.map { it xor 1 }

    return gamma.joinToString("").toInt(2) * epsilon.joinToString("").toInt(2) //Binär -> decimal
}

fun förbättradRäknaEnergikonsumptionB(input: List<String>): Int {
    //Den här blev ganska bra som den är. Ändrade bara så metoden binärTillDecimal() inte behövs
    var oxygenList = input.toList()
    var CO2ScrubberList = input.toList()
    val radLängd = input.get(0).length

    for (index in 0 until radLängd) {
        if (oxygenList.size > 1) {
            var mestFörekommandeTalet =
                if (oxygenList.count { it[index] == '1' } >= oxygenList.count { it[index] == '0' }) '1' else '0'
            oxygenList = oxygenList.filter { it[index] == mestFörekommandeTalet }
        }

        if (CO2ScrubberList.size > 1) {
            var minstFörekommandeTalet =
                if (CO2ScrubberList.count { it[index] == '1' } < CO2ScrubberList.count { it[index] == '0' }) '1' else '0'
            CO2ScrubberList = CO2ScrubberList.filter { it[index] == minstFörekommandeTalet }
        }
    }
    return oxygenList.joinToString("").toInt(2) * CO2ScrubberList.joinToString("").toInt(2) //Binär -> decimal
}

 fun List<List<Int>>.mestFörekommande() = first().indices//first kommer åt den yttre listan och indices kommer åt den inre listan
    .map { idx -> count { it[idx] == 1 } } ///Räknar antal ettor vid varje idx
    .map { if (it >= size - it) 1 else 0 } //Kollar om count(it) från föregående steg är större eller lika med antal elemnen minus (it)

fun binärTillDecimal(binär: String): Int {
    var decimal = 0
    var multipel = 1

    for (index in binär.length - 1 downTo 0) {
        if (binär[index] == '1') {
            decimal += multipel
        }
        multipel *= 2
    }
    return decimal
}


fun main() {
    val data: List<String> = File("src\\main\\kotlin\\AoC2021Day3.txt").readLines()

    println(räknaEnergikonsumptionA(data))
    println(räknaEnergikonsumptionB(data))
    println(förbättradRäknaEnergikonsumptionA(data))
    println(förbättradRäknaEnergikonsumptionB(data))

    //println(binärTillDecimal("1010"))
}


//https://github.com/nielsutrecht/adventofcode/blob/master/src/main/kotlin/com/nibado/projects/advent/y2021/Day03.kt
//Använder mostCommmon för båda delarna när han räknar fram dom mest förekommande talen vilket ger snygg kod
//Verkar ha hemmaskrivna koder för AoC vilket gör det svårare för en annan att testa hans lösning
//part1 har verkligen minimal kod och är lättläst
/*object Day03 : Day {
    private val values = resourceLines(2021, 3).map { it.toCharArray().map { it.digitToInt() } }

    private fun List<List<Int>>.mostCommon() = first().indices//first kommer åt den yttre listan och indices kommer åt den inre listan
        .map { idx -> count { it[idx] == 1 } } ///Räknar antal ettor vid varje idx
        .map { if (it >= size - it) 1 else 0 } //Kollar om count(it) från föregående steg är större eller lika med antal elemnen minus (it)

    override fun part1(): Any {
        val binary = values.mostCommon()
        val inverted = binary.map { it xor 1 }

        return binary.joinToString("").toLong(2) * inverted.joinToString("").toLong(2) //Här omvandlas binära siffror till decimalform
    }

    override fun part2(): Any {
        fun solve(test: (Int, Int) -> Boolean): Long {
            var rating = values.toMutableList()
            var bitIndex = 0
            while (rating.size > 1) {
                val most = rating.mostCommon()[bitIndex]

                rating.removeIf { test(it[bitIndex], most) }
                bitIndex++
            }
            return rating.first().joinToString("").toLong(2)
        }

        return solve { a, b -> a != b } * solve { a, b -> a == b }
    }*/


//https://todd.ginsberg.com/post/advent-of-code/2021/day3/
//solvePart1 skapar först upp val gamma där man mappar upp två delar och beroende på uträkningen och lägger sen till i gamma med hjälp av joinToString()
//val epsilon skapas enkelt genom att använda gamma och bara sätta dit motsatta värdet mot gamma med hjälp av joinToString
//SolvePart2 tar hjälp av två extension functions longest och shortest för enklare kod
class Day03(private val input: List<String>) {
    fun solvePart1(): Int {
        val gamma = input.first().indices.map { column ->
            if (input.count { it[column] == '1' } > input.size / 2) '1' else '0'
        }.joinToString("")
        val epsilon = gamma.map { if (it == '1') '0' else '1' }.joinToString("")
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun solvePart2(): Int =
        input.bitwiseFilter(true).toInt(2) * input.bitwiseFilter(false).toInt(2)

    private fun List<String>.bitwiseFilter(keepMostCommon: Boolean): String =
        first().indices.fold(this) { inputs, column ->
            if (inputs.size == 1) inputs else {
                val split = inputs.partition { it[column] == '1' }
                if (keepMostCommon) split.longest() else split.shortest()
            }
        }.first()

    private fun <T> Pair<List<T>, List<T>>.longest(): List<T> =
        if (first.size >= second.size) first else second

    private fun <T> Pair<List<T>, List<T>>.shortest(): List<T> =
        if (first.size < second.size) first else second
}
