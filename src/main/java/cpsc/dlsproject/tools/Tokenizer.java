package cpsc.dlsproject.tools;

import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    private static String program;
    private static List<String> literals;
    private String[] tokens;
    public int currentToken;
    private static Tokenizer theTokenizer;

    private Tokenizer() {
//        tokens = new String[]{"START", "GET", "{", "ENDPOINT", "{", "TESTING123", "}", "}", "END"};
        // THIS IS A TEST
//        tokens = new String[]{"GET", "{", "ENDPOINT", "/v1/hello", ";", "SEND", "{", "200", ";", "Hello World!", ";", "}", "}"};
//       tokens = new String[]{"GET", "{", "ENDPOINT", "/v1/user/{userId}", ";", "IF", "userId", "<", "10", "then", "SEND", "{", "200", ";", "Success", ";", "}", "else", "SEND", "{", "404", ";", "NOT FOUND", ";", "}", "}", "GET", "{", "ENDPOINT", "/v1/hello", ";", "}"};
        tokens = new String[]{"GET", "{", "ENDPOINT", "/v1/user/{userId}", ";", "IF", "(","userId", "<", "10", ")", "{", "SEND", "{", "200", ";", "\"", "Success", "\"", ";", "}", "}", "ELSE", "{", "SEND", "{", "404", ";", "\"", "NOT FOUND", "\"", ";", "}", "}", "}", "GET", "{", "ENDPOINT", "/v1/hello", ";", "}"};
        System.out.println(tokens);
    }

    //modifies: this.tokens
    //effects: will result in a list of tokens (sitting at this.tokens) that has no spaces within tokens.
    //          so if you want spaces within tokens, use the spaceHappyTokenize method (above) instead
    private void spaceKillingTokenize(){
        String tokenizedProgram = program;
        tokenizedProgram = tokenizedProgram.replace("\n","");
        tokenizedProgram = tokenizedProgram.trim();
        System.out.println(program);
    }

    //modifies: this.tokens
    //effects: will result in a list of tokens (sitting at this.tokens) that has spaces within tokens.
    //          this might mean you need to strip off spaces around things during parsing (ick)
    private void spaceHappyTokenize (){
        String tokenizedProgram = program;
        tokenizedProgram = tokenizedProgram.replace("\n","");
        System.out.println(program);
        for (String s : literals){
            tokenizedProgram = tokenizedProgram.replace(s,"_"+s+"_");
            System.out.println(tokenizedProgram);
        }
        tokenizedProgram = tokenizedProgram.replaceAll("__","_");
        System.out.println(tokenizedProgram);
        String [] temparray=tokenizedProgram.split("_");
        tokens = new String[temparray.length-1];
        System.arraycopy(temparray,1,tokens,0,temparray.length-1);
        System.out.println(Arrays.asList(tokens));
    }

    public String checkNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
        }
        else
            token="NO_MORE_TOKENS";
        return token;
    }

    public String checkAheadOfNext(int pos){
        String token="";
        int index = currentToken + pos;
        if (index<tokens.length){
            token = tokens[index];
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
        //Ignore this is a hack
//        if(s.matches("\\{")){
//            return true;
//        }
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

    public static void makeTokenizer(){
        if (theTokenizer==null) {
            theTokenizer = new Tokenizer();
        }
    }

    public static Tokenizer getTokenizer(){
        return theTokenizer;
    }

    public int checkOpenBrackets(){
        int count = 0;
        for (String s: tokens){
            if (s.equals("{")){
                count++;
            }
        }
        return count;
    }


    public boolean checkBracket(String s) {
        return s.equals("}");
    }

    public boolean checkOpenBracket(String s) {
        return s.equals("{");
    }

    public String checkCurrent() {
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
        }
        else
            token="NULLTOKEN";
        return token;
    }

    public String getCurrent() {
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
        }
        else
            token="NULLTOKEN";
        return token;
    }
}