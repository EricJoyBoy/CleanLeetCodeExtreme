class ParenthesesValidator {
    private val brackets = mutableListOf<Char>()

    fun isValidParentheses(input: String): Boolean {
        val matchTop: (List<Char>, Char) -> Boolean = { stack, bracket ->
            if (stack.isEmpty()) false
            else {
                val top = stack.removeLast()
                when (bracket) {
                    ')' -> top == '('
                    '}' -> top == '{'
                    ']' -> top == '['
                    else -> false
                }
            }
        }

        val isClosingBracket: (Char) -> Boolean = { it in setOf(')', '}', ']') }

        return input.all { bracket ->
            when {
                isClosingBracket(bracket) -> matchTop(brackets, bracket)
                else -> {
                    brackets.add(bracket)
                    true
                }
            }
        } && brackets.isEmpty()
    }
}

fun main() {
    val validator = ParenthesesValidator()

    println(validator.isValidParentheses("()"))       // true
    println(validator.isValidParentheses("()[]{}"))   // true
    println(validator.isValidParentheses("(]"))       // false
    println(validator.isValidParentheses("([])"))     // true
}
