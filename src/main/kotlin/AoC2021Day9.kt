import java.io.File


fun riskLevel(input: List<String>) : Int {

    var dubbeltFöregåendeString = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"
    var föregåendeString = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"
    var lågaNummer = 0
    var input2 = input.toMutableList()
    var radLängd = input2.get(1).length
    var string = "9".repeat(radLängd)
    println(string)
    input2.add(string)

    for (numbers in input2) {
        var index = 0

        for (number in numbers){
            var före = föregåendeString.getOrNull(index-1)?.digitToInt() ?:9
            var efter = föregåendeString.getOrNull(index+1)?.digitToInt() ?:9
            var över = dubbeltFöregåendeString.get(index).digitToInt()
            var under = numbers.get(index).digitToInt()
            var nuvarande = föregåendeString.get(index).digitToInt()
            if (under > nuvarande
                && över > nuvarande
                && efter > nuvarande
                && före > nuvarande) lågaNummer += nuvarande+1//lågaNummer += number.digitToInt()+1
            index++
        }
        dubbeltFöregåendeString = föregåendeString
        föregåendeString = numbers
    }
    return lågaNummer
}


fun main () {
    val data = File("src\\main\\kotlin\\AoC2021Day9.txt").readLines()
    println("lösning: " + riskLevel(data) )
    //println("lösning: " + förbättradDecoding(data) )

}