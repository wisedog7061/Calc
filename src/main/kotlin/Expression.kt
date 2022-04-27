import java.util.*

class Expression(input: String) {
    private val _symbols: List<String>

    val symbols: List<String>
        get() = _symbols

    init{
        var result = input.lowercase(Locale.getDefault())

        for (operation in OperationsList.operations.keys) {
            result = result.replace(operation, " $operation ")
        }

        result = result.replace("(", " ( ")
        result = result.replace(")", " ) ")
        result = result.replace("\\s+".toRegex(), " ")
        result = result.trim()

        _symbols = result.split(" ")
    }
}