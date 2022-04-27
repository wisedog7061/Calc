import Operations.Binary.IBinaryOperation
import Operations.IOperation
import Operations.Unary.IUnaryOperation
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.math.roundToInt

class Calculator {
    private val operations = OperationsList.operations

    private fun parseInput(input: String): List<String> {
        var result: String = input.lowercase(Locale.getDefault())

        for (operation in operations.keys) {
            result = result.replace(operation, " $operation ")
        }

        result = result.replace("(", " ( ")
        result = result.replace(")", " ) ")
        result = result.replace("\\s+".toRegex(), " ")
        result = result.trim()

        return result.split(" ")
    }

    private fun calculateFromStack(numberStack: Stack<Double>, operationName: String) {
        val operation: IOperation = operations[operationName]!!

        if (operation is IUnaryOperation) {
            numberStack.add(operation.calculate(numberStack.pop()))
        } else {
            val second: Double = numberStack.pop()
            val first: Double = numberStack.pop()

            numberStack.add((operation as IBinaryOperation).calculate(first, second))
        }
    }

    fun calculate(input: String): Double { //reverse Polsk notation
        val splitSymbols: List<String> = parseInput(input)

        val numberStack: Stack<Double> = Stack()
        val operationStack: Stack<String> = Stack()

        try {
            for (symbol in splitSymbols) {
                if (symbol.toDoubleOrNull() != null) {
                    numberStack.add(symbol.toDouble())
                    continue
                }

                if (symbol == "(") {
                    operationStack.add(symbol)
                    continue
                }

                if (symbol == ")") {
                    while (!operationStack.empty() && operationStack.peek() != "(") {
                        calculateFromStack(numberStack, operationStack.pop())
                    }

                    operationStack.pop()
                    continue
                }

                if (symbol in operations.keys) {
                    val thisOperation: IOperation = operations[symbol]!!

                    if (operationStack.empty() || operationStack.peek() == "(") {
                        operationStack.add(symbol)
                        continue
                    }

                    var previousOperation: IOperation = operations[operationStack.peek()]!!

                    if (thisOperation.priority > previousOperation.priority) {
                        operationStack.add(thisOperation.name)
                        continue
                    } else {
                        while (!operationStack.empty()) {
                            previousOperation = operations[operationStack.pop()]!!
                            calculateFromStack(numberStack, previousOperation.name)

                            if (operationStack.empty() ||
                                operationStack.peek() == "(" ||
                                thisOperation.priority > operations[operationStack.peek()]!!.priority) {
                                break
                            }
                        }

                        operationStack.add(thisOperation.name)
                        continue
                    }
                }
            }

            while (!operationStack.empty()) {
                calculateFromStack(numberStack, operationStack.pop())
            }
        } catch (e: Exception){
            throw IllegalArgumentException()
        }

        return (numberStack.pop() * 1000).roundToInt() / 1000.0
    }
}