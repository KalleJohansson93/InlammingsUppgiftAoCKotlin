import java.io.File

fun räknaEnergikonsumption(input: List<String>): String {

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
            epsilon += '0'}
        else {
            gamma += '0'
        epsilon += '1'}
    }

    println(gamma)
    println(epsilon)
    return gamma
}

fun binärTillDecimal(binär : String) : Int {
    var decimal = 0
    for (index in binär.length - 1 downTo  0) {
        var multipel = 1

        if (binär[index].equals('1')) {
            decimal = decimal + (multipel * index)
        }
        multipel *= 2
    }
    return decimal
}


fun main() {
    val data: List<String> = File("src\\main\\kotlin\\AoC2021Day3.txt").readLines()

    println(räknaEnergikonsumption(data))

    println(binärTillDecimal("1010"))
}