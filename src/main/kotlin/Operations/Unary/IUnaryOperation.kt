package Operations.Unary

import Operations.IOperation

interface IUnaryOperation: IOperation {
    fun calculate(a: Double): Double
}