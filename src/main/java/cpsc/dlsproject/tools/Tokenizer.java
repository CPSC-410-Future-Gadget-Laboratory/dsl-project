package cpsc.dlsproject.tools;

import java.util.List;

public class Tokenizer {

    private String[] tokens;
    private int currentToken;
    private static List<String> literals;



    private Tokenizer(String filename, List<String> literalsList) {
        literals = literalsList;
        spaceKillingTokenize();
    }

    private void spaceKillingTokenize() {

    }


    private String checkNext() {
        String token = "";
        if (currentToken < tokens.length) {
            token = tokens[currentToken];
        } else
            token = "NO_MORE_TOKENS";
        return token;
    }

    public String getNext() {
        String token = "";
        if (currentToken < tokens.length) {
            token = tokens[currentToken];
            currentToken++;
        } else
            token = "NULLTOKEN";
        return token;
    }


    public boolean checkToken(String regexp) {
        String s = checkNext();
        System.out.println("comparing: " + s + "  to  " + regexp);
        return (s.matches(regexp));
    }


    public String getAndCheckNext(String regexp) {
        String s = getNext();
        if (!s.matches(regexp)) System.exit(0);
        System.out.println("matched: " + s + "  to  " + regexp);
        return s;
    }


}