fun main(args: Array<String>){
    val calculator = Calculator

    if (args.contains("-a")){
        println(" All Operations: ${calculator.getAvailableOperations()} ")
        return
    }

    if(args.contains("-h") || args.contains("--help") || args.isEmpty()){
        println("Usage: Calculator.jar [OPTIONS] EXPRESSION")
        println("Example: Calculator.jar 1+2")
        println()
        println("Options:")
        println("   -a      Print all available operations")
        print("   -h, --help      Print this message")

        return
    }

    val expression: String = args.joinToString("")
    println( "Expression: $expression ")
    println("Result: ${calculator.calculate(expression)}")
}