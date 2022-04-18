package Operations.Unary

import kotlin.math.sin

class SinOperation: IUnaryOperation {
    override fun calculate(a: Double): Double {
        return sin(a * Math.PI / 180)
    }

    override val name = "sin"
    override val priority: Byte = 4
}