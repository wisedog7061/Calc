//@file:Suppress("UNCHECKED_CAST")

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CalculatorTest {
    private val calculator: Calculator = Calculator()

    @Test
    fun testCalculateEasyExpression(){
        val expression = "4 + 3 - 1 * 2 / 2"

        assertEquals(6.0, calculator.calculate(expression))
    }

    @Test
    fun testCalculateBracketExpression(){
        val expression = "1+2*(3+4/2-(1+2))*2+1"

        assertEquals(10.0, calculator.calculate(expression))
    }

    @Test
    fun testCalculateOneNumber(){
        val expression = "5"

        assertEquals(5.0, calculator.calculate(expression))
    }

    @Test
    fun testCalculateIncorrectInput(){
        val expression = "5+(4-2"
        val exception = assertThrows(IllegalArgumentException::class.java) { calculator.calculate(expression) }

        assertEquals(IllegalArgumentException::class, exception::class)
    }

    @Test
    fun testCalculateWithUnary(){
        val expression = "3+(4-3*8/5+sin(15*2))/2"

        assertEquals(2.85, calculator.calculate(expression))
    }
}