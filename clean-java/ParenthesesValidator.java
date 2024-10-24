import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class ParenthesesValidator {

    private final Stack<Character> brackets = new Stack<>();

    
    public boolean isValidParentheses(String input) {
        BiFunction<Stack<Character>, Character, Boolean> matchTop = (stack, bracket) -> {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            return (bracket == ')' && top == '(') ||
                   (bracket == '}' && top == '{') ||
                   (bracket == ']' && top == '[');
        };

        Predicate<Character> isClosingBracket = c -> c == ')' || c == '}' || c == ']';

  
        return input.chars()
                .mapToObj(c -> (char) c)
                .allMatch(bracket -> {
                    if (isClosingBracket.test(bracket)) {
                        return matchTop.apply(brackets, bracket);
                    } else {
                        brackets.push(bracket);
                        return true;
                    }
                }) && brackets.isEmpty();
    }

    public static void main(String[] args) {
        ParenthesesValidator validator = new ParenthesesValidator();

        System.out.println(validator.isValidParentheses("()"));       // true
        System.out.println(validator.isValidParentheses("()[]{}"));   // true
        System.out.println(validator.isValidParentheses("(]"));       // false
        System.out.println(validator.isValidParentheses("([])"));     // true
    }
}
