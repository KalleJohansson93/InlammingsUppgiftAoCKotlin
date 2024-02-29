import junit.framework.TestCase
import org.junit.jupiter.api.Test

class AoC2021Day7KtTest {

    @Test
    fun räknaMinstaBensinåtgång() {
        val exampleData = "16,1,2,0,4,2,7,1,2,14"

        val result = räknaMinstaBensinåtgång(exampleData)

        TestCase.assertEquals(37, result)
    }

    @Test
    fun räknaMinstaBensinåtgångB() {
        val exampleData = "16,1,2,0,4,2,7,1,2,14"

        val result = räknaMinstaBensinåtgångB(exampleData)

        TestCase.assertEquals(168, result)
    }

    @Test
    fun förbättradRäknaMinstaBensinåtgång() {
        val exampleData = "16,1,2,0,4,2,7,1,2,14"

        val result = förbättradRäknaMinstaBensinåtgång(exampleData)

        TestCase.assertEquals(37, result)
    }

    @Test
    fun förbättradRäknaMinstaBensinåtgångB() {
        val exampleData = "16,1,2,0,4,2,7,1,2,14"

        val result = förbättradRäknaMinstaBensinåtgångB(exampleData)

        TestCase.assertEquals(168, result)
    }
}