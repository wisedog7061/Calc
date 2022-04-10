import Operations.Binary.AddOperation
import Operations.Binary.MultiplyOperation
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class OperationsTest {
    @Test
    fun testAdd(){
        val operation = AddOperation()
        assertEquals(35.0, operation.calculate(15.0, 20.0))
        assertEquals("+", operation.name)
        assertEquals(1, operation.priority)
    }

    @Test
    fun testMul(){
        val operation = MultiplyOperation()
        assertEquals(48.0, operation.calculate(6.0, 8.0))
        assertEquals("*", operation.name)
        assertEquals(2, operation.priority)
    }
}

