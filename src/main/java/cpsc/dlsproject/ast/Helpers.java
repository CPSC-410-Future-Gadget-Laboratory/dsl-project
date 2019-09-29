package cpsc.dlsproject.ast;

import cpsc.dlsproject.tools.Tokenizer;

public class Helpers {

    public static boolean CheckForRequestType() {
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        return tokenizer.checkToken("GET") || tokenizer.checkToken("POST") || tokenizer.checkToken("DELETE") || tokenizer.checkToken("PUT");
    }

    public static boolean CheckForCond() {
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        return tokenizer.checkToken("IF") || tokenizer.checkToken("ELSE");
    }

    public static boolean CheckForIO() {
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        return tokenizer.checkToken("READFILE") || tokenizer.checkToken("WRITEFILE");
    }



}
