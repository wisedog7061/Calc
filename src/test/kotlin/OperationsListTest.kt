import org.junit.jupiter.api.Test

class OperationsListTest {
    private val operationsList = OperationsList

    @Test
    fun testOperationCollector(){
        val e = operationsList.getAvailableOperations()

        assert(e.contains("+"))
        assert(e.contains("*"))
        assert(e.contains("/"))
        assert(e.contains("sin"))
    }
}