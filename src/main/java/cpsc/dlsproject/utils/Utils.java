package cpsc.dlsproject.utils;

import cpsc.dlsproject.ast.expressions.values.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return token.equals("PLUS") ||
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

    public String replaceEmbeddedValues(SymbolTable variables, String message) throws Exception {
        Pattern pattern = Pattern.compile("\\{[A-z]*[A-z|0-9]*\\}");
        Matcher matcher = pattern.matcher(message);
        String replacedMsg = message;
        while (matcher.find()) {
            String identifier = matcher.group().substring(1, matcher.group().length() - 1);
            Value value = variables.getValue(identifier);
            String valueStr = value.toString();
            if (value == null) {
                throw new Exception(identifier + " is not defined.");
            }
            replacedMsg = replacedMsg.replaceFirst("\\{" + identifier + "\\}", valueStr);
        }
        return replacedMsg;
    }
}
