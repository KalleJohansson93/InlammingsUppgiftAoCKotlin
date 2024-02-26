
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test
import java.net.URL

class AoC2021Day2KtTest {

@Test
fun testLösningA() {
    // Given
    val exampleData = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2"
    )

    val result = lösningA(exampleData)

    assertEquals(150, result)
}

    @Test
    fun testLösningB() {
        // Given
        val exampleData = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        ).joinToString ("\n")

        val reader = exampleData.byteInputStream().bufferedReader()
        val result = lösningB(reader)

        assertEquals(900, result)
    }

    @Test
    fun testFörbättradLösningA() {
        // Given
        val exampleData = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )

        val url = URL("https://adventofcode.com/2021/day/2/input")
        val result = förbättradLösningA(exampleData)

        assertEquals("150", result)
    }

    @Test
    fun testFörbättradLösningB() {
        // Given
        val exampleData = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )

        val url = URL("https://adventofcode.com/2021/day/2/input")
        val result = förbättradLösningB(exampleData)

        assertEquals("900", result)
    }


}