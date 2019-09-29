package cpsc.dlsproject;

import cpsc.dlsproject.ast.PROGRAM;
import cpsc.dlsproject.tools.Tokenizer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Tokenizer.makeTokenizer();
        PROGRAM p = new PROGRAM();
        p.parse();
    }
}
