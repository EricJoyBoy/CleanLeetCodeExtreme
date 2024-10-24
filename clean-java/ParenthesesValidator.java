import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class ParenthesesValidator {

    private final Stack<Character> brackets = new Stack<>();

    /**
     * Checks if the given string contains valid parentheses.
     *
     * @param input The string to check for valid parentheses
     * @return True if the parentheses are valid, false otherwise
     */
    public boolean isValidParentheses(String input) {
        // Function to check if the top of the stack matches the current closing bracket
        BiFunction<Stack<Character>, Character, Boolean> matchTop = (stack, bracket) -> {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            return (bracket == ')' && top == '(') ||
                   (bracket == '}' && top == '{') ||
                   (bracket == ']' && top == '[');
        };

        // Predicate to identify closing brackets
        Predicate<Character> isClosingBracket = c -> c == ')' || c == '}' || c == ']';

        // Process each character in the string
        return input.chars()
                .mapToObj(c -> (char) c)
                .allMatch(bracket -> {
                    if (isClosingBracket.test(bracket)) {
                        // Validate closing bracket against the stack
                        return matchTop.apply(brackets, bracket);
                    } else {
                        // Push opening bracket onto the stack
                        brackets.push(bracket);
                        return true;
                    }
                }) && brackets.isEmpty(); // Stack should be empty at the end for valid parentheses
    }

    public static void main(String[] args) {
        ParenthesesValidator validator = new ParenthesesValidator();

        // Test cases
        System.out.println(validator.isValidParentheses("()"));       // true
        System.out.println(validator.isValidParentheses("()[]{}"));   // true
        System.out.println(validator.isValidParentheses("(]"));       // false
        System.out.println(validator.isValidParentheses("([])"));     // true
    }
}
