/*
Tomtarna på Nordpolen har en strikt chefs-hierarki
Högsta chefen för allt är "Tomten"
Under "Tomten" jobbar "Glader" och "Butter"
Under "Glader" jobbar "Tröger", "Trötter"och "Blyger"
Under "Butter" jobbar "Rådjuret", "Nyckelpigan", "Haren" och "Räven"
Under "Trötter" jobbar "Skumtomten"
Under "Skumtomten" jobbar "Dammråttan"
Under "Räven" jobbar "Gråsuggan" och "Myran"
Under "Myran" jobbar "Bladlusen"

Er uppgift är att illustrera tomtens arbetshierarki i en lämplig datastruktur.
(Det finns flera tänkbara datastrukturer här)

Skriv sedan en funktion där man anger ett namn på tomten eller någon av hens underhuggare som
inparameter.
Funktionen listar sedan namnen på alla underlydandesom en given person har
Expempel: Du anger "Trötter" och får som svar ["Skumtomten", "Dammråttan"]"

För att bli godkänd på uppgiften måste du använda rekursion.
 */

class Tomte(val namn: String, val underlings: List<Tomte> = emptyList())

class Tomteland {

    val tomtarMap = mapOf(
        "Tomten" to listOf("Glader", "Butter"),
        "Glader" to listOf("Tröger", "Trötter", "Blyger"),
        "Butter" to listOf("Rådjuret", "Nyckelpigan", "Haren", "Räven"),
        "Trötter" to listOf("Skumtomten"),
        "Skumtomten" to listOf("Dammråttan"),
        "Räven" to listOf("Gråsuggan", "Myran"),
        "Myran" to listOf("Bladlusen"),
        "Bladlusen" to emptyList(),
        "Dammråttan" to emptyList(),
        "Gråsuggan" to emptyList(),
        "Haren" to emptyList(),
        "Rådjuret" to emptyList(),
        "Nyckelpigan" to emptyList(),
        "Blyger" to emptyList(),
        "Tröger" to emptyList()
    )

    //TODO: skapa en datastruktur för att lagra tomtarna och deras relationer i
/*    val bladlusen = Tomte("Bladlusen")
    val dammråttan = Tomte("Dammråttan")
    val gråsuggan = Tomte("Gråsuggan")
    val haren = Tomte("Haren")
    val rådjuret = Tomte("Rådjuret")
    val nyckelpigan = Tomte("Nyckelpigan")
    val blyger = Tomte("Blyger")
    val tröger = Tomte("Tröger")
    val skumtomten = Tomte("Skumtomten", listOf(dammråttan))
    val myran = Tomte("Myran", listOf(bladlusen))
    val räven = Tomte("Räven", listOf(gråsuggan, myran))
    val trötter = Tomte("Trötter", listOf(skumtomten))
    val butter = Tomte("Butter", listOf(rådjuret, nyckelpigan, haren, räven))
    val glader = Tomte("Glader", listOf(tröger, trötter, blyger))
    val tomten = Tomte("Tomten", listOf(glader, butter))*/

    // current namn är den tomte vars underlydande ni vill ta fram
    //res är listan som håller alla underlydande
    fun getUnderlings(currentName: String, res: MutableList<String>): List<String> {
        val underlings = tomtarMap[currentName] ?: listOf()

        for (tomte in underlings) {
            if (!res.contains(tomte)) {
                res.add(tomte)
                getUnderlings(tomte, res)
            }
        }
        return res
        //TODO, skriv denna metod, glöm inte att den ska vara rekursiv!
        throw UnsupportedOperationException()
    }
}

fun main() {
    //Exempel på anrop till den rekursiva funktionen getUnderlings,
    // här är tanken att hitta Tröger underlydande
    //listan fylls på successivt när vi rekurserar

    var list: MutableList<String> = mutableListOf()
    println(Tomteland().getUnderlings("Tomten", list))
}
