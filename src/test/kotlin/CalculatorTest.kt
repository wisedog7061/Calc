import Operations.Binary.AddOperation
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CalculatorTest {
    @Test
    fun testOperationCollector(){
        val calculator = Calculator
        val e = calculator.getAvailableOperations()

        assert(e["binary"]!!.contains("+"))
        assert(e["binary"]!!.contains("*"))
    }
}