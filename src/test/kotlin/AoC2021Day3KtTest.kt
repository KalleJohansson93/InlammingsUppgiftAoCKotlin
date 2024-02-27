import junit.framework.TestCase
import org.junit.jupiter.api.Test

class AoC2021Day3KtTest {

    @Test
    fun räknaEnergikonsumptionA() {
        val exampleData = listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
        )
        val result = räknaEnergikonsumptionA(exampleData)

        TestCase.assertEquals(198, result)
    }

    @Test
    fun räknaEnergikonsumptionB() {
        val exampleData = listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
        )
        val result = räknaEnergikonsumptionB(exampleData)

        TestCase.assertEquals(230, result)
    }

    @Test
    fun räknaFörbättradEnergikonsumptionA() {
        val exampleData = listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
        )
        val result = förbättradRäknaEnergikonsumptionB(exampleData)

        TestCase.assertEquals(230, result)
    }

    @Test
    fun räknaFörbättradEnergikonsumptionB() {
        val exampleData = listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
        )
        val result = förbättradRäknaEnergikonsumptionA(exampleData)

        TestCase.assertEquals(198, result)
    }

    @Test
    fun binärTillDecimal() {
        val exampleData = "1010"
        val result = binärTillDecimal(exampleData)

        TestCase.assertEquals(10, result)
    }
}