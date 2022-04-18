import Operations.Binary.*
import Operations.Unary.IUnaryOperation
import Operations.Unary.SinOperation
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.math.roundToInt

class OperationsTest {
    @Test
    fun testAdd(){
        val operation:IBinaryOperation = AddOperation()
        assertEquals(35.0, operation.calculate(15.0, 20.0))
        assertEquals("+", operation.name)
        assertEquals(1, operation.priority)
    }

    @Test
    fun testMul(){
        val operation: IBinaryOperation = MultiplyOperation()
        assertEquals(48.0, operation.calculate(6.0, 8.0))
        assertEquals("*", operation.name)
        assertEquals(2, operation.priority)
    }

    @Test
    fun testSub() {
        val operation:IBinaryOperation = SubOperation()
        assertEquals(5.0, operation.calculate(7.0, 2.0))
        assertEquals("-", operation.name)
        assertEquals(1, operation.priority)
    }

    @Test
    fun testDiv(){
        val operation: IBinaryOperation = DivOperation()
        assertEquals(4.0, operation.calculate(8.0, 2.0))
        assertEquals("/", operation.name)
        assertEquals(2, operation.priority)
    }

    @Test
    fun testSin(){
        val operation: IUnaryOperation = SinOperation()
        assertEquals(0.5, (operation.calculate(30.0) * 1000).roundToInt() / 1000.0)
        assertEquals("sin", operation.name)
        assertEquals(4, operation.priority)
    }
}

