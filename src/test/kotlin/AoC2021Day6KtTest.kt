import junit.framework.TestCase
import org.junit.jupiter.api.Test

class AoC2021Day6KtTest {

    @Test
    fun räknaFiskarA() {
        val exampleData = listOf(
            "3,4,3,1,2"
        )
        val result = räknaFiskarA(exampleData, 80)
        val result2 = räknaFiskarA(exampleData, 18)

        TestCase.assertEquals(5934, result)
        TestCase.assertEquals(26, result2)
    }

    @Test
    fun förbättradRäknaFiskar() {
        val exampleData = listOf(
            "3,4,3,1,2"
        )
        val result = förbättradRäknaFiskar(exampleData, 80)
        val result2 = förbättradRäknaFiskar(exampleData, 18)

        TestCase.assertEquals(5934L, result)
        TestCase.assertEquals(26L, result2)
    }
}