package cpsc.dlsproject;

import cpsc.dlsproject.ast.PROGRAM;
import cpsc.dlsproject.tools.Tokenizer;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> literals = Arrays.asList("START", "GET", "POST", "PUT", "DELETE",
                "{", "ENDPOINT", "VAR", "SEND", "}", "(", ")", "IF", "ELSE", "PLUS", "MINUS", "MULTI", "DIV", "String", "Number", "Boolean", "\"", "<", ">", "<=", ">=", "==");
        Tokenizer.makeTokenizer("input.epdsl", literals);
        PROGRAM p = new PROGRAM();
        System.out.println("Done tokenizing");
        p.parse();
        System.out.println("Done parsing");
    }
}