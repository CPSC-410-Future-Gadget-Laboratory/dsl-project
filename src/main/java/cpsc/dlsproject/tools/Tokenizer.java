package cpsc.dlsproject.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private static String program;
    private static List<String> literals;
    private String[] tokens;
    private int currentToken;
    private static Tokenizer theTokenizer;

    private Tokenizer(String filename, List<String> literalsList){
        literals = literalsList;
        try {
            program = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Didn't find file");
            System.exit(0);
        }
        spaceKillingTokenize();
    }

    //modifies: this.tokens
    //effects: will result in a list of tokens (sitting at this.tokens) that has no spaces within tokens.
    //          so if you want spaces within tokens, use the spaceHappyTokenize method (above) instead
    private void spaceKillingTokenize(){
        String tokenizedProgram = program;
        tokenizedProgram = tokenizedProgram.replace("\n","");
        tokenizedProgram = tokenizedProgram.replaceAll(";","");
        tokenizedProgram = tokenizedProgram.replace("(","{");
        tokenizedProgram = tokenizedProgram.replace(")","}");
        //Replacing the double quoted strings with single quotes
        List<String> quotedStrings = new ArrayList<>();
        tokenizedProgram = changeDoubleQuotes(tokenizedProgram, quotedStrings);

        //Changing the endpoint braces so in case the endpoint contains curly braces like /api/v1/{userId}
        //the braces don't get tokenized
        List<String> endpoints = new ArrayList<>();
        tokenizedProgram = changeEndPointBraces(tokenizedProgram, endpoints);


        System.out.println(program);
        for (String s : literals){
            tokenizedProgram = tokenizedProgram.replace(s,"_"+s+"_");
        }
        tokenizedProgram = tokenizedProgram.replace("[", "{");
        tokenizedProgram = tokenizedProgram.replace("]", "}");

        //Tokenizing
        List<String> temparray= new LinkedList<>(Arrays.asList(tokenizedProgram.split("[_]+")));

        for(int i = 0; i < quotedStrings.size(); i++){
            quotedStrings.set(i, quotedStrings.get(i).replaceAll("\"", "\'"));
        }

        for(int i = 0; i<temparray.size(); i++)
            if(!quotedStrings.contains(temparray.get(i)))
                temparray.set(i, temparray.get(i).replaceAll("[ ]+", ""));
        temparray.removeAll(Collections.singletonList(""));

        //switch logical operator and first operand
        swapLogicalOperator(temparray);

        tokens = new String[temparray.size()];
        System.arraycopy(temparray.toArray(),0,tokens,0,temparray.size()-1);
        System.out.println(Arrays.asList(tokens));
    }

    private String changeEndPointBraces(String tokenizedProgram, List<String> endpoints) {
        Pattern pattern = Pattern.compile("ENDPOINT[ ]*=[ ]*_'(.*?)'_");
        Matcher m = pattern.matcher(tokenizedProgram);
        while (m.find()) {
            endpoints.add(m.group());
        }
        for (String s : endpoints){
            String changedBraceEndpoint = s.replace('{', '[');
            changedBraceEndpoint = changedBraceEndpoint.replace('}', ']');
            tokenizedProgram = tokenizedProgram.replace(s,changedBraceEndpoint);
            System.out.println(tokenizedProgram);
        }
        return tokenizedProgram;
    }

    private String changeDoubleQuotes(String tokenizedProgram, List<String> quotedStrings) {
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher m = pattern.matcher(tokenizedProgram);
        while (m.find()) {
            quotedStrings.add(m.group());
        }
        for (String s : quotedStrings){
            tokenizedProgram = tokenizedProgram.replace(s,"_'"+s.substring(1, s.length()-1)+"'_");
            System.out.println(tokenizedProgram);
        }
        return tokenizedProgram;
    }

    private void swapLogicalOperator(List<String> temparray) {
        String [] operators = {"&&", "||", "<", ">", "<=", ">=", "=="};
        for(String operator: operators){
            if(temparray.contains(operator)){
                int indexOperator = temparray.indexOf(operator);
                String temp = temparray.get(indexOperator-1);
                temparray.set(indexOperator-1, operator);
                temparray.set(indexOperator, temp);
            }
        }
    }

    private String checkNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
        }
        else
            token="NO_MORE_TOKENS";
        return token;
    }

    public String getNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
            currentToken++;
        }
        else
            token="NULLTOKEN";
        return token;
    }


    public boolean checkToken(String regexp){
        String s = checkNext();
        System.out.println("comparing: "+s+"  to  "+regexp);
        return (s.matches(regexp));
    }


    public String getAndCheckNext(String regexp){
        String s = getNext();
        if (!s.matches(regexp)) System.exit(0);
        System.out.println("matched: "+s+"  to  "+regexp);
        return s;
    }

    public boolean moreTokens(){
        return currentToken<tokens.length;
    }

    public static void makeTokenizer(String s, List<String> literals){
        if (theTokenizer==null) {
            theTokenizer = new Tokenizer(s, literals);
        }
    }

    public static Tokenizer getTokenizer(){
        return theTokenizer;
    }

}