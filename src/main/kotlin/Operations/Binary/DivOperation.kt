package Operations.Binary

class DivOperation: IBinaryOperation {
    override fun calculate(a: Double, b: Double): Double {
        return a / b
    }

    override val name: String = "/"
    override val priority: Byte = 2
}