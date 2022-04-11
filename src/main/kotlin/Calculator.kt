import Operations.Binary.IBinaryOperation
import Operations.IOperation
import Operations.Unary.IUnaryOperation
import org.reflections.Reflections
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.roundToInt

object Calculator {
    private val binaryOperations: HashMap<String, IBinaryOperation> = HashMap()
    private val unaryOperations: HashMap<String, IUnaryOperation> = HashMap()

    init {
        Reflections("Operations.Binary").getSubTypesOf(IBinaryOperation::class.java)
            .forEach { x ->
                val operation: IBinaryOperation = x.getConstructor().newInstance()
                binaryOperations[operation.name] = operation
            }

        Reflections("Operations.Unary").getSubTypesOf(IUnaryOperation::class.java)
            .forEach { x ->
                val operation: IUnaryOperation = x.getConstructor().newInstance()
                unaryOperations[operation.name] = operation
            }
    }

    private fun parseInput(input: String): List<String> {
        var result: String = input

        for (operation in binaryOperations.keys) {
            result = result.replace(operation, " $operation ")
        }

        for (operation in unaryOperations.keys) {
            result = result.replace(operation, " $operation ")
        }

        result = result.replace("(", " ( ")
        result = result.replace(")", " ) ")
        result = result.replace("\\s+".toRegex(), " ")
        result = result.trim()

        return result.split(" ")
    }

    fun getAvailableOperations(): HashMap<String, Collection<String>> {
        val res: HashMap<String, Collection<String>> = HashMap()

        res["binary"] = binaryOperations.keys
        res["unary"] = unaryOperations.keys

        return res
    }

    private fun calculateFromStack(numberStack: Stack<Double>, operationName: String) {
        val operation: IOperation = binaryOperations[operationName]
            ?: unaryOperations[operationName]!!

        if (operation is IUnaryOperation) {
            numberStack.add(operation.calculate(numberStack.pop()))
        } else {
            val second: Double = numberStack.pop()
            val first: Double = numberStack.pop()

            numberStack.add((operation as IBinaryOperation).calculate(first, second))
        }
    }

    fun calculate(input: String): Double {
        val splittedSymbols: List<String> = parseInput(input)

        val numberStack: Stack<Double> = Stack()
        val operationStack: Stack<String> = Stack()
        try {
            for (symbol in splittedSymbols) {
                if (symbol.toDoubleOrNull() != null) {
                    numberStack.add(symbol.toDouble())
                    continue
                }

                if (symbol == "(") {
                    operationStack.add("(")
                    continue
                }

                if (symbol == ")") {
                    while (!operationStack.empty() && operationStack.peek() != "(") {
                        calculateFromStack(numberStack, operationStack.pop())
                    }

                    operationStack.pop()
                    continue
                }

                if (symbol in binaryOperations.keys) {
                    val operation: IBinaryOperation = binaryOperations[symbol]!!

                    if (operationStack.empty() || operationStack.peek() == "(") {
                        operationStack.add(symbol)
                        continue
                    }

                    var temp = operationStack.peek()

                    val operationFromStack: IOperation = binaryOperations[temp]
                        ?: unaryOperations[temp]!!

                    if (operation.priority > operationFromStack.priority) {
                        operationStack.add(operation.name)
                        continue
                    } else {
                        while (!operationStack.empty()) {
                            calculateFromStack(numberStack, temp)
                            temp = operationStack.pop()
                            val oper: IOperation = binaryOperations[temp] ?: unaryOperations[temp]!!

                            if (temp == "(" || oper.priority <= oper.priority) {
                                break
                            }
                        }

                        operationStack.add(operation.name)
                        continue
                    }
                }
            }

            while (!operationStack.empty()) {
                calculateFromStack(numberStack, operationStack.peek())
                operationStack.pop()
            }
        } catch (e: Exception){
            throw IllegalArgumentException()
        }

        return (numberStack.pop() * 1000).roundToInt() / 1000.0
    }
}