import junit.framework.TestCase
import org.junit.jupiter.api.Test

class AoC2021Day9KtTest {

    @Test
    fun riskLevel() {
        val exampleData = listOf("2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678")

        val result = riskLevel(exampleData)

        TestCase.assertEquals(15, result)
    }

}