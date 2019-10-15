package cpsc.dlsproject.utils;

public class Utils {
    private static Utils utilsObj = new Utils();

    public static Utils getUtilsObj() {
        return utilsObj;
    }

    public boolean checkIfTokenIsString(String token) {
        return (token.charAt(0) == '\'' && token.charAt(token.length() - 1) == '\'') || (token.charAt(0) == '"' && token.charAt(token.length() - 1) == '"');
    }

    public String getStringLiteralContent(String token) {
        return token.substring(1, token.length() - 1);
    }

    public boolean checkIfTokenIsNumber(String token) {
        try {
            double d = Double.parseDouble(token);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public boolean checkIfTokenIsOperator(String token) {
        return  token.equals("PLUS") ||
                token.equals("MINUS") ||
                token.equals("MULTI") ||
                token.equals("DIV") ||
                token.equals("<") ||
                token.equals("<=") ||
                token.equals("==") ||
                token.equals("!=") ||
                token.equals(">") ||
                token.equals(">=") ||
                token.equals("%") ||
                token.equals("&&") ||
                token.equals("||");
    }
}
