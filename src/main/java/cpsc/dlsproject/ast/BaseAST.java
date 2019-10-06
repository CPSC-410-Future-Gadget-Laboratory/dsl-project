import cpsc.dlsproject.tools.Tokenizer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public abstract class BaseAST {

    public ArrayList<BaseAST> children = new ArrayList<>();
    protected Tokenizer tokenizer = Tokenizer.getTokenizer();
    protected static PrintWriter writer; //in case you need to write something to a file!
    public static void setWriter(String name) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(name, "UTF-8");
    }
    public static void closeWriter(){
        writer.close();
    }

    abstract public void parse();
    abstract public void evaluate();
    abstract public void nameCheck();
    abstract public void typeCheck();

}
