import Operations.Binary.IBinaryOperation
import Operations.IOperation
import Operations.Unary.IUnaryOperation
import org.reflections.Reflections

object OperationsList {
    val operations: HashMap<String, IOperation> = HashMap()

    init {
        Reflections("Operations.Binary").getSubTypesOf(IBinaryOperation::class.java)
            .forEach { x ->
                val operation: IBinaryOperation = x.getConstructor().newInstance()
                operations[operation.name] = operation
            }

        Reflections("Operations.Unary").getSubTypesOf(IUnaryOperation::class.java)
            .forEach { x ->
                val operation: IUnaryOperation = x.getConstructor().newInstance()
                operations[operation.name] = operation
            }
    }

    fun getAvailableOperations(): List<String>{
        return operations.keys.toList()
    }
}