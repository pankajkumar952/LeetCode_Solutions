import java.util.*;

class Solution {

    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    // expression := term (',' term)*
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < expression.length()
                && expression.charAt(index) == ',') {

            index++; // skip ','

            Set<String> next = parseTerm();
            result.addAll(next); // UNION
        }

        return result;
    }

    // term := factor+
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < expression.length()
                && expression.charAt(index) != '}'
                && expression.charAt(index) != ',') {

            Set<String> next = parseFactor();

            result = concatenate(result, next);
        }

        return result;
    }

    // factor := letter | '{' expression '}'
    private Set<String> parseFactor() {

        char ch = expression.charAt(index);

        // Single lowercase letter
        if (Character.isLetter(ch)) {
            index++;

            Set<String> result = new HashSet<>();
            result.add(String.valueOf(ch));

            return result;
        }

        // Braced expression
        index++; // skip '{'

        Set<String> result = parseExpression();

        index++; // skip '}'

        return result;
    }

    // Cartesian product for concatenation
    private Set<String> concatenate(Set<String> first,
                                    Set<String> second) {

        Set<String> result = new HashSet<>();

        for (String a : first) {
            for (String b : second) {
                result.add(a + b);
            }
        }

        return result;
    }
}
