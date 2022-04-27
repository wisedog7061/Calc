import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExpressionParserTest {
    @Test
    fun testInputParserWithUnaryMinus(){
        val expression = "-2+-3+(-2-2)-1"
        val res = Expression(expression).symbols

        assertEquals(listOf("-2", "+", "-3",
            "+", "(", "-2",
            "-", "2", ")",
            "-", "1"), res)
    }

    @Test
    fun testInputParserWithoutSpaces(){
        val expression = "3 +15*(2+3)"
        val res = Expression(expression).symbols

        assertEquals(listOf(
            "3", "+", "15", "*",
            "(", "2", "+", "3", ")"), res)
    }

    @Test
    fun testInputParser(){
        val expression = "       1+3 +2.15 * (  3+2 )  "
        val res = Expression(expression).symbols

        assertEquals(listOf(
            "1", "+", "3", "+",
            "2.15", "*", "(", "3",
            "+", "2", ")"
        ), res)
    }
}