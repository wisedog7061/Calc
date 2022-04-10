import Operations.Binary.IBinaryOperation
import Operations.Unary.IUnaryOperation
import org.reflections.Reflections

object Calculator {
    private val binaryOperations: HashMap<String, IBinaryOperation> = HashMap()
    private val unaryOperations: HashMap<String, IUnaryOperation> = HashMap()

    init {
        Reflections("Operations.Binary").getSubTypesOf(IBinaryOperation::class.java)
            .forEach { x -> val operation: IBinaryOperation = x.getConstructor().newInstance()
                binaryOperations[operation.name] = operation }

        Reflections("Operations.Unary").getSubTypesOf(IUnaryOperation::class.java)
            .forEach { x -> val operation: IUnaryOperation = x.getConstructor().newInstance()
                unaryOperations[operation.name] = operation }
    }

    private fun inputParser(input: String): List<String>{
        TODO()
    }

    fun getAvailableOperations(): HashMap<String, Collection<String>>{
        val res: HashMap<String, Collection<String>> = HashMap()

        res["binary"] = binaryOperations.keys
        res["unary"] = unaryOperations.keys

        return res
    }

    fun calculate(input: String): Double{
        TODO()
    }
}