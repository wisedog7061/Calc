import java.util.*
import kotlin.collections.ArrayList

class Expression(input: String) {
    private val _symbols: List<String>

    val symbols: List<String>
        get() = _symbols

    init{
        var tmp = input.lowercase(Locale.getDefault())

        for (operation in OperationsList.operations.keys) {
            tmp = tmp.replace(operation, " $operation ")
        }

        tmp = tmp.replace("(", " ( ")
        tmp = tmp.replace(")", " ) ")
        tmp = tmp.replace("\\s+".toRegex(), " ")
        tmp = tmp.trim()

        val symbols = tmp.split(" ")
        val result = ArrayList<String>()

        var i = 0
        while (i < symbols.size){
            val symbol = symbols[i]

            if(symbol == "-" &&
                (i == 0 ||
                        symbols[i-1].toDoubleOrNull() == null &&
                        symbols[i-1] != ")")){
                result.add("${symbol}${symbols[i+1]}")
                i += 2

                continue
            }

            result.add(symbol)
            i++
        }

        _symbols = result.toList()
    }
}