package Operations.Binary

import Operations.IOperation

interface IBinaryOperation: IOperation {
    fun calculate(a: Double, b: Double): Double
}