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

    private fun parseInput(input: String): List<String>{
        var result: String = input

        for (operation in binaryOperations.keys){
            result = result.replace(operation, " $operation ")
        }

        for (operation in unaryOperations.keys){
            result = result.replace(operation, " $operation ")
        }

        result = result.replace("(", " ( ")
        result = result.replace(")", " ) ")
        result = result.replace("\\s+".toRegex(), " ")
        result = result.trim()

        return result.split(" ")
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