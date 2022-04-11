import Operations.Binary.AddOperation
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.reflect.Method
import kotlin.math.exp

class CalculatorTest {
    private val calculator: Calculator = Calculator

    @Test
    fun testOperationCollector(){
        val e = calculator.getAvailableOperations()

        assert(e["binary"]!!.contains("+"))
        assert(e["binary"]!!.contains("*"))
    }

    private fun getInputParserMethod(): Method{
        val method: Method = Calculator::class.java.getDeclaredMethod("parseInput", String::class.java)
        method.trySetAccessible()

        return method
    }

    @Test
    fun testInputParserWithoutSpaces(){
        val inputParserMethod: Method = getInputParserMethod()
        val res: List<String> = inputParserMethod.invoke(calculator, "3 +15*(2+3)") as List<String>

        assertEquals(listOf(
            "3", "+", "15", "*",
            "(", "2", "+", "3", ")"), res)
    }

    @Test
    fun testInputParser(){
        val inputParserMethod: Method = getInputParserMethod()
        val res: List<String> = inputParserMethod.invoke(calculator, "       1+3 +2.15 * (  3+2 )  ") as List<String>

        assertEquals(listOf(
            "1", "+", "3", "+",
            "2.15", "*", "(", "3",
            "+", "2", ")"
        ), res)
    }

    @Test
    fun testCalculateEasyExpression(){
        val expression: String = "4 + 3 - 1 * 2 / 2"

        assertEquals(6.0, calculator.calculate(expression))
    }

    @Test
    fun testCalculateBracketExpression(){
        val expression: String = "1+2*(3+4/2-(1+2))*2+1"

        assertEquals(10.0, calculator.calculate(expression))
    }

    @Test
    fun testCalculateOneNumber(){
        val expression: String = "5"

        assertEquals(5.0, calculator.calculate(expression))
    }

    @Test
    fun testCalculateIncorrectInput(){
        val expression: String = "5+(4-2"

        val exception = assertThrows(IllegalArgumentException::class.java) { calculator.calculate(expression) }
        assertEquals(IllegalArgumentException::class, exception::class)
    }
}